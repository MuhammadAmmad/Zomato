package com.hackerearth.zomato.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("categories")
    @Expose
    private Categories_ categories;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Category() {
    }

    /**
     * 
     * @param categories
     */
    public Category(Categories_ categories) {
        this.categories = categories;
    }

    /**
     * 
     * @return
     *     The categories
     */
    public Categories_ getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    public void setCategories(Categories_ categories) {
        this.categories = categories;
    }

}
