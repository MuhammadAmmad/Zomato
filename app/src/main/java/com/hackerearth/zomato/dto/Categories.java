package com.hackerearth.zomato.dto;





 import java.util.ArrayList;
 import java.util.List;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;


 public class Categories {

 @SerializedName("categories")
 @Expose
 private List<Category> categories = new ArrayList<Category>();

 /**
  * No args constructor for use in serialization
 *
 */
public Categories() {
        }

/**
 *
 * @param categories
 */
public Categories(List<Category> categories) {
        this.categories = categories;
        }

/**
 *
 * @return
 *     The categories
 */
public List<Category> getCategories() {
        return categories;
        }

/**
 *
 * @param categories
 *     The categories
 */
public void setCategories(List<Category> categories) {
        this.categories = categories;
        }

}
