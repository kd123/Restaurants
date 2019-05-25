package com.restaurant.web;

import com.restaurant.model.RestaurantResponse;
import com.restaurant.model.Restaurants;
import com.restaurant.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Kuldeep Gupta
 */

@Controller
@RequestMapping(value = "/restaurant")
public class RestaurantSearch {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantSearch.class);

    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public String search(@RequestBody Map<String,String> map, Model model){
        RestaurantResponse restaurantResponse =restaurantService.searchRestaurant(map);
        List<Restaurants> restaurantsList = new ArrayList<>();
        if(restaurantResponse!=null){
            restaurantsList = restaurantResponse.getRestaurants();
        }
        model.addAttribute("restaurants",restaurantsList);
        //logger.info("respaonse{}",responseEntity.getBody());
        //ModelAndView mav = new ModelAndView("search-result");
        return "search-result";
    }


}