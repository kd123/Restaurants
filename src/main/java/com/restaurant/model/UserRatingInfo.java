package com.restaurant.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Kuldeep Gupta
 */
@Entity
@Table(name = "user_rating_info")
public class UserRatingInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aggregate_rating;
    private String rating_text;
    private String rating_color;
    private String votes;
    @Column(name="res_id")
    private String resId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAggregate_rating() {
        return aggregate_rating;
    }

    public void setAggregate_rating(String aggregate_rating) {
        this.aggregate_rating = aggregate_rating;
    }

    public String getRating_text() {
        return rating_text;
    }

    public void setRating_text(String rating_text) {
        this.rating_text = rating_text;
    }

    public String getRating_color() {
        return rating_color;
    }

    public void setRating_color(String rating_color) {
        this.rating_color = rating_color;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String res_id) {
        this.resId = res_id;
    }
}
