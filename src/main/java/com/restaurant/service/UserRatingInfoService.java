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

    public void saveUserRatingInfo(Map<String,String> map);


}
