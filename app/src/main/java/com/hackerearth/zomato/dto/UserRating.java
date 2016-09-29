
package com.hackerearth.zomato.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRating {

    @SerializedName("aggregate_rating")
    @Expose
    private String aggregateRating;
    @SerializedName("rating_text")
    @Expose
    private String ratingText;
    @SerializedName("rating_color")
    @Expose
    private String ratingColor;
    @SerializedName("votes")
    @Expose
    private String votes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserRating() {
    }

    /**
     * 
     * @param ratingColor
     * @param votes
     * @param aggregateRating
     * @param ratingText
     */
    public UserRating(String aggregateRating, String ratingText, String ratingColor, String votes) {
        this.aggregateRating = aggregateRating;
        this.ratingText = ratingText;
        this.ratingColor = ratingColor;
        this.votes = votes;
    }

    /**
     * 
     * @return
     *     The aggregateRating
     */
    public String getAggregateRating() {
        return aggregateRating;
    }

    /**
     * 
     * @param aggregateRating
     *     The aggregate_rating
     */
    public void setAggregateRating(String aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    /**
     * 
     * @return
     *     The ratingText
     */
    public String getRatingText() {
        return ratingText;
    }

    /**
     * 
     * @param ratingText
     *     The rating_text
     */
    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    /**
     * 
     * @return
     *     The ratingColor
     */
    public String getRatingColor() {
        return ratingColor;
    }

    /**
     * 
     * @param ratingColor
     *     The rating_color
     */
    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    /**
     * 
     * @return
     *     The votes
     */
    public String getVotes() {
        return votes;
    }

    /**
     * 
     * @param votes
     *     The votes
     */
    public void setVotes(String votes) {
        this.votes = votes;
    }

}
