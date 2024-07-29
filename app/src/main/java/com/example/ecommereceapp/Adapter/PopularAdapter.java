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
import com.example.ecommereceapp.databinding.ViewholderPopListBinding;
import com.example.ecommereceapp.domain.PopularDomain;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder>
{
    ArrayList<PopularDomain> items;
    Context context;
    ViewholderPopListBinding binding;
    public PopularAdapter(ArrayList<PopularDomain> items)
    {
        this.items = items;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        binding = ViewholderPopListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context=parent.getContext();
        return new ViewHolder(binding);

        //return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position)
    {
        binding.titleTxt.setText(items.get(position).getTitle());
        binding.feeTxt.setText("$"+items.get(position).getPrice());
        binding.scoreTxt.setText(""+items.get(position).getScore());
        binding.reviewTxt.setText(""+items.get(position).getReview());

        int drawableResourced = holder.itemView.getResources().getIdentifier(items.get(position).getPicurl(),
                "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(binding.pic);


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("object",items.get(position));
            context.startActivity(intent);
            //Toast.makeText(context, items.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public ViewHolder(ViewholderPopListBinding binding)
        {
            super(binding.getRoot());
        }
    }
}
