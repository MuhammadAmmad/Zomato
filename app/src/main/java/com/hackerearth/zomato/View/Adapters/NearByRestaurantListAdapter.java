package com.hackerearth.zomato.View.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hackerearth.zomato.R;
import com.hackerearth.zomato.dto.Restaurant_;
import com.hackerearth.zomato.dto.Restaurant__;
import com.hackerearth.zomato.dto.RestaurantsNear.NearbyRestaurant;
import com.hackerearth.zomato.dto.RestaurantsNear.RestaurantsNearBy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by santosh on 9/28/2016.
 */


public class NearByRestaurantListAdapter extends RecyclerView.Adapter<NearByRestaurantListAdapter.RestaurantViewHolder> {

    private ItemClickListener clickListener;
    private List<NearbyRestaurant> itemList;
    private Context context;

    public NearByRestaurantListAdapter(Context context, List<NearbyRestaurant> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public NearByRestaurantListAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_restaurant_list, null);
        NearByRestaurantListAdapter.RestaurantViewHolder rcv = new NearByRestaurantListAdapter.RestaurantViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(NearByRestaurantListAdapter.RestaurantViewHolder holder, int position) {
        holder.cuisinesText.setText(itemList.get(position).getRestaurant().getCuisines());
        holder.ratingText.setText(itemList.get(position).getRestaurant().getUserRating().getRatingText());
        holder.titleText.setText(itemList.get(position).getRestaurant().getName());
        holder.aggregateRatingText.setText(itemList.get(position).getRestaurant().getUserRating().getAggregateRating());

        holder.addressTextView.setText(itemList.get(position).getRestaurant().getLocation().getAddress()+" ");
        if(!itemList.get(position).getRestaurant().getFeaturedImage().isEmpty()) {
            Picasso.with(context)
                    .load(itemList.get(position).getRestaurant().getFeaturedImage())
                    .placeholder(R.mipmap.placeholder)
                    .error(R.mipmap.error)
                    .into(holder.featureImageView);
        }


    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView cuisinesText;
        public ImageView featureImageView;
        public TextView titleText;
        public TextView ratingText;
        public TextView aggregateRatingText;
        public TextView addressTextView;



        public RestaurantViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cuisinesText = (TextView)itemView.findViewById(R.id.txt_cuisines);
            ratingText = (TextView)itemView.findViewById(R.id.txt_rating);
            titleText = (TextView)itemView.findViewById(R.id.txt_restaurant_name);
            featureImageView = (ImageView)itemView.findViewById(R.id.iv_feature);
            aggregateRatingText = (TextView)itemView.findViewById(R.id.txt_aggregaterating);
            addressTextView =(TextView)itemView.findViewById(R.id.txt_address);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
