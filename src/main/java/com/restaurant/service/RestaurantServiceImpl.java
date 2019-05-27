package com.restaurant.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.model.RestaurantResponse;
import com.restaurant.model.Restaurants;
import com.restaurant.model.UserRatingInfo;
import com.restaurant.repository.RestaurantRepository;
import com.restaurant.util.ObjectMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.restaurant.util.RestTempateUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * @author Kuldeep Gupta
 */

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private static final String CITY="city";
    private static final String API_KEY_USER="user-key";
    private static final String ZOMATO_API_KEY="96b91246acf06fe50ccd86fe5d4514a1";
    private static final String URL="https://developers.zomato.com/api/v2.1/search";

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    RestaurantRepository restaurantRepository;

    private static final Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Override
    public RestaurantResponse searchRestaurant(Map<String,String> req){
        String quesryStr = req.get("q");
        HttpHeaders headers = getHeader();
        MultiValueMap<String, String> urlParam = new LinkedMultiValueMap();
        urlParam.add("entity_type", CITY);
        urlParam.add("q", quesryStr);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);
        builder.queryParams(urlParam);
        HttpEntity entity = new HttpEntity(headers);
        String url = builder.toUriString();
        RestTemplate restTemplate = RestTempateUtil.getRestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, urlParam);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode responseData = mapper.readTree(response.getBody());
            JsonNode result = responseData.get("restaurants");
            RestaurantResponse restaurantResponse = ObjectMapperUtil.getRestaurantData(result);
            logger.info("zomato response body {}", restaurantResponse.getRestaurants());
            return restaurantResponse;
        } catch (Exception e) {
            logger.error("zomato API failed  {}", e);
            return null;
        }
        //logger.info("zomato response body {}", response.getBody());

    }

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(API_KEY_USER, ZOMATO_API_KEY);
        headers.add("Accept", "application/json");
        return headers;
    }


    @Override
    public void saveRestaurantData(RestaurantResponse restaurantResponse) {
        Restaurants restaurants = new Restaurants();
        for(Restaurants res : restaurantResponse.getRestaurants()){
            restaurants.setId(res.getId());
            restaurants.setName(res.getName());
            restaurants.setThumb(res.getThumb());
            restaurants.setCuisines(res.getCuisines());
            restaurants.setAverage_cost_for_two(res.getAverage_cost_for_two());
            restaurants.setLocality(res.getLocality());
            UserRatingInfo userRatingInfo = new UserRatingInfo();
            userRatingInfo.setRating_text("Not Rated");
            userRatingInfo.setRating_color(Color.gray.name());
            userRatingInfo.setResId(res.getId());
            userRatingInfo.setVotes("0");
            userRatingInfo.setAggregate_rating("0");
            restaurants.setUser_rating(userRatingInfo);
            restaurantRepository.save(restaurants);

        }
    }

    @Override
    public Restaurants getRestaurantById(String resId){
        Restaurants restaurant = new Restaurants();
        restaurant = restaurantRepository.findById(resId).get();
        return restaurant;
    }
}
