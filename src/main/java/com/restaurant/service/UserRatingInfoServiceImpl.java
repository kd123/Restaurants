package com.restaurant.service;

import com.restaurant.model.Restaurants;
import com.restaurant.model.User;
import com.restaurant.model.UserRating;
import com.restaurant.model.UserRatingInfo;
import com.restaurant.repository.RestaurantRepository;
import com.restaurant.repository.UserRatingInfoRepository;
import com.restaurant.repository.UserRatingRepository;
import com.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Kuldeep Gupta
 */
@Service
public class UserRatingInfoServiceImpl implements UserRatingInfoService {

    @Autowired
    UserRatingInfoRepository userRatingInfoRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    UserRatingRepository userRatingRepository;


    @Override
    public UserRatingInfo getUserRatingInfo(String resId) {
            return userRatingInfoRepository.findByResId(resId);
    }

    @Override
    public UserRatingInfo saveUserRatingInfo(Map<String, String> map,long userId) {
        Optional<UserRatingInfo> userRatingInfo=null;
          //userRatingInfo.setRating_text("");
          //userRatingInfo.setVotes(map.get("vote"));
          //userRatingInfo.setRating_color("");
        UserRatingInfo usrInfo= new UserRatingInfo();
        String ratingId=StringUtils.isEmpty(map.get("id"))?"0":map.get("id");
        userRatingInfo= userRatingInfoRepository.findById(Long.valueOf(ratingId));

        double rating=0;
        double ratingFromUser=0;
        double aggregaterating=0;
        Restaurants restaurants= new Restaurants();
        Optional<Restaurants> restaurantsOptional=restaurantRepository.findById(map.get("res_id"));
        List<UserRating> userRatingList= userRatingRepository.findByRatingId(Long.valueOf(ratingId));
        UserRating userRating=null ;
        for (UserRating ur:userRatingList){
            if(ur.getUserId()==userId) {
                userRating = ur;
                break;
            }
        }
        restaurants=restaurantsOptional.get();
            if(userRating!=null){
                usrInfo=userRatingInfo.get();
                rating=Double.parseDouble(usrInfo.getAggregate_rating());
                int numberOfVoters=Integer.parseInt(usrInfo.getVotes());
                double totalRate= rating*(numberOfVoters-1);
                ratingFromUser=Double.parseDouble(map.get("rating"));
                aggregaterating=((totalRate+ratingFromUser)/numberOfVoters)%6;
                usrInfo.setAggregate_rating(String.valueOf(aggregaterating));
                usrInfo=setColorAndRatingText(usrInfo);
                userRatingInfoRepository.save(usrInfo);
                restaurants.setUser_rating(usrInfo);
                restaurantRepository.save(restaurants);
                return usrInfo;
            }else {
                usrInfo = userRatingInfo.get();
                rating = Double.parseDouble(usrInfo.getAggregate_rating());
                int numberOfVoters=Integer.parseInt(usrInfo.getVotes());
                double totalRate= rating*(numberOfVoters);
                ratingFromUser = Double.parseDouble(map.get("rating"));
                aggregaterating = ((totalRate + ratingFromUser) / (numberOfVoters+1)) % 6;
                usrInfo.setAggregate_rating(String.valueOf(aggregaterating));
                usrInfo.setVotes(String.valueOf(numberOfVoters+1));
                usrInfo=setColorAndRatingText(usrInfo);
                userRating=new UserRating();
                userRating.setUserId(userId);
                userRating.setRatingId(Long.valueOf(ratingId));
                userRatingRepository.save(userRating);
                userRatingInfoRepository.save(usrInfo);
                restaurants.setUser_rating(usrInfo);
                restaurantRepository.save(restaurants);
                return usrInfo;
            }
    }

    @Override
    public void saveUserAndRatingIds(long uId,long rateId){
        UserRating userRating =new UserRating();
        userRating.setRatingId(rateId);
        userRating.setUserId(uId);
        userRatingRepository.save(userRating);
    }

    private UserRatingInfo setColorAndRatingText(UserRatingInfo userRatingInfo){

        double colorRate=Double.parseDouble(userRatingInfo.getAggregate_rating());
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
            userRatingInfo.setRating_color(color);
            userRatingInfo.setRating_text(ratingtext);
            return userRatingInfo;

    }
}
