package com.restaurant.repository;

import com.restaurant.model.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Kuldeep Gupta
 */
public interface UserRatingRepository extends JpaRepository<UserRating,Long> {
    List<UserRating> findByUserId(long userId);
    List<UserRating> findByRatingId(long ratingId);
}
