package com.restaurant.model;

/**
 * @author Kuldeep Gupta
 */

public class Restaurants {
    private String id;
    private String name;
    private String url;
    private String thumb;
    private String cuisines;
    private String average_cost_for_two;
    private UserRatingInfo user_rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getAverage_cost_for_two() {
        return average_cost_for_two;
    }

    public void setAverage_cost_for_two(String average_cost_for_two) {
        this.average_cost_for_two = average_cost_for_two;
    }

    public UserRatingInfo getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(UserRatingInfo user_rating) {
        this.user_rating = user_rating;
    }
}
