package com.hackerearth.zomato.Services;

import com.android.volley.VolleyError;
import com.hackerearth.zomato.DataObjects.ErrorMessage;
import com.hackerearth.zomato.dto.Restaurant;

import java.util.List;

/**
 * Created by santosh on 9/26/2016.
 */

public interface IRestaurantResponseListener {
    void onResponseRecieved(List<Restaurant> restaurantList , ErrorMessage errMessage, int param);
    void onResponseFailed(VolleyError error);
}
