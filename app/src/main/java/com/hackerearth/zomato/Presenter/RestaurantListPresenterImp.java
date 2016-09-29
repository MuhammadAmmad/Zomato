package com.hackerearth.zomato.Presenter;

import android.net.Uri;
import android.util.Log;

import com.android.volley.VolleyError;
import com.hackerearth.zomato.Communicator.RequestInput;
import com.hackerearth.zomato.DataObjects.ErrorMessage;
import com.hackerearth.zomato.ServiceConstants.Constants;
import com.hackerearth.zomato.Services.IRestaurantListServiceInteractor;
import com.hackerearth.zomato.Services.IRestaurantResponseListener;
import com.hackerearth.zomato.View.ViewCallback.RestaurantView;
import com.hackerearth.zomato.dto.Restaurant;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by santosh on 9/26/2016.
 */

public class RestaurantListPresenterImp implements IRestaurantResponseListener,IRestaurantListPresenter {

    private IRestaurantListServiceInteractor serviceInteractor;
    private RestaurantView restaurantView;
    public RestaurantListPresenterImp(IRestaurantListServiceInteractor serviceInteractor, RestaurantView restaurantView) {
        this.serviceInteractor = serviceInteractor;
        this.restaurantView = restaurantView;
    }

    @Override
    public void getRestaurantList(Map<String,String> mapParameter) {

        RequestInput inputParams = new RequestInput();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept","application/json");
        inputParams.headers = headers;

        inputParams.url = Constants.SEARCHAPI;
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
        serviceInteractor.addRestaurantServiceListener(this);
        serviceInteractor.sendParameters(inputParams);
    }



    @Override
    public void onResponseRecieved(List<Restaurant> restaurantList, ErrorMessage errMessage, int param) {

        restaurantView.onRestaurantListSuccess(restaurantList);
    }

    @Override
    public void onResponseFailed(VolleyError error) {

    }
}
