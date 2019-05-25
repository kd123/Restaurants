package com.restaurant.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.restaurant.model.RestaurantResponse;
import com.restaurant.model.Restaurants;
import com.restaurant.model.UserRatingInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kuldeep Gupta
 */
public class ObjectMapperUtil {

    public static RestaurantResponse getRestaurantData(JsonNode jsonArray){
        RestaurantResponse restaurantResponse = new RestaurantResponse();
        List<Restaurants> restaurantsList = new ArrayList<>();
        if(jsonArray.isArray()){
            for (final JsonNode objNode : jsonArray) {
                JsonNode jsonNode = objNode.get("restaurant");
                Restaurants restaurants=getRestaurantInfo(jsonNode);
                restaurantsList.add(restaurants);
                //System.out.println(objNode);
            }
        }
        restaurantResponse.setRestaurants(restaurantsList);
        return restaurantResponse;
    }

    private static Restaurants getRestaurantInfo(JsonNode jsonNode){
        Restaurants restaurants = new Restaurants();
        restaurants.setId(jsonNode.get("id").asText());
        restaurants.setCuisines(jsonNode.get("cuisines").asText());
        restaurants.setName(jsonNode.get("name").asText());
        restaurants.setAverage_cost_for_two(jsonNode.get("average_cost_for_two").asText());
        restaurants.setThumb(jsonNode.get("thumb").asText());
        restaurants.setUrl(jsonNode.get("url").asText());
        restaurants.setUser_rating(getUserRatingInfo(jsonNode.get("user_rating")));
        return restaurants;
    }

    private static UserRatingInfo getUserRatingInfo(JsonNode jsonNode){
        UserRatingInfo userRatingInfo = new UserRatingInfo();
        userRatingInfo.setAggregate_rating(jsonNode.get("aggregate_rating").asText());
        userRatingInfo.setRating_color(jsonNode.get("rating_color").asText());
        userRatingInfo.setVotes(jsonNode.get("votes").asText());
        userRatingInfo.setRating_text(jsonNode.get("rating_text").asText());
        return userRatingInfo;
    }
}
