package com.hackerearth.zomato.View;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hackerearth.zomato.App;
import com.hackerearth.zomato.Communicator.CommuncationHelper;
import com.hackerearth.zomato.Presenter.NearByRestaurantPresenterImp;
import com.hackerearth.zomato.R;
import com.hackerearth.zomato.Services.NearByRestaurantServiceInteractorImp;
import com.hackerearth.zomato.View.Adapters.DividerItemDecoration;
import com.hackerearth.zomato.View.Adapters.NearByRestaurantListAdapter;
import com.hackerearth.zomato.View.ViewCallback.INearByRestaurantView;
import com.hackerearth.zomato.dto.Restaurant__;
import com.hackerearth.zomato.dto.RestaurantsNear.NearbyRestaurant;
import com.hackerearth.zomato.dto.RestaurantsNear.Restaurant;
import com.hackerearth.zomato.dto.RestaurantsNear.RestaurantsNearBy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NearByRestaurantActivity extends AppCompatActivity implements View.OnClickListener,INearByRestaurantView{

    private Location currentLocation;
    private RecyclerView recyclerViewRestaurant;

    private NearByRestaurantListAdapter nearByRestaurantListAdapter;
    private NearByRestaurantPresenterImp nearByRestaurantPresenterImp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_restaurant);

        initUI();
        setListeners();
        getNearByRestaurants();

    }

    private void initUI(){
        Intent intent = getIntent();
        currentLocation = intent.getExtras().getParcelable("currentlocation");
        recyclerViewRestaurant =(RecyclerView)findViewById(R.id.recycler_view_nearby);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);

        recyclerViewRestaurant.setLayoutManager(mLayoutManager);
        recyclerViewRestaurant.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        nearByRestaurantPresenterImp = new NearByRestaurantPresenterImp(new NearByRestaurantServiceInteractorImp(new CommuncationHelper()),this);
    }

    private  void setListeners()
    {

    }
    private void getNearByRestaurants()
    {
        App.getInstance().showProgressDialog("Loading...Please Wait",this,false);
        Map<String,String> mapParameters = new LinkedHashMap<>();
        mapParameters.put("lat",currentLocation.getLatitude()+"");
        mapParameters.put("lon",currentLocation.getLongitude()+"");
        nearByRestaurantPresenterImp.getNearByRestaurantList(mapParameters);
    }

    @Override
    public void onNearByRestaurantListSuccess(List<RestaurantsNearBy> nearbyRestaurantList) {
        List<NearbyRestaurant> listRestaurant__ = nearbyRestaurantList.get(0).getNearbyRestaurants();
       /* List<Restaurant> restaurants =  ;
        for (int i=0;i<nearbyRestaurantList.size();i++)
        {
            listRestaurant__.add(nearbyRestaurantList.get(i).getNearbyRestaurants().);
        }*/

        //contentProviderHelper.insertIntoDB(this,restaurantsList);
        nearByRestaurantListAdapter = new NearByRestaurantListAdapter(this,listRestaurant__);

        recyclerViewRestaurant.setAdapter(nearByRestaurantListAdapter);

        App.getInstance().dismissProgressDialog();


    }

    @Override
    public void onNearByRestaurantListFailed() {

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
    public void onClick(View v) {

    }
}
