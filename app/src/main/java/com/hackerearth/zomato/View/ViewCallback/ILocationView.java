package com.hackerearth.zomato.View.ViewCallback;

import com.hackerearth.zomato.dto.Restaurant;
import com.hackerearth.zomato.dto.SearchedLocation;

import java.util.List;

/**
 * Created by santosh on 9/27/2016.
 */

public interface ILocationView {

    void onLocationParameterSuccess(List<SearchedLocation> restaurantList);
    void onLocationParameterFailed();
    void onNetworkError();
    void showErrorMessage(String message);
    void onResponseFailed();
}
