package one.digitalinnovation.restaurantapi.service;

import one.digitalinnovation.restaurantapi.dto.request.RestaurantDTO;
import one.digitalinnovation.restaurantapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.restaurantapi.entity.Restaurant;
import one.digitalinnovation.restaurantapi.exception.RestaurantNotFoundException;
import one.digitalinnovation.restaurantapi.mapper.RestaurantMapper;
import one.digitalinnovation.restaurantapi.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static one.digitalinnovation.restaurantapi.utils.RestaurantUtils.createFakeDTO;
import static one.digitalinnovation.restaurantapi.utils.RestaurantUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    void testGivenRestaurantDTOThenReturnSavedMessage() {
        RestaurantDTO restaurantDTO = createFakeDTO();
        Restaurant expectedSavedRestaurant = createFakeEntity();

        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(expectedSavedRestaurant);

        MessageResponseDTO successMessage = restaurantService.create(restaurantDTO);

        assertEquals("Created restaurant with ID 1", successMessage.getMessage());
    }

    @Test
    void testGivenValidRestaurantIdThenReturnThisRestaurant() throws RestaurantNotFoundException {
        RestaurantDTO expectedRestaurantDTO = createFakeDTO();
        Restaurant expectedSavedRestaurant = createFakeEntity();
        expectedRestaurantDTO.setId(expectedSavedRestaurant.getId());
        expectedRestaurantDTO.getCookeries().forEach(cookeryDTO -> {
            expectedSavedRestaurant.getCookeries().forEach(cookery -> {
                if (cookeryDTO.getName().equals(cookery.getName())) {
                    cookeryDTO.setId(cookery.getId());
                }
            });
        });

        when(restaurantRepository.findById(expectedSavedRestaurant.getId())).thenReturn(Optional.of(expectedSavedRestaurant));

        RestaurantDTO restaurantDTO = restaurantService.findById(expectedSavedRestaurant.getId());

        assertEquals(expectedRestaurantDTO, restaurantDTO);

        assertEquals(expectedSavedRestaurant.getId(), restaurantDTO.getId());
        assertEquals(expectedSavedRestaurant.getName(), restaurantDTO.getName());
    }

    @Test
    void testGivenInvalidRestaurantIdThenThrowException() {
        var invalidRestaurantId = 1L;
        when(restaurantRepository.findById(invalidRestaurantId))
                .thenReturn(Optional.ofNullable(any(Restaurant.class)));

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.findById(invalidRestaurantId));
    }

    @Test
    void testGivenNoDataThenReturnAllRestaurantsRegistered() {
        List<Restaurant> expectedRegisteredRestaurants = Collections.singletonList(createFakeEntity());
        RestaurantDTO restaurantDTO = createFakeDTO();
        restaurantDTO.setId(1L);

        when(restaurantRepository.findAll()).thenReturn(expectedRegisteredRestaurants);

        List<RestaurantDTO> expectedRestaurantsDTOList = restaurantService.listAll();

        assertFalse(expectedRestaurantsDTOList.isEmpty());
        assertEquals(expectedRestaurantsDTOList.get(0).getId(), restaurantDTO.getId());
    }

    @Test
    void testGivenValidRestaurantIdAndUpdateInfoThenReturnSuccesOnUpdate() throws RestaurantNotFoundException {
        var updatedRestaurantId = 2L;

        RestaurantDTO updateRestaurantDTORequest = createFakeDTO();
        updateRestaurantDTORequest.setId(updatedRestaurantId);
        updateRestaurantDTORequest.setName("Lucas updated");

        Restaurant expectedRestaurantToUpdate = createFakeEntity();
        expectedRestaurantToUpdate.setId(updatedRestaurantId);

        Restaurant expectedRestaurantUpdated = createFakeEntity();
        expectedRestaurantUpdated.setId(updatedRestaurantId);
        expectedRestaurantUpdated.setFreightRate(updateRestaurantDTORequest.getFreightRate());

        when(restaurantRepository.findById(updatedRestaurantId)).thenReturn(Optional.of(expectedRestaurantUpdated));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(expectedRestaurantUpdated);

        MessageResponseDTO successMessage = restaurantService.updateById(updatedRestaurantId, updateRestaurantDTORequest);

        assertEquals("Updated restaurant with ID 2", successMessage.getMessage());
    }

    @Test
    void testGivenInvalidRestaurantIdAndUpdateInfoThenThrowExceptionOnUpdate() throws RestaurantNotFoundException {
        var invalidRestaurantId = 1L;

        RestaurantDTO updateRestaurantDTORequest = createFakeDTO();
        updateRestaurantDTORequest.setId(invalidRestaurantId);
        updateRestaurantDTORequest.setName("Lucas updated");

        when(restaurantRepository.findById(invalidRestaurantId))
                .thenReturn(Optional.ofNullable(any(Restaurant.class)));

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.updateById(invalidRestaurantId, updateRestaurantDTORequest));
    }

    @Test
    void testGivenValidRestaurantIdThenReturnSuccesOnDelete() throws RestaurantNotFoundException {
        var deletedRestaurantId = 1L;
        Restaurant expectedRestaurantToDelete = createFakeEntity();

        when(restaurantRepository.findById(deletedRestaurantId)).thenReturn(Optional.of(expectedRestaurantToDelete));
        restaurantService.delete(deletedRestaurantId);


        verify(restaurantRepository, times(1)).deleteById(deletedRestaurantId);
    }

    @Test
    void testGivenInvalidRestaurantIdThenReturnSuccesOnDelete() throws RestaurantNotFoundException {
        var invalidRestaurantId = 1L;

        when(restaurantRepository.findById(invalidRestaurantId))
                .thenReturn(Optional.ofNullable(any(Restaurant.class)));

        assertThrows(RestaurantNotFoundException.class, () -> restaurantService.delete(invalidRestaurantId));
    }

}
