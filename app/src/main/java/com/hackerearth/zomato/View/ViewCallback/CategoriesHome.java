package com.hackerearth.zomato.View.ViewCallback;

import com.hackerearth.zomato.dto.Categories;
import com.hackerearth.zomato.dto.Category;

import java.util.List;

/**
 * Created by santosh on 9/24/2016.
 */

public class CategoriesHome
{
   public interface  View{
        void onCategoriesSuccess(List<Category> categoriesList);
        void onCategoriesFailed();
        void onNetworkError();
        void showErrorMessage(String message);
        void onResponseFailed();

   }
}

