package com.restaurant.service;

import com.restaurant.model.Restaurants;
import com.restaurant.model.UserRatingInfo;
import com.restaurant.repository.RestaurantRepository;
import com.restaurant.repository.UserRatingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Kuldeep Gupta
 */
public class UserRatingInfoServiceImpl implements UserRatingInfoService {

    @Autowired
    UserRatingInfoRepository userRatingInfoRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public UserRatingInfo getUserRatingInfo(String resId) {
            return userRatingInfoRepository.findByResId(resId);
    }

    @Override
    public void saveUserRatingInfo(Map<String, String> map) {
        Optional<UserRatingInfo> userRatingInfo=null;
          //userRatingInfo.setRating_text("");
          //userRatingInfo.setVotes(map.get("vote"));
          //userRatingInfo.setRating_color("");
        UserRatingInfo usrInfo= new UserRatingInfo();
        userRatingInfo= userRatingInfoRepository.findById(Long.valueOf(map.get("id")));
        double rating=0;
        double ratingFromUser=0;
        double aggregaterating=0;
        Restaurants restaurants= new Restaurants();
        Optional<Restaurants> restaurantsOptional=restaurantRepository.findById(map.get("res_id"));
        restaurants=restaurantsOptional.get();
        if(userRatingInfo!=null){
            usrInfo=userRatingInfo.get();
            rating=Double.parseDouble(usrInfo.getAggregate_rating());
            ratingFromUser=Double.parseDouble(map.get("rating"));
            aggregaterating=rating+ratingFromUser/2;
            usrInfo.setAggregate_rating(String.valueOf(aggregaterating));
            userRatingInfoRepository.save(usrInfo);
            restaurants.setUser_rating(usrInfo);
            restaurantRepository.save(restaurants);
        }else {
            usrInfo.setAggregate_rating(map.get("rating"));
            usrInfo.setVotes("1");
            usrInfo.setResId(map.get("res_id"));
            double colorRate=Double.parseDouble(map.get("rating"));
            String color="";
            String ratingtext="";
            if(colorRate>=0 && colorRate<=0.5){
                color=Color.gray.name();
                ratingtext="Not Rated";
            }else if(colorRate>=0.5 && colorRate<1){
                color=Color.yellow.name();
                ratingtext="Average";
            }else if(colorRate>=1 && colorRate<2){
                color=Color.lightgreen.name();
                ratingtext="Average";
            }else if(colorRate>=2 && colorRate<3){
                color=Color.lightgreen.name();
                ratingtext="Good";
            }else if(colorRate>=3 && colorRate<4){
                color=Color.green.name();
                ratingtext="Very Good";
            }else if(colorRate>=4 && colorRate<=5) {
                color = Color.darkgreen.name();
                ratingtext="Excellent";
            }
                usrInfo.setRating_color(color);
            usrInfo.setRating_text(ratingtext);
            userRatingInfoRepository.save(usrInfo);
            restaurants.setUser_rating(usrInfo);
            restaurantRepository.save(restaurants);
        }

    }
}
