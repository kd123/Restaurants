package com.restaurant.model;

import javax.persistence.*;

/**
 * @author Kuldeep Gupta
 */
@Embeddable
public class Address {

    private String address;
    private String locality;
    private String city;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
