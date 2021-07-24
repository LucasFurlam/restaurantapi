package one.digitalinnovation.restaurantapi.service;

import one.digitalinnovation.restaurantapi.dto.request.RestaurantDTO;
import one.digitalinnovation.restaurantapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.restaurantapi.entity.Restaurant;
import one.digitalinnovation.restaurantapi.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.restaurantapi.utils.RestaurantUtils.createFakeDTO;
import static one.digitalinnovation.restaurantapi.utils.RestaurantUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    void testGivenRestaurantDTOThenReturnSavedMessage() {
        RestaurantDTO restaurantDTO = createFakeDTO();
        Restaurant expectedSavedRestaurant = createFakeEntity();

        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(expectedSavedRestaurant);

        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedRestaurant.getId());
        MessageResponseDTO successMessage = restaurantService.createRestaurant(restaurantDTO);

        assertEquals(expectedSuccessMessage, successMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created restaurant with ID " + id)
                .build();
    }
}
