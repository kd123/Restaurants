package com.restaurant.model;

import java.util.List;

/**
 * @author Kuldeep Gupta
 */
public class RestaurantResponse {

    private List<Restaurants> restaurants;

    public List<Restaurants> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurants> restaurants) {
        this.restaurants = restaurants;
    }
}
