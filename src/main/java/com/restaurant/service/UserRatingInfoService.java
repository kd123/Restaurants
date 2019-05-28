package com.restaurant.service;

import com.restaurant.model.UserRatingInfo;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Kuldeep Gupta
 */

@Component
public interface UserRatingInfoService {

    public UserRatingInfo getUserRatingInfo(String resId);

    public UserRatingInfo saveUserRatingInfo(Map<String,String> map,long userId);

    public void saveUserAndRatingIds(long userId,long ratingId);


}
