package com.restaurant.service;

import com.restaurant.model.RestaurantResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Kuldeep Gupta
 */
@Component
public interface RestaurantService {

    public RestaurantResponse searchRestaurant(Map<String,String> req);
}
