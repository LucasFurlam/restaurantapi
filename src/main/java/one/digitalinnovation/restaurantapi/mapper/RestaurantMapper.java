package one.digitalinnovation.restaurantapi.mapper;

import one.digitalinnovation.restaurantapi.dto.request.RestaurantDTO;
import one.digitalinnovation.restaurantapi.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant toModel(RestaurantDTO restaurantDTO);

    RestaurantDTO toDTO(Restaurant restaurant);

}
