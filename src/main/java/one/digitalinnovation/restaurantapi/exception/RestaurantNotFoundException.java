package one.digitalinnovation.restaurantapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestaurantNotFoundException extends Exception {

    public RestaurantNotFoundException(Long id) {
        super("Restaurant not found with ID " + id);
    }
}
