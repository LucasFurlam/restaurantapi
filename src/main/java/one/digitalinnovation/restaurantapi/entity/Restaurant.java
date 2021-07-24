package one.digitalinnovation.restaurantapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "freight_rate", nullable = false)
    private BigDecimal freightRate;

    @ManyToOne
    @JoinColumn(name = "cookery_id", nullable = false)
    private Cookery cookery;

}
