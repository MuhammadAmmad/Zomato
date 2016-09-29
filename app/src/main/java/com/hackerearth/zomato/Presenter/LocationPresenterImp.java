package com.hackerearth.zomato.Presenter;

import android.location.Location;

import com.android.volley.VolleyError;
import com.hackerearth.zomato.Communicator.ICommunicationHelper;
import com.hackerearth.zomato.Communicator.RequestInput;
import com.hackerearth.zomato.DataObjects.ErrorMessage;
import com.hackerearth.zomato.ServiceConstants.Constants;
import com.hackerearth.zomato.Services.ILocationResponseListener;
import com.hackerearth.zomato.Services.ILocationServiceInteractor;
import com.hackerearth.zomato.View.ViewCallback.ILocationView;
import com.hackerearth.zomato.dto.SearchedLocation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by santosh on 9/27/2016.
 */

public class LocationPresenterImp implements  ILocationPresenter,ILocationResponseListener{

    private ILocationServiceInteractor serviceInteractor;
    private ILocationView locationView;
    public LocationPresenterImp(ILocationServiceInteractor serviceInteractor , ILocationView locationView)
    {
        this.serviceInteractor= serviceInteractor;
        this.locationView = locationView;
    }

    @Override
    public void getLocationParam(String locationQuery, Location location) {

        RequestInput inputParams = new RequestInput();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Accept","application/json");
        inputParams.headers = headers;
        inputParams.url = Constants.LOCATIONAPI;
        HashMap<String, String> parameter = new HashMap<String, String>();
        parameter.put("lat",location.getLatitude()+"");
        parameter.put("long",location.getLongitude()+"");
        parameter.put("locatinoquery",locationQuery);

        inputParams.params = parameter;
        serviceInteractor.addLocationServiceListener(this);
        serviceInteractor.sendParameters(inputParams);

    }

    @Override
    public void onResponseRecieved(List<SearchedLocation> locationParams, ErrorMessage errMessage, int param) {
        locationView.onLocationParameterSuccess(locationParams);
    }

    @Override
    public void onResponseFailed(VolleyError error) {

    }

}
