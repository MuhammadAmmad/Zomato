package com.hackerearth.zomato.View;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hackerearth.zomato.App;
import com.hackerearth.zomato.Communicator.CommuncationHelper;
import com.hackerearth.zomato.Presenter.RestaurantListPresenterImp;
import com.hackerearth.zomato.Providers.ContentProviderHelper;
import com.hackerearth.zomato.R;
import com.hackerearth.zomato.ServiceConstants.Constants;
import com.hackerearth.zomato.Services.RestaurantListServiceInteractorImp;
import com.hackerearth.zomato.View.Adapters.DividerItemDecoration;
import com.hackerearth.zomato.View.Adapters.NearByRestaurantListAdapter;
import com.hackerearth.zomato.View.Adapters.RestaurantListAdapter;
import com.hackerearth.zomato.View.ViewCallback.RestaurantView;
import android.location.Location;
import com.hackerearth.zomato.dto.Restaurant;
import com.hackerearth.zomato.dto.Restaurant_;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.hackerearth.zomato.App.context;

public class RestaurantListActivity extends AppCompatActivity implements View.OnClickListener,TextView.OnEditorActionListener,RestaurantView{
    private RecyclerView recyclerViewRestaurant;
    private RestaurantListAdapter restaurantListAdapter;
    private RestaurantListPresenterImp restaurantListPresenterImp;
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;
    private TextView locationText;
    private EditText searchLocationText;
    private Button searchLocationButton;
    private SimpleCursorAdapter adapter;
    private ContentProviderHelper contentProviderHelper;
    private Button btnBrowsePlaces;
    private Location lastLocation;
    Map<String,String > mapParameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUI();
        setListeners();
        searchWithQueryLocation();
    }

    private  void initUI(){
        sharedpreferences = getSharedPreferences(Constants.ZOMATOPREFERENCE, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        searchLocationButton = (Button) findViewById(R.id.toolbar_searchbtn);

        contentProviderHelper = new ContentProviderHelper();
        recyclerViewRestaurant =(RecyclerView)findViewById(R.id.recycler_view_restaurantlist);
        searchLocationText =(EditText)findViewById(R.id.toolbar_searchlocation);
        searchLocationText.setVisibility(View.VISIBLE);
        searchLocationButton.setVisibility(View.VISIBLE);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        locationText =(TextView)findViewById(R.id.toolbar_location_left);
        recyclerViewRestaurant.setLayoutManager(mLayoutManager);
        recyclerViewRestaurant.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        restaurantListPresenterImp = new RestaurantListPresenterImp(new RestaurantListServiceInteractorImp(new CommuncationHelper()),this);
    }

    private void setListeners()
    {
        searchLocationText.setOnEditorActionListener(this);
        searchLocationButton.setOnClickListener(this);

    }
    private  void searchWithQueryLocation()
    {
        mapParameter = new LinkedHashMap<>();
        Intent intent = getIntent();
        String categoryId = intent.getExtras().getString(Constants.CATEGORYID);
        lastLocation = intent.getExtras().getParcelable("location");
        locationText.setText(sharedpreferences.getString(Constants.LOCATION,null));
        mapParameter.put(Constants.ENTITYID,sharedpreferences.getString(Constants.ENTITYID,null));
        mapParameter.put(Constants.ENTITYTYPE,sharedpreferences.getString(Constants.ENTITYTYPE,null));
        mapParameter.put(Constants.CATEGORY,sharedpreferences.getString(Constants.CATEGORYID,null));
        mapParameter.put(Constants.SORT,"rating");
        mapParameter.put(Constants.ORDER,"dsc");
        mapParameter.put(Constants.LOCATIONQUERY,sharedpreferences.getString(Constants.LOCATION,null));
        mapParameter.put(Constants.LATITUDE,lastLocation.getLatitude()+"");
        mapParameter.put(Constants.LONGITUDE,lastLocation.getLongitude()+"");
//        checkDB();
        App.getInstance().showProgressDialog("Loading.....",this,false);
        restaurantListPresenterImp.getRestaurantList(mapParameter);

    }

    @Override
    public void onRestaurantListSuccess(List<Restaurant> restaurantList) {

        if(!restaurantList.isEmpty()) {
            List<Restaurant_> restaurantsList = restaurantList.get(0).getRestaurants();
            contentProviderHelper.insertIntoDB(this, restaurantsList);
            restaurantListAdapter = new RestaurantListAdapter(this, restaurantsList);

            recyclerViewRestaurant.setAdapter(restaurantListAdapter);

            App.getInstance().dismissProgressDialog();
        }else
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("No Restaurant Found");
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
    public void onRestaurantListFailed() {
        App.getInstance().dismissProgressDialog();

    }

    @Override
    public void onNetworkError() {
        App.getInstance().dismissProgressDialog();

    }

    @Override
    public void showErrorMessage(String message) {
        App.getInstance().dismissProgressDialog();

    }

    @Override
    public void onResponseFailed() {
        App.getInstance().dismissProgressDialog();

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            // performSearch();
            editor.putString(Constants.LOCATIONQUERY,searchLocationText.getText()+"");
            locationText.setText(searchLocationText.getText()+"");
            mapParameter.clear();

            searchQuery();
            return true;
        }
        return false;
    }
    public void checkDB()
    {

      new DBAsyncTask(this).execute();

    }

    public void UpdateUI(List<Restaurant_> restaurant_list)
    {
        if(restaurant_list.size()>0) {
            restaurantListAdapter = new RestaurantListAdapter(this,restaurant_list);

            recyclerViewRestaurant.setAdapter(restaurantListAdapter);

        }
        else
        {

            App.getInstance().showProgressDialog("Loading.....",this,false);
            restaurantListPresenterImp.getRestaurantList(mapParameter);
        }
    }
    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.toolbar_searchbtn) {
            editor.putString(Constants.LOCATIONQUERY, searchLocationText.getText() + "");
            locationText.setText(searchLocationText.getText() + "");
            mapParameter.clear();
            searchQuery();
        }

    }

    public void searchQuery()
    {
        mapParameter.put("q",searchLocationText.getText()+"");
        App.getInstance().showProgressDialog("Loading.....",this,false);
        restaurantListPresenterImp.getRestaurantList(mapParameter);
    }

    public class DBAsyncTask extends AsyncTask<Void,Void ,List<Restaurant_>>{
        Context context;
        public  DBAsyncTask(Context context)
        {
            this.context = context;
        }
        @Override
        protected void onPostExecute(List<Restaurant_> aVoid) {
            super.onPostExecute(aVoid);

            UpdateUI(aVoid);

        }

        @Override
        protected List<Restaurant_> doInBackground(Void... params) {
            List<Restaurant_> restaurant_list = contentProviderHelper.getDataFromDB(context);
            return restaurant_list;
        }
    }
}
