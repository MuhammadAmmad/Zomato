package com.hackerearth.zomato.Communicator;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface ICommunicator {
	
	public void onResponseSuccess(String response);
	public void onResponseFailure(VolleyError error);
	
	
	

}
