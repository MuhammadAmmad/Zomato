package com.hackerearth.zomato.Providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.hackerearth.zomato.ServiceConstants.DBConstants;
import com.hackerearth.zomato.ServiceConstants.ProviderConstants;
import com.hackerearth.zomato.dto.Location;
import com.hackerearth.zomato.dto.Restaurant;
import com.hackerearth.zomato.dto.Restaurant_;
import com.hackerearth.zomato.dto.Restaurant__;
import com.hackerearth.zomato.dto.UserRating;

import java.util.ArrayList;
import java.util.List;

import static com.hackerearth.zomato.App.TAG;

/**
 * Created by santosh on 9/28/2016.
 */

public class ContentProviderHelper {

    private String projection[] = {DBConstants.COLUMN_RESTAURANTNAME,DBConstants.COLUMN_FEATUREDIMAGEURI,DBConstants.COLUMN_CUISINES,DBConstants.COLUMN_USERRATING};

    public void insertIntoDB(Context context , List<Restaurant_> tList)
    {
        for(int i =0;i<tList.size();i++) {

            ContentValues values = new ContentValues();

            values.put(DBConstants.COLUMN_RESTAURANTNAME, tList.get(i).getRestaurant().getName());
            values.put(DBConstants.COLUMN_FEATUREDIMAGEURI, tList.get(i).getRestaurant().getFeaturedImage());
            values.put(DBConstants.COLUMN_CUISINES, tList.get(i).getRestaurant().getCuisines());
            values.put(DBConstants.COLUMN_RATINGCOLOR, tList.get(i).getRestaurant().getUserRating().getRatingColor());
            values.put(DBConstants.COLUMN_AGGREGATERATING, tList.get(i).getRestaurant().getUserRating().getAggregateRating());
            values.put(DBConstants.COLUMN_ADDRESS, tList.get(i).getRestaurant().getLocation().getAddress());
            values.put(DBConstants.COLUMN_RESTAURANTID, tList.get(i).getRestaurant().getId());
            values.put(DBConstants.COLUMN_USERRATING, tList.get(i).getRestaurant().getUserRating().getRatingText());
            context.getContentResolver().insert(ProviderConstants.CONTENT_URI, values);

        }
        Log.i("db operation", "Inserted");

    }


    public List<Restaurant_> getDataFromDB(Context context)
    {
        List<Restaurant_>  listRestaurant_ = new ArrayList<>();
        Cursor cur = context.getContentResolver().query(ProviderConstants.CONTENT_URI,
                null, null, null, null);

        if (cur.getCount() > 0) {
            Log.i(TAG, "Showing values.....");
            while (cur.moveToNext()) {
                Restaurant_ restaurant_ = new Restaurant_();
                    Restaurant__ restaurant__ = new Restaurant__();
                restaurant__.setName(cur.getString(cur.getColumnIndex(DBConstants.COLUMN_RESTAURANTNAME)));
                restaurant__.setFeaturedImage(cur.getString(cur.getColumnIndex(DBConstants.COLUMN_FEATUREDIMAGEURI)));
                restaurant__.setCuisines(cur.getString(cur.getColumnIndex(DBConstants.COLUMN_CUISINES)));
                UserRating userRating = new UserRating();
                userRating.setRatingText(cur.getString(cur.getColumnIndex(DBConstants.COLUMN_USERRATING)));
                userRating.setAggregateRating(cur.getString(cur.getColumnIndex(DBConstants.COLUMN_AGGREGATERATING)));
                userRating.setRatingColor(cur.getString(cur.getColumnIndex(DBConstants.COLUMN_RATINGCOLOR)));
                restaurant__.setUserRating(userRating);
                Location  location = new Location();
                location.setAddress(cur.getString(cur.getColumnIndex(DBConstants.COLUMN_ADDRESS)));
                restaurant__.setLocation(location);
                restaurant__.setId(cur.getString(cur.getColumnIndex(DBConstants.COLUMN_RESTAURANTID)));
                restaurant_.setRestaurant(restaurant__);
                listRestaurant_.add(restaurant_);
            }

        } else {
            return listRestaurant_;
        }
        return listRestaurant_;
    }

}
