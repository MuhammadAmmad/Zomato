package com.hackerearth.zomato.Communicator;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.hackerearth.zomato.App;

import org.json.JSONObject;

import static com.hackerearth.zomato.App.getInstance;


public class CommuncationHelper implements ICommunicationHelper  {
	


	
	@Override
	public void executePost(final RequestInput event, final ICommunicator callback){
		StringRequest jsonObjectRequest = new StringRequest(Method.GET, event.url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				callback.onResponseSuccess(response);

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		}){
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Accept", "application/json");
				headers.put("user-key", "9704deae4b1daafe8c5d8f8eb1d955ee");
				return headers;
			}

			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				return event.params;
			}
		};

		App.getInstance().addToRequestQueue(jsonObjectRequest);
	}

}
