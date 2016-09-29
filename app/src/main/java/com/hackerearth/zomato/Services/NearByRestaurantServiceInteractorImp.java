package com.hackerearth.zomato.Services;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hackerearth.zomato.Communicator.ICommunicationHelper;
import com.hackerearth.zomato.Communicator.ICommunicator;
import com.hackerearth.zomato.Communicator.RequestInput;
import com.hackerearth.zomato.dto.Restaurant__;
import com.hackerearth.zomato.dto.RestaurantsNear.RestaurantsNearBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by santosh on 9/28/2016.
 */

public class NearByRestaurantServiceInteractorImp implements INearByRestaurantServiceInteractor {


    private INearByRestaurantResponseListener serviceListener;
    private ICommunicationHelper communicationHelper;
    public NearByRestaurantServiceInteractorImp(ICommunicationHelper communicationHelper) {

        this.communicationHelper = communicationHelper;
    }

    @Override
    public void addNearByRestaurantServiceListener(INearByRestaurantResponseListener serviceListener) {
        this.serviceListener = serviceListener;
    }

    @Override
    public void sendParameters(RequestInput input) {

        communicationHelper.executePost(input, new ICommunicator() {
            @Override
            public void onResponseSuccess(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                Gson gson = gsonBuilder.create();

                List<RestaurantsNearBy> listRestaurant = new ArrayList<RestaurantsNearBy>();
                listRestaurant = Arrays.asList(gson.fromJson(response, RestaurantsNearBy.class));
                serviceListener.onResponseRecieved(listRestaurant,null,0);
            }

            @Override
            public void onResponseFailure(VolleyError error) {

            }
        });
    }

}
