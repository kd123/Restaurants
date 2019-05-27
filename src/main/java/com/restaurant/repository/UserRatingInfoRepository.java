package com.restaurant.repository;

import com.restaurant.model.UserRatingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kuldeep Gupta
 */
public interface UserRatingInfoRepository extends JpaRepository<UserRatingInfo,Long> {

    UserRatingInfo findByResId(String resId);
}
