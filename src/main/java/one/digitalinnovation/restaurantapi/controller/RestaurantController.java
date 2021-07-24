package one.digitalinnovation.restaurantapi.controller;

import one.digitalinnovation.restaurantapi.dto.MessageResponseDTO;
import one.digitalinnovation.restaurantapi.dto.request.RestaurantDTO;
import one.digitalinnovation.restaurantapi.entity.Restaurant;
import one.digitalinnovation.restaurantapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createRestaurant(@RequestBody @Valid RestaurantDTO restaurantDTO) {
        return restaurantService.createRestaurant(restaurantDTO);
    }

    @GetMapping
    public List<RestaurantDTO> listAll() {
        return restaurantService.listAll();
    }

}
