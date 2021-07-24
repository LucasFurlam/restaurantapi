package one.digitalinnovation.restaurantapi.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import one.digitalinnovation.restaurantapi.dto.request.RestaurantDTO;
import one.digitalinnovation.restaurantapi.entity.Restaurant;

import java.math.BigDecimal;
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

    public static String asJsonString(RestaurantDTO restaurantDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(restaurantDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
