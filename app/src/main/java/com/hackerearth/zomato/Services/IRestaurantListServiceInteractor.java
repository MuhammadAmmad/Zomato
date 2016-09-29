package com.hackerearth.zomato.Services;

import com.hackerearth.zomato.Communicator.RequestInput;

/**
 * Created by santosh on 9/28/2016.
 */

public interface IRestaurantListServiceInteractor {
    void addRestaurantServiceListener(IRestaurantResponseListener serviceListener);
    void sendParameters(RequestInput input);
}
