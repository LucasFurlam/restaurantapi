package one.digitalinnovation.restaurantapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal freightRate;

    @Valid
    @NotNull
    private List<CookeryDTO> cookeries;

}
