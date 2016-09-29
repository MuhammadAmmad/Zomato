package com.hackerearth.zomato.Presenter;

import android.net.Uri;
import android.util.Log;

import com.android.volley.VolleyError;
import com.hackerearth.zomato.Communicator.RequestInput;
import com.hackerearth.zomato.DataObjects.ErrorMessage;
import com.hackerearth.zomato.ServiceConstants.Constants;
import com.hackerearth.zomato.Services.INearByRestaurantResponseListener;

import com.hackerearth.zomato.Services.INearByRestaurantServiceInteractor;
import com.hackerearth.zomato.View.ViewCallback.INearByRestaurantView;
import com.hackerearth.zomato.dto.Restaurant__;
import com.hackerearth.zomato.dto.RestaurantsNear.RestaurantsNearBy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by santosh on 9/28/2016.
 */

public class NearByRestaurantPresenterImp implements INearByRestaurantPresenter,INearByRestaurantResponseListener{


    private INearByRestaurantServiceInteractor serviceInteractor;
    private INearByRestaurantView nearbyRestaurantView;
    public NearByRestaurantPresenterImp(INearByRestaurantServiceInteractor serviceInteractor, INearByRestaurantView nearbyRestaurantView) {
        this.serviceInteractor = serviceInteractor;
        this.nearbyRestaurantView = nearbyRestaurantView;
    }

    @Override
    public void getNearByRestaurantList(Map<String,String> mapParameter) {

        RequestInput inputParams = new RequestInput();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept","application/json");
        inputParams.headers = headers;

        inputParams.url = Constants.NEARBYAPI;
        Uri.Builder builder  = Uri.parse(inputParams.url).buildUpon();
        //builder.appendPath(inputParams.url);
        for (Map.Entry<String, String> entry : mapParameter.entrySet())
        {
            builder.appendQueryParameter(entry.getKey(),entry.getValue());

        }
        try
        {
            inputParams.url = URLDecoder.decode(builder.build().toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException ex)
        {
            Log.i("url exception",ex.getMessage());
        }
        serviceInteractor.addNearByRestaurantServiceListener(this);
        serviceInteractor.sendParameters(inputParams);
    }




    @Override
    public void onResponseRecieved(List<RestaurantsNearBy> nearbyRestaurantList, ErrorMessage errMessage, int param) {
        nearbyRestaurantView.onNearByRestaurantListSuccess(nearbyRestaurantList);

    }

    @Override
    public void onResponseFailed(VolleyError error) {

    }
}
