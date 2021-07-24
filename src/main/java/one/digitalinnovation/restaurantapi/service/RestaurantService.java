package one.digitalinnovation.restaurantapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.restaurantapi.dto.request.RestaurantDTO;
import one.digitalinnovation.restaurantapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.restaurantapi.entity.Restaurant;
import one.digitalinnovation.restaurantapi.exception.RestaurantNotFoundException;
import one.digitalinnovation.restaurantapi.mapper.CookeryMapper;
import one.digitalinnovation.restaurantapi.mapper.RestaurantMapper;
import one.digitalinnovation.restaurantapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper = RestaurantMapper.INSTANCE;

    private final CookeryMapper cookeryMapper = CookeryMapper.INSTANCE;

    public MessageResponseDTO create(RestaurantDTO restaurantDTO) {
        Restaurant restaurantToSave = restaurantMapper.toModel(restaurantDTO);

        Restaurant savedRestaurant = restaurantRepository.save(restaurantToSave);
        return createMessageResponse(savedRestaurant.getId(), "Created restaurant with ID ");
    }

    public List<RestaurantDTO> listAll() {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        return allRestaurants.stream()
                .map(restaurantMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RestaurantDTO findById(Long id) throws RestaurantNotFoundException {
        Restaurant restaurant = verifyIfExists(id);
        RestaurantDTO restaurantDTO = restaurantMapper.toDTO(restaurant);
        restaurantDTO.setCookeries(cookeryMapper.toDTO(restaurant.getCookeries()));
        return restaurantDTO;
    }

    public MessageResponseDTO updateById(Long id, RestaurantDTO restaurantDTO) throws RestaurantNotFoundException {
        verifyIfExists(id);

        Restaurant restaurantToUpdate = restaurantMapper.toModel(restaurantDTO);

        Restaurant updatedRestaurant = restaurantRepository.save(restaurantToUpdate);
        return createMessageResponse(updatedRestaurant.getId(), "Updated restaurant with ID ");
    }

    public void delete(Long id) throws RestaurantNotFoundException {
        verifyIfExists(id);
        restaurantRepository.deleteById(id);
    }

    private Restaurant verifyIfExists(Long id) throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

}
