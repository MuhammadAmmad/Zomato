
package com.hackerearth.zomato.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant_ {

    @SerializedName("restaurant")
    @Expose
    private Restaurant__ restaurant;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Restaurant_() {
    }

    /**
     * 
     * @param restaurant
     */
    public Restaurant_(Restaurant__ restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * 
     * @return
     *     The restaurant
     */
    public Restaurant__ getRestaurant() {
        return restaurant;
    }

    /**
     * 
     * @param restaurant
     *     The restaurant
     */
    public void setRestaurant(Restaurant__ restaurant) {
        this.restaurant = restaurant;
    }

}
