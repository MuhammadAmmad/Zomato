package com.hackerearth.zomato.View.ViewCallback;

import com.hackerearth.zomato.dto.Category;
import com.hackerearth.zomato.dto.Restaurant;

import java.util.List;

/**
 * Created by santosh on 9/27/2016.
 */

public interface RestaurantView {
    void onRestaurantListSuccess(List<Restaurant> restaurantList);
    void onRestaurantListFailed();
    void onNetworkError();
    void showErrorMessage(String message);
    void onResponseFailed();
}
