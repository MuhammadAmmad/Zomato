
package com.hackerearth.zomato.dto.RestaurantsNear;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearbyRestaurant {

    @SerializedName("restaurant")
    @Expose
    private Restaurant restaurant;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NearbyRestaurant() {
    }

    /**
     * 
     * @param restaurant
     */
    public NearbyRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * 
     * @return
     *     The restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * 
     * @param restaurant
     *     The restaurant
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
