package com.hackerearth.zomato.Services;

import com.hackerearth.zomato.Communicator.RequestInput;

/**
 * Created by santosh on 9/24/2016.
 */

public interface ICategoriesServiceInteractor {

    void addCategoryServiceListener(IResponseServiceListener serviceListener);
    void sendParameters(RequestInput input);
}
