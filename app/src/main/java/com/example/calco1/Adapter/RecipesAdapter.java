package com.example.calco1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.calco1.Activities.ExtendedRecipes;
import com.example.calco1.R;
import com.example.calco1.Model.RecipesModel;

import java.util.ArrayList;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.MyViewHolder> {
    public Context context;
    public ArrayList<RecipesModel> recipesModelArrayList;

    public RecipesAdapter(Context context, ArrayList<RecipesModel> recipesModelArrayList) {
        this.context = context;
        this.recipesModelArrayList = recipesModelArrayList;
    }

    @NonNull
    @Override
    public RecipesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recipe_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesAdapter.MyViewHolder holder, int position) {
        RecipesModel recipesModel = recipesModelArrayList.get(position);
        Glide.with(context).load(recipesModelArrayList.get(position).getImg_url()).into(holder.recipeImage);
        holder.recipeName.setText(recipesModel.recipeName);
        holder.recipeContent.setText(recipesModel.recipeContent);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ExtendedRecipes.class);
            intent.putExtra("recipeName", recipesModel.getRecipeName());
            intent.putExtra("ingredients", recipesModel.getIngredients());
            intent.putExtra("description", recipesModel.getDescription());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recipesModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView recipeName;
        private final TextView recipeContent;
        private final ImageView recipeImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeTextView);
            recipeContent = itemView.findViewById(R.id.recipeContentTextView);
            recipeImage = itemView.findViewById(R.id.recipeImage);
        }
    }
}
