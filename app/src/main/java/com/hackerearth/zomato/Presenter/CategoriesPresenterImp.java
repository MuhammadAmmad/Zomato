package com.hackerearth.zomato.Presenter;

import com.android.volley.VolleyError;
import com.hackerearth.zomato.Communicator.RequestInput;
import com.hackerearth.zomato.DataObjects.ErrorMessage;
import com.hackerearth.zomato.Services.ICategoriesServiceInteractor;
import com.hackerearth.zomato.Services.IResponseServiceListener;
import com.hackerearth.zomato.View.ViewCallback.CategoriesHome;
import com.hackerearth.zomato.dto.Categories;
import com.hackerearth.zomato.dto.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by santosh on 9/24/2016.
 */

public class CategoriesPresenterImp implements  ICategoriesPresenter,IResponseServiceListener {

    private final ICategoriesServiceInteractor serviceInteractor;
    private final CategoriesHome.View categoriesView;



    public CategoriesPresenterImp(ICategoriesServiceInteractor serviceInteractor, CategoriesHome.View categoriesView)
    {
            this.serviceInteractor = serviceInteractor;
        this.categoriesView = categoriesView;
    }

    @Override
    public void getAllCategories() {
        RequestInput inputParams = new RequestInput();
        HashMap<String, String>  parameters = new HashMap<String, String>();
        parameters.put("Accept","application/json");
        inputParams.headers = parameters;

        serviceInteractor.addCategoryServiceListener(this);
        serviceInteractor.sendParameters(inputParams);
    }

    @Override
    public void onResponseRecieved(List<Categories> listCategories, ErrorMessage errMessage, int param) {
        if(errMessage!=null){
            categoriesView.showErrorMessage(errMessage.getErrorMessage());
            return;
        }
        List<Category> list =(ArrayList<Category>) listCategories.get(0).getCategories();
        categoriesView.onCategoriesSuccess(list);
    }

    @Override
    public void onResponseFailed(VolleyError error) {

    }
}
