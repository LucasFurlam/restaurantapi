package one.digitalinnovation.restaurantapi.mapper;

import one.digitalinnovation.restaurantapi.dto.request.CookeryDTO;
import one.digitalinnovation.restaurantapi.dto.request.RestaurantDTO;
import one.digitalinnovation.restaurantapi.entity.Cookery;
import one.digitalinnovation.restaurantapi.entity.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static one.digitalinnovation.restaurantapi.utils.RestaurantUtils.createFakeDTO;
import static one.digitalinnovation.restaurantapi.utils.RestaurantUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RestaurantMapperTest {

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Test
    void testGivenRestaurantDTOThenReturnRestaurantEntity() {
        RestaurantDTO restaurantDTO = createFakeDTO();
        Restaurant restaurant = restaurantMapper.toModel(restaurantDTO);

        assertEquals(restaurantDTO.getName(), restaurant.getName());
        assertEquals(restaurantDTO.getFreightRate(), restaurant.getFreightRate());

        Cookery cookery = restaurant.getCookeries().get(0);
        CookeryDTO cookeryDTO = restaurantDTO.getCookeries().get(0);

        assertEquals(cookeryDTO.getName(), cookery.getName());
    }

    @Test
    void testGivenRestaurantEntityThenReturnRestaurantDTO() {
        Restaurant restaurant = createFakeEntity();
        RestaurantDTO restaurantDTO = restaurantMapper.toDTO(restaurant);

        assertEquals(restaurantDTO.getName(), restaurant.getName());
        assertEquals(restaurantDTO.getFreightRate(), restaurant.getFreightRate());

        Cookery cookery = restaurant.getCookeries().get(0);
        CookeryDTO cookeryDTO = restaurantDTO.getCookeries().get(0);

        assertEquals(cookeryDTO.getName(), cookery.getName());
    }

}
