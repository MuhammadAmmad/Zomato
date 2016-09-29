package com.hackerearth.zomato.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hackerearth.zomato.ServiceConstants.DBConstants;

import static com.hackerearth.zomato.ServiceConstants.DBConstants.COLUMN_ADDRESS;
import static com.hackerearth.zomato.ServiceConstants.DBConstants.COLUMN_AGGREGATERATING;
import static com.hackerearth.zomato.ServiceConstants.DBConstants.COLUMN_CUISINES;
import static com.hackerearth.zomato.ServiceConstants.DBConstants.COLUMN_FEATUREDIMAGEURI;
import static com.hackerearth.zomato.ServiceConstants.DBConstants.COLUMN_ID;
import static com.hackerearth.zomato.ServiceConstants.DBConstants.COLUMN_RATINGCOLOR;
import static com.hackerearth.zomato.ServiceConstants.DBConstants.COLUMN_RESTAURANTID;
import static com.hackerearth.zomato.ServiceConstants.DBConstants.COLUMN_RESTAURANTNAME;
import static com.hackerearth.zomato.ServiceConstants.DBConstants.COLUMN_USERRATING;
import static com.hackerearth.zomato.ServiceConstants.DBConstants.TABLE_RESTAURANTLIST;

/**
 * Created by santosh on 9/28/2016.
 */



public class RestaurantSqliteHelper extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 3;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_RESTAURANTLIST + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_RESTAURANTNAME + " text not null, "
            + COLUMN_FEATUREDIMAGEURI + " text not null,"
            + COLUMN_CUISINES + " text not null,"
            + COLUMN_RATINGCOLOR + " text not null,"
            + COLUMN_ADDRESS + " text not null,"
            + COLUMN_AGGREGATERATING + " text not null,"
            + COLUMN_RESTAURANTID + " text not null,"
            + COLUMN_USERRATING + " text not null"+");";

    public RestaurantSqliteHelper(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RestaurantSqliteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTLIST);
        onCreate(db);
    }

}