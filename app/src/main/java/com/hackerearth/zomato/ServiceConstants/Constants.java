package com.hackerearth.zomato.ServiceConstants;

/**
 * Created by santosh on 9/24/2016.
 */

public class Constants {

    public final static String ENDPOINT ="https://developers.zomato.com/api/v2.1/categories";
    public final static String LOCATIONAPI="https://developers.zomato.com/api/v2.1/locations?";
    public final static String SEARCHAPI ="https://developers.zomato.com/api/v2.1/search";
    public final static String NEARBYAPI ="https://developers.zomato.com/api/v2.1/geocode?";

    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME =
            "com.google.android.gms.location.com.hackerearth.zomato";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME +
            ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";

    public  static final String ZOMATOPREFERENCE ="zomatopreference";

    //Location Api
    public  static final String LOCATION ="location";
    public  static final String LATITUDE ="latitude";
    public  static final String LONGITUDE ="longitude";
    public  static final String SEARCHLOCATION ="searchlocation";
    public  static final String ENTITYID ="entity-id";
    public  static final String ENTITYTYPE ="entity_type";
    public  static final String CATEGORY ="category";
    public  static final String CATEGORYID ="category";
    public  static final String SORT ="sort";
    public  static final String ORDER ="order";
    public  static final String LOCATIONQUERY ="q";





}
