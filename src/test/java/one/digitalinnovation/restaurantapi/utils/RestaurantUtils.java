package one.digitalinnovation.restaurantapi.utils;

import one.digitalinnovation.restaurantapi.dto.request.RestaurantDTO;
import one.digitalinnovation.restaurantapi.entity.Restaurant;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

public class RestaurantUtils {

    private static final String NAME = "Lucas";
    private static final BigDecimal FREIGHT_RATE = BigDecimal.valueOf(2.20);
    private static final long RESTAURANT_ID = 1L;

    public static RestaurantDTO createFakeDTO() {
        return RestaurantDTO.builder()
                .name(NAME)
                .freightRate(FREIGHT_RATE)
                .cookeries(Collections.singletonList(CookeryUtils.createFakeDTO()))
                .build();
    }

    public static Restaurant createFakeEntity() {
        return Restaurant.builder()
                .id(RESTAURANT_ID)
                .name(NAME)
                .freightRate(FREIGHT_RATE)
                .cookeries(Collections.singletonList(CookeryUtils.createFakeEntity()))
                .build();
    }

}
