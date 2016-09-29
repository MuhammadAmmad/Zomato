package com.hackerearth.zomato;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
/**
 * Created by santosh on 9/24/2016.
 */
public class App extends Application {
    public  static  Context context;
    public static final String TAG = App.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private ProgressDialog mProgressDialog;
    public ArrayList<Activity> activities = new ArrayList<Activity>();
    public Activity activity ;
    public ArrayList<Activity> scheduleActivities = new ArrayList<Activity>();
    public ArrayList<Activity> classes = new ArrayList<Activity>();
    public ArrayList<Activity> programs = new ArrayList<Activity>();

    private static  App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = this;
        App.mInstance = this;

    }

    public synchronized static  App getInstance() {
        if (mInstance==null) {
            mInstance = new App();

        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(this);
        }

        return mRequestQueue;
    }



    public <T> void addToRequestQueue(Request<T> req, final String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        req.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    /**
     * The purpose of putting progress dialog is to utilize singleton behaviour of Application Class and Context
     * @param title
     * @param activity
     * @param cancaleble
     * @return
     */
    public ProgressDialog showProgressDialog(String title, Activity activity,
                                             boolean cancaleble) {
        //
        try {
            if (mProgressDialog == null&&!activity.isFinishing()) {
                ProgressDialog dialog = new ProgressDialog(activity);
                dialog.setMessage(title);
                dialog.setIndeterminate(true);
                dialog.setCancelable(cancaleble);
                mProgressDialog = dialog;
            }
            mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mProgressDialog;
    }

    public ProgressDialog showProgressDialogContext(String title, Context activity,
                                                    boolean cancaleble) {
        //
        try {
            if (mProgressDialog == null) {
                ProgressDialog dialog = new ProgressDialog(activity);
                dialog.setMessage(title);
                dialog.setIndeterminate(true);
                dialog.setCancelable(cancaleble);
                mProgressDialog = dialog;
            }
            mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mProgressDialog;
    }


    public void dismissProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}