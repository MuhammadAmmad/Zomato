package com.hackerearth.zomato.Services;

import com.android.volley.VolleyError;
import com.hackerearth.zomato.DataObjects.ErrorMessage;
import com.hackerearth.zomato.dto.Categories;

import java.util.List;

/**
 * Created by santosh on 9/24/2016.
 */

public interface IResponseServiceListener {

    void onResponseRecieved(List<Categories> string, ErrorMessage errMessage, int param);
    void onResponseFailed(VolleyError error);
}
