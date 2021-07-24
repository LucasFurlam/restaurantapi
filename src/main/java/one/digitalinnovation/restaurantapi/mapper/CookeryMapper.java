package one.digitalinnovation.restaurantapi.mapper;

import one.digitalinnovation.restaurantapi.dto.request.CookeryDTO;
import one.digitalinnovation.restaurantapi.entity.Cookery;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CookeryMapper {

    CookeryMapper INSTANCE = Mappers.getMapper(CookeryMapper.class);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<CookeryDTO> toDTO(List<Cookery> cookery);

}
