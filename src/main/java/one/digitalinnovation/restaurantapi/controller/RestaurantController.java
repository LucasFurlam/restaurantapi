package one.digitalinnovation.restaurantapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    @GetMapping
    public String getRestaurant() {
        return "API Test!";
    }

}
