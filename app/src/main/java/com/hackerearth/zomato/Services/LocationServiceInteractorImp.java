package com.hackerearth.zomato.Services;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hackerearth.zomato.Communicator.ICommunicationHelper;
import com.hackerearth.zomato.Communicator.ICommunicator;
import com.hackerearth.zomato.Communicator.RequestInput;
import com.hackerearth.zomato.DataObjects.ErrorMessage;
import com.hackerearth.zomato.dto.Restaurant;
import com.hackerearth.zomato.dto.SearchedLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by santosh on 9/27/2016.
 */

public class LocationServiceInteractorImp implements  ILocationServiceInteractor {

    private  ILocationResponseListener serviceListener;
    private ICommunicationHelper communicationHelper;

    public LocationServiceInteractorImp(ICommunicationHelper communicationHelper)
    {
        this.communicationHelper = communicationHelper;
    }

    @Override
    public void addLocationServiceListener(ILocationResponseListener serviceListener) {

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

                List<SearchedLocation> searchedLocations = new ArrayList<SearchedLocation>();
                searchedLocations = Arrays.asList(gson.fromJson(response, SearchedLocation.class));
                serviceListener.onResponseRecieved(searchedLocations,null,0);
            }

            @Override
            public void onResponseFailure(VolleyError error) {

            }
        });
    }
}
