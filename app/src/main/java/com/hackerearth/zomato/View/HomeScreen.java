package com.hackerearth.zomato.View;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.os.ResultReceiver;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.hackerearth.zomato.App;
import com.hackerearth.zomato.Communicator.CommuncationHelper;
import com.hackerearth.zomato.Presenter.LocationPresenterImp;
import com.hackerearth.zomato.R;
import com.hackerearth.zomato.ServiceConstants.Constants;
import com.hackerearth.zomato.Services.LocationServiceInteractorImp;
import com.hackerearth.zomato.Util.PermissionUtility;
import com.hackerearth.zomato.View.ViewCallback.ILocationView;
import com.hackerearth.zomato.dto.SearchedLocation;

import android.location.Location;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.hackerearth.zomato.App.TAG;
import static com.hackerearth.zomato.App.context;

public class HomeScreen extends AppCompatActivity implements ILocationView ,TextView.OnEditorActionListener,View.OnClickListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;
    public Location mLastLocation;
    private AddressResultReceiver mResultReceiver;
    private TextView locationText;
    private EditText editTextLocation;
    private Button btnSearch;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    private LocationPresenterImp locationPresenterImp;
    public  String searchLocation;
    public  float latitude;
    public  float longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getGoogleApiClient();
        if(checkPlayServices())
        mGoogleApiClient.connect();
        initUI();
        setListeners();


    }

    private void initUI()
    {
        sharedpreferences = getSharedPreferences(Constants.ZOMATOPREFERENCE, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        //mResultReceiver =
        locationPresenterImp = new LocationPresenterImp(new LocationServiceInteractorImp(new CommuncationHelper()),this);
        locationText = (TextView)findViewById(R.id.toolbar_location_left);
        editTextLocation = (EditText)findViewById(R.id.toolbar_searchlocation);
        btnSearch = (Button) findViewById(R.id.toolbar_searchbtn);

        addFragment();
    }

    private void setListeners()
    {
        editTextLocation.setOnEditorActionListener(this);
        btnSearch.setOnClickListener(this);
    }

    private void getLocation()
    {
       locationPresenterImp.getLocationParam(locationText.getText()+"",mLastLocation);
    }

    private void addFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setHomeFragmentListener(this);
        fragmentTransaction.add(R.id.recipeContainer,homeFragment);
        fragmentTransaction.commit();
    }

    private void getGoogleApiClient()
    {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(PermissionUtility.checkPermission(this)) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                //startIntentService();
                App.getInstance().showProgressDialog("please....wait", this, false);
                FetchAddressAysncTask getAddress = new FetchAddressAysncTask(this);
                getAddress.execute(mLastLocation);
                getLocation();
                App.getInstance().dismissProgressDialog();
            }
        }
        else
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Location not enabled");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mGoogleApiClient!=null)
        {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mGoogleApiClient!=null)
        {
            mGoogleApiClient.disconnect();
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }
    public void UpdateUI(String address)
    {
        locationText.setText(address);
        editor.putString(Constants.LOCATION,locationText.getText()+"");
        editor.commit();
        App.getInstance().dismissProgressDialog();
    }

    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, mResultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLastLocation);
        startService(intent);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLocationParameterSuccess(List<SearchedLocation> searchedLocations) {

        searchLocation = searchedLocations.get(0).getLocationSuggestions().get(0).getCityName();
        latitude = searchedLocations.get(0).getLocationSuggestions().get(0).getLatitude();
        editor.putFloat(Constants.LATITUDE,latitude);
        editor.putFloat(Constants.LONGITUDE,longitude);
        editor.putString(Constants.SEARCHLOCATION,searchLocation);
        editor.putString(Constants.ENTITYID,searchedLocations.get(0).getLocationSuggestions().get(0).getEntityId()+"");
        editor.putString(Constants.ENTITYTYPE,searchedLocations.get(0).getLocationSuggestions().get(0).getEntityType());
        editor.commit();
    }

    @Override
    public void onLocationParameterFailed() {

    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void onResponseFailed() {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            return true;
        }
        return false;
    }


    class AddressResultReceiver extends ResultReceiver {
        String mAddressOutput;
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }



        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            UpdateUI(mAddressOutput);
        }
    }

    class FetchAddressAysncTask extends AsyncTask<Location,Void,String>
    {
        Activity activity;
        String city;
        public FetchAddressAysncTask(Context context)
        {
            activity = (Activity)context;
        }
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            UpdateUI(aVoid);
            App.getInstance().dismissProgressDialog();
        }

        @Override
        protected String doInBackground(Location... params) {
            App.getInstance().showProgressDialog("please....wait",activity,false);

            Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
            String errorMessage = "";
            Location location = params[0];


            List<Address> addresses = null;

            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } catch (IOException ioException) {
                // Catch network or other I/O problems.
                errorMessage = getString(R.string.service_not_available);
                Log.e(TAG, errorMessage, ioException);
            } catch (IllegalArgumentException illegalArgumentException) {
                // Catch invalid latitude or longitude values.
                errorMessage = getString(R.string.invalid_lat_long_used);
                Log.e(TAG, errorMessage + ". " +
                        "Latitude = " + location.getLatitude() +
                        ", Longitude = " +
                        location.getLongitude(), illegalArgumentException);
            }

            // Handle case where no address was found.
            if (addresses == null || addresses.size()  == 0) {
                if (errorMessage.isEmpty()) {
                    errorMessage = getString(R.string.no_address_found);
                    Log.e(TAG, errorMessage);
                }

            } else {
                Address address = addresses.get(0);
                city = address.getLocality();
                ArrayList<String> addressFragments = new ArrayList<String>();


                for(int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    addressFragments.add(address.getAddressLine(i));
                }

            }

            return city;
        }
    }

}
