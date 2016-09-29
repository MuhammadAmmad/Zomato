package com.hackerearth.zomato.View.ViewCallback;

import com.hackerearth.zomato.dto.Restaurant__;
import com.hackerearth.zomato.dto.RestaurantsNear.RestaurantsNearBy;

import java.util.List;

/**
 * Created by santosh on 9/28/2016.
 */

public interface INearByRestaurantView {

    void onNearByRestaurantListSuccess(List<RestaurantsNearBy> nearbyRestaurantList);
    void onNearByRestaurantListFailed();
    void onNetworkError();
    void showErrorMessage(String message);
    void onResponseFailed();
}
