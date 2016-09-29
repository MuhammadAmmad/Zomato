
package com.hackerearth.zomato.dto;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("results_found")
    @Expose
    private Integer resultsFound;
    @SerializedName("results_start")
    @Expose
    private Integer resultsStart;
    @SerializedName("results_shown")
    @Expose
    private Integer resultsShown;
    @SerializedName("restaurants")
    @Expose
    private List<Restaurant_> restaurants = new ArrayList<Restaurant_>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Restaurant() {
    }

    /**
     * 
     * @param resultsShown
     * @param resultsFound
     * @param resultsStart
     * @param restaurants
     */
    public Restaurant(Integer resultsFound, Integer resultsStart, Integer resultsShown, List<Restaurant_> restaurants) {
        this.resultsFound = resultsFound;
        this.resultsStart = resultsStart;
        this.resultsShown = resultsShown;
        this.restaurants = restaurants;
    }

    /**
     * 
     * @return
     *     The resultsFound
     */
    public Integer getResultsFound() {
        return resultsFound;
    }

    /**
     * 
     * @param resultsFound
     *     The results_found
     */
    public void setResultsFound(Integer resultsFound) {
        this.resultsFound = resultsFound;
    }

    /**
     * 
     * @return
     *     The resultsStart
     */
    public Integer getResultsStart() {
        return resultsStart;
    }

    /**
     * 
     * @param resultsStart
     *     The results_start
     */
    public void setResultsStart(Integer resultsStart) {
        this.resultsStart = resultsStart;
    }

    /**
     * 
     * @return
     *     The resultsShown
     */
    public Integer getResultsShown() {
        return resultsShown;
    }

    /**
     * 
     * @param resultsShown
     *     The results_shown
     */
    public void setResultsShown(Integer resultsShown) {
        this.resultsShown = resultsShown;
    }

    /**
     * 
     * @return
     *     The restaurants
     */
    public List<Restaurant_> getRestaurants() {
        return restaurants;
    }

    /**
     * 
     * @param restaurants
     *     The restaurants
     */
    public void setRestaurants(List<Restaurant_> restaurants) {
        this.restaurants = restaurants;
    }

}
