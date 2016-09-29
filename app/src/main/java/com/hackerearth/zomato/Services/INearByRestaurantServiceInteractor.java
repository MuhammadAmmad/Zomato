package com.hackerearth.zomato.Services;

import com.hackerearth.zomato.Communicator.RequestInput;

/**
 * Created by santosh on 9/28/2016.
 */

public interface INearByRestaurantServiceInteractor {

    void addNearByRestaurantServiceListener(INearByRestaurantResponseListener serviceListener);
    void sendParameters(RequestInput input);
}
