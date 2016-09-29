package com.hackerearth.zomato.Services;

import com.hackerearth.zomato.Communicator.RequestInput;

/**
 * Created by santosh on 9/27/2016.
 */

public interface ILocationServiceInteractor {

    void addLocationServiceListener(ILocationResponseListener serviceListener);
    void sendParameters(RequestInput input);
}
