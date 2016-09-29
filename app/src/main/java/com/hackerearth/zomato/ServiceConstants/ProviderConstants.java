package com.hackerearth.zomato.ServiceConstants;

import android.content.ContentResolver;
import android.net.Uri;

/**
 * Created by santosh on 9/28/2016.
 */

public class ProviderConstants {

    public static final int TODOS = 10;
    public static final int TODO_ID = 20;

    public static final String AUTHORITY = "com.hackerearth.zomato";

    public static final String BASE_PATH = "restaurant";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/restaurants";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/restaurant";

}
