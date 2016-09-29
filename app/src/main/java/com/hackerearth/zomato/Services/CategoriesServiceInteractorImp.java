package com.hackerearth.zomato.Services;

import android.bluetooth.BluetoothProfile;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hackerearth.zomato.Communicator.ICommunicationHelper;
import com.hackerearth.zomato.Communicator.ICommunicator;
import com.hackerearth.zomato.Communicator.RequestInput;
import com.hackerearth.zomato.dto.Categories;
import com.hackerearth.zomato.dto.Category;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by santosh on 9/24/2016.
 */

public class CategoriesServiceInteractorImp implements ICategoriesServiceInteractor {

    private IResponseServiceListener serviceListener;
    private final ICommunicationHelper communicationHelper;
    public CategoriesServiceInteractorImp(ICommunicationHelper communicationHelper)
    {
            this.communicationHelper = communicationHelper;
    }
    @Override
    public void addCategoryServiceListener(IResponseServiceListener serviceListener) {
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

                List<Categories> listCategories = new ArrayList<Categories>();
                listCategories = Arrays.asList(gson.fromJson(response, Categories.class));
                serviceListener.onResponseRecieved(listCategories,null,0);
            }

            @Override
            public void onResponseFailure(VolleyError error) {

            }
        });
    }
}
