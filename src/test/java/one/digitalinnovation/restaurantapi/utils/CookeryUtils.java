package one.digitalinnovation.restaurantapi.utils;

import one.digitalinnovation.restaurantapi.dto.request.CookeryDTO;
import one.digitalinnovation.restaurantapi.entity.Cookery;

public class CookeryUtils {

    private static final String NAME = "Lucas";
    private static final long COOKERY_ID = 1L;

    public static CookeryDTO createFakeDTO() {
        return CookeryDTO.builder()
                .name(NAME)
                .build();
    }

    public static Cookery createFakeEntity() {
        return Cookery.builder()
                .id(COOKERY_ID)
                .name(NAME)
                .build();
    }

}
