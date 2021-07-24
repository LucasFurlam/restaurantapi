package one.digitalinnovation.restaurantapi.service;

import one.digitalinnovation.restaurantapi.dto.request.RestaurantDTO;
import one.digitalinnovation.restaurantapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.restaurantapi.entity.Restaurant;
import one.digitalinnovation.restaurantapi.exception.RestaurantNotFoundException;
import one.digitalinnovation.restaurantapi.mapper.RestaurantMapper;
import one.digitalinnovation.restaurantapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper = RestaurantMapper.INSTANCE;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public MessageResponseDTO createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurantToSave = restaurantMapper.toModel(restaurantDTO);

        Restaurant savedRestaurant = restaurantRepository.save(restaurantToSave);
        return MessageResponseDTO
                .builder()
                .message("Create restaurant with ID " + savedRestaurant.getId())
                .build();
    }

    public List<RestaurantDTO> listAll() {
        List<Restaurant> allRestaurants = restaurantRepository.findAll();
        return allRestaurants.stream()
                .map(restaurantMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RestaurantDTO findById(Long id) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        return restaurantMapper.toDTO(restaurant);
    }
}
