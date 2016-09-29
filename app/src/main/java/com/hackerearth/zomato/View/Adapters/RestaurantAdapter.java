package com.hackerearth.zomato.View.Adapters;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hackerearth.zomato.R;
import com.hackerearth.zomato.dto.Categories;
import com.hackerearth.zomato.dto.Category;

import java.util.List;
/**
 * Created by santosh on 9/24/2016.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Category> itemList;
    private Context context;
    private ItemClickListener clickListener;

    public RestaurantAdapter(Context context, List<Category> itemList , ItemClickListener clickListener) {
        this.itemList = itemList;
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row_layout, null);
        RestaurantViewHolder rcv = new RestaurantViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.categoryName.setText(itemList.get(position).getCategories().getName());

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView categoryName;


        public RestaurantViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            categoryName = (TextView)itemView.findViewById(R.id.txtview_category);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}