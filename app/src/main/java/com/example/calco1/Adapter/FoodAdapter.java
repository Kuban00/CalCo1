package com.example.calco1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calco1.Activities.ExtendedFoodList;
import com.example.calco1.Model.FoodModel;
import com.example.calco1.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    public Context context;
    public ArrayList<FoodModel> foodModelArrayList;

    public FoodAdapter(Context context, ArrayList<FoodModel> foodModelArrayList) {
        this.context = context;
        this.foodModelArrayList = foodModelArrayList;
    }

    @NonNull
    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyViewHolder holder, int position) {
        FoodModel foodModel = foodModelArrayList.get(position);

        holder.foodName.setText(foodModel.getFoodName());
        holder.calories.setText(foodModel.getCalories() + " " + "kCal/1g");
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ExtendedFoodList.class);
            intent.putExtra("calories", foodModel.getCalories());
            intent.putExtra("foodName", foodModel.getFoodName());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView foodName;
        public TextView calories;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.foodTextView);
            calories = itemView.findViewById(R.id.kcalTextView);
        }
    }
}