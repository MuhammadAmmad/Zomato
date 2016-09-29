package com.hackerearth.zomato.Communicator;

import com.hackerearth.zomato.ServiceConstants.Constants;

import java.util.HashMap;

/**
 * Created by santosh on 9/24/2016.
 */

public class RequestInput {
    public String url = Constants.ENDPOINT;
    public HashMap<String, String>  headers = new HashMap<String, String>();
    public String body;
    public HashMap<String, String>  params = new HashMap<String, String>();
}
