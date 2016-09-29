package com.hackerearth.zomato.Services;

import com.android.volley.VolleyError;
import com.hackerearth.zomato.DataObjects.ErrorMessage;
import com.hackerearth.zomato.dto.Restaurant;
import com.hackerearth.zomato.dto.SearchedLocation;

import java.util.List;

/**
 * Created by santosh on 9/27/2016.
 */

public interface ILocationResponseListener {
    void onResponseRecieved(List<SearchedLocation> locationParams , ErrorMessage errMessage, int param);
    void onResponseFailed(VolleyError error);
}
