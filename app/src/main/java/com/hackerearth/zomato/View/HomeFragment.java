package com.hackerearth.zomato.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hackerearth.zomato.App;
import com.hackerearth.zomato.Communicator.CommuncationHelper;
import com.hackerearth.zomato.Presenter.CategoriesPresenterImp;
import com.hackerearth.zomato.R;
import com.hackerearth.zomato.ServiceConstants.Constants;
import com.hackerearth.zomato.Services.CategoriesServiceInteractorImp;
import com.hackerearth.zomato.View.Adapters.DividerItemDecoration;
import com.hackerearth.zomato.View.Adapters.ItemClickListener;
import com.hackerearth.zomato.View.Adapters.RestaurantAdapter;
import com.hackerearth.zomato.View.ViewCallback.CategoriesHome;
import com.hackerearth.zomato.View.ViewCallback.IFragmentListener;
import com.hackerearth.zomato.dto.Categories;
import com.hackerearth.zomato.dto.Category;

import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener,IFragmentListener,CategoriesHome.View ,ItemClickListener{

    private CategoriesPresenterImp categoriesPresenterImp;
    private RecyclerView recyclerViewCategories;
    private RestaurantAdapter restaurantAdapter;
    private List<Category> listCategory;
    private View mRootView;
    private Button btnBrowsePlaces;
    private HomeScreen activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public HomeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        initUI();
        setListeners();
        App.getInstance().showProgressDialog("Loading",this.getActivity(),false);
        categoriesPresenterImp = new CategoriesPresenterImp(new CategoriesServiceInteractorImp(new CommuncationHelper()),this);
        categoriesPresenterImp.getAllCategories();
        return mRootView;
    }


    private void initUI()
    {
        sharedPreferences = this.getActivity().getSharedPreferences(Constants.ZOMATOPREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        btnBrowsePlaces =(Button)mRootView.findViewById(R.id.btn_browseplaces);

        recyclerViewCategories = (RecyclerView)mRootView.findViewById(R.id.recycler_view_categories);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());

        recyclerViewCategories.setLayoutManager(mLayoutManager);
        recyclerViewCategories.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayoutManager.VERTICAL));
    }
    private  void setListeners()
    {
        btnBrowsePlaces.setOnClickListener(this);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onCategoriesSuccess(List<Category> categoriesList) {
        listCategory = categoriesList;

        restaurantAdapter = new RestaurantAdapter(this.getActivity(),categoriesList , this);
        recyclerViewCategories.setAdapter(restaurantAdapter);
        App.getInstance().dismissProgressDialog();
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
    public void onCategoriesFailed() {

    }

    @Override
    public void onClick(View view, int position) {

        editor.putString(Constants.CATEGORYID,listCategory.get(position).getCategories().getId()+"");
        editor.commit();
        Intent intent = new Intent(this.getActivity(),RestaurantListActivity.class);
            intent.putExtra(Constants.CATEGORYID,listCategory.get(position).getCategories().getId());
            intent.putExtra("CategoryName",listCategory.get(position).getCategories().getName());
            intent.putExtra("location",this.activity.mLastLocation);

            startActivity(intent);
    }

    @Override
    public void setHomeFragmentListener(HomeScreen activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_browseplaces)
        {
            Intent intent = new Intent(this.getActivity(),NearByRestaurantActivity.class);
            intent.putExtra("currentlocation",this.activity.mLastLocation);
            startActivity(intent);
        }
    }
}
