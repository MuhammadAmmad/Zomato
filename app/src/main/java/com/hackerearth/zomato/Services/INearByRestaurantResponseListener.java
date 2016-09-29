package com.hackerearth.zomato.Services;

import com.android.volley.VolleyError;
import com.hackerearth.zomato.DataObjects.ErrorMessage;
import com.hackerearth.zomato.dto.Restaurant__;
import com.hackerearth.zomato.dto.RestaurantsNear.RestaurantsNearBy;

import java.util.List;

/**
 * Created by santosh on 9/28/2016.
 */

public interface INearByRestaurantResponseListener {
    void onResponseRecieved(List<RestaurantsNearBy> nearbyRestaurantList , ErrorMessage errMessage, int param);
    void onResponseFailed(VolleyError error);
}
