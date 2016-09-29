
package com.hackerearth.zomato.dto.RestaurantsNear;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantsNearBy {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("popularity")
    @Expose
    private Popularity popularity;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("nearby_restaurants")
    @Expose
    private List<NearbyRestaurant> nearbyRestaurants = new ArrayList<NearbyRestaurant>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public RestaurantsNearBy() {
    }

    /**
     * 
     * @param nearbyRestaurants
     * @param location
     * @param link
     * @param popularity
     */
    public RestaurantsNearBy(Location location, Popularity popularity, String link, List<NearbyRestaurant> nearbyRestaurants) {
        this.location = location;
        this.popularity = popularity;
        this.link = link;
        this.nearbyRestaurants = nearbyRestaurants;
    }

    /**
     * 
     * @return
     *     The location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *     The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * 
     * @return
     *     The popularity
     */
    public Popularity getPopularity() {
        return popularity;
    }

    /**
     * 
     * @param popularity
     *     The popularity
     */
    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    /**
     * 
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The nearbyRestaurants
     */
    public List<NearbyRestaurant> getNearbyRestaurants() {
        return nearbyRestaurants;
    }

    /**
     * 
     * @param nearbyRestaurants
     *     The nearby_restaurants
     */
    public void setNearbyRestaurants(List<NearbyRestaurant> nearbyRestaurants) {
        this.nearbyRestaurants = nearbyRestaurants;
    }

}
