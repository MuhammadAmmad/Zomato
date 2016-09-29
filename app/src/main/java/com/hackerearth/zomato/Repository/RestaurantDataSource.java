package com.hackerearth.zomato.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by santosh on 9/28/2016.
 */

public class RestaurantDataSource {
    // Database fields
    private SQLiteDatabase database;
    private RestaurantSqliteHelper dbHelper;


    public RestaurantDataSource(Context context) {
        dbHelper = new RestaurantSqliteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

   /* public void insertProfileDetails(ProfileDetail profileDetail) {
        ContentValues valuesProfile = new ContentValues();
        valuesProfile.put(RestaurantSqliteHelper.COLUMN_RESTAURANTNAME, profileDetail.getFirstName());
        valuesProfile.put(RestaurantSqliteHelper.COLUMN_FEATUREDIMAGEURI, profileDetail.getLastName());
        valuesProfile.put(RestaurantSqliteHelper.COLUMN_CUISINES, profileDetail.getEmailId());
        valuesProfile.put(RestaurantSqliteHelper.COLUMN_USERRATING, profileDetail.getFacebookId());
        valuesProfile.put(RestaurantSqliteHelper.COLUMN_PASSWORD, profileDetail.getPassword());

        long insertId = database.insert(RestaurantSqliteHelper.TABLE_RESTAURANTLIST, null,
                valuesProfile);


    }*/
}