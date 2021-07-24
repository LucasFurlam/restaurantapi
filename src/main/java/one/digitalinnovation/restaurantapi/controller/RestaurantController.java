package one.digitalinnovation.restaurantapi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.restaurantapi.dto.request.RestaurantDTO;
import one.digitalinnovation.restaurantapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.restaurantapi.exception.RestaurantNotFoundException;
import one.digitalinnovation.restaurantapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestaurantController {

    private RestaurantService restaurantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@RequestBody @Valid RestaurantDTO restaurantDTO) {
        return restaurantService.create(restaurantDTO);
    }

    @GetMapping
    public List<RestaurantDTO> listAll() {
        return restaurantService.listAll();
    }

    @GetMapping("/{id}")
    public RestaurantDTO findById(@PathVariable Long id) throws RestaurantNotFoundException {
        return restaurantService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid RestaurantDTO restaurantDTO) throws RestaurantNotFoundException {
        return restaurantService.updateById(id, restaurantDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws RestaurantNotFoundException {
        restaurantService.delete(id);
    }

}
