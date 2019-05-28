package com.restaurant.web;

import com.restaurant.model.RestaurantResponse;
import com.restaurant.model.Restaurants;
import com.restaurant.model.User;
import com.restaurant.model.UserRating;
import com.restaurant.repository.UserRepository;
import com.restaurant.service.RestaurantService;
import com.restaurant.service.UserRatingInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
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
    @Autowired
    UserRatingInfoService userRatingInfoService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public String search(@RequestBody Map<String,String> map, Model model){
        RestaurantResponse restaurantResponse =restaurantService.searchRestaurant(map);
        List<Restaurants> restaurantsList = new ArrayList<>();
        if(restaurantResponse!=null){
            restaurantsList = restaurantResponse.getRestaurants();
        }
        restaurantService.saveRestaurantData(restaurantResponse);
        model.addAttribute("restaurants",restaurantsList);
        //logger.info("respaonse{}",responseEntity.getBody());
        //ModelAndView mav = new ModelAndView("search-result");
        return "search-result";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/restaurant-details/{res_id}")
    public String getRestuarantDetails(@PathVariable("res_id") String res_id,Model model){
        Restaurants restaurants=restaurantService.getRestaurantById(res_id);
        System.out.println(restaurants.getUser_rating().getAggregate_rating());
        model.addAttribute("restaurant",restaurants);
        return "restaurant-details";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/rating")
    public String putAndGetRestaurantRating(@RequestBody Map<String,String> map,Model model){
        long userId=0;
        String userName="";
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetails) {
            userName = ((UserDetails)userDetails).getUsername();
        }
        User user = userRepository.findByUsername(userName);
        if(user!=null)
            userId=user.getId();
        //userRatingInfoService.saveUserAndRatingIds(userId,Long.valueOf(map.get("id")));
        userRatingInfoService.saveUserRatingInfo(map,userId);
        //UserRating userRating=userRatingInfoService.getUserRatingByRatingId(Long.valueOf(map.get("id")));
       // userRatingInfoService.saveUserAndRatingIds(userId,Long.valueOf(map.get("id")));
        Restaurants restaurants= restaurantService.getRestaurantById(map.get("res_id"));

        model.addAttribute("restaurant",restaurants);
        return "restaurant-details";

    }

}
