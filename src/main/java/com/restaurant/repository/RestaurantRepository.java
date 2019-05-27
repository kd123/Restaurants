package com.restaurant.repository;

import com.restaurant.model.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kuldeep Gupta
 */
public interface RestaurantRepository extends JpaRepository<Restaurants,String> {
}
