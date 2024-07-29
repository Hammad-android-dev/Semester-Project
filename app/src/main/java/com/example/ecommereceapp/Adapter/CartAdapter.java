package com.example.ecommereceapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.ecommereceapp.Activity.DetailActivity;
import com.example.ecommereceapp.Helper.ChangeNumberItemsListener;
import com.example.ecommereceapp.Helper.ManagmentCart;
import com.example.ecommereceapp.databinding.ViewholderCartBinding;
import com.example.ecommereceapp.databinding.ViewholderPopListBinding;
import com.example.ecommereceapp.domain.PopularDomain;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>
{
    ArrayList<PopularDomain> items;
    Context context;
    ViewholderCartBinding binding;
    ChangeNumberItemsListener changeNumberItemsListener;
    ManagmentCart managmentCart;


    public CartAdapter(ArrayList<PopularDomain> items, ChangeNumberItemsListener changeNumberItemsListener)
    {
        this.items = items;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    public CartAdapter(Context context)
    {
        this.context = context;
    }

    public CartAdapter(ChangeNumberItemsListener changeNumberItemsListener)
    {
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context=parent.getContext();
        managmentCart = new ManagmentCart(context);
        return new ViewHolder(binding);

        //return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position)
    {
        binding.titleTxt.setText(items.get(position).getTitle());
        binding.feeEachitem.setText("$"+items.get(position).getPrice());
        binding.totalEachitem.setText("$"+Math.round(items.get(position).getNumberInCart()*items.get(position).getPrice()));
        binding.numberItemTxt.setText(String.valueOf(items.get(position).getNumberInCart()));

        int drawableResourced = holder.itemView.getResources().getIdentifier(items.get(position).getPicurl(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(binding.imageView4);


        binding.plusCartBtn.setOnClickListener(v -> {
            managmentCart.plusItem(items, position, new ChangeNumberItemsListener()
            {
                @Override
                public void change() {
                    notifyDataSetChanged();
                    changeNumberItemsListener.change();
                }

                @Override
                public void changed() {

                }
            });
        });


        binding.minusCartBtn.setOnClickListener(v -> {
            managmentCart.minusItem(items, position, new ChangeNumberItemsListener() {
                @Override
                public void change() {
                    notifyDataSetChanged();
                    changeNumberItemsListener.change();
                }

                @Override
                public void changed() {
                }
            });
        });


    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public ViewHolder(ViewholderCartBinding binding)
        {
            super(binding.getRoot());
        }
    }
}
