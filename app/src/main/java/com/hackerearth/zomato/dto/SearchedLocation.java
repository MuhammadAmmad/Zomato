
package com.hackerearth.zomato.dto;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchedLocation {

    @SerializedName("location_suggestions")
    @Expose
    private List<LocationSuggestion> locationSuggestions = new ArrayList<LocationSuggestion>();
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("has_more")
    @Expose
    private long hasMore;
    @SerializedName("has_total")
    @Expose
    private long hasTotal;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SearchedLocation() {
    }

    /**
     * 
     * @param hasMore
     * @param status
     * @param locationSuggestions
     * @param hasTotal
     */
    public SearchedLocation(List<LocationSuggestion> locationSuggestions, String status, long hasMore, long hasTotal) {
        this.locationSuggestions = locationSuggestions;
        this.status = status;
        this.hasMore = hasMore;
        this.hasTotal = hasTotal;
    }

    /**
     * 
     * @return
     *     The locationSuggestions
     */
    public List<LocationSuggestion> getLocationSuggestions() {
        return locationSuggestions;
    }

    /**
     * 
     * @param locationSuggestions
     *     The location_suggestions
     */
    public void setLocationSuggestions(List<LocationSuggestion> locationSuggestions) {
        this.locationSuggestions = locationSuggestions;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The hasMore
     */
    public long getHasMore() {
        return hasMore;
    }

    /**
     * 
     * @param hasMore
     *     The has_more
     */
    public void setHasMore(long hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * 
     * @return
     *     The hasTotal
     */
    public long getHasTotal() {
        return hasTotal;
    }

    /**
     * 
     * @param hasTotal
     *     The has_total
     */
    public void setHasTotal(long hasTotal) {
        this.hasTotal = hasTotal;
    }

}
