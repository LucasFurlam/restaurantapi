package one.digitalinnovation.restaurantapi.controller;

import one.digitalinnovation.restaurantapi.dto.MessageResponseDTO;
import one.digitalinnovation.restaurantapi.entity.Restaurant;
import one.digitalinnovation.restaurantapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    public MessageResponseDTO createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return MessageResponseDTO
                .builder()
                .message("Create restaurant with ID " + savedRestaurant.getId())
                .build();
    }

}
