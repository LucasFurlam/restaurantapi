package one.digitalinnovation.restaurantapi.repository;

import one.digitalinnovation.restaurantapi.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
