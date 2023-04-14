package com.example.calco1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calco1.R;

public class ExtendedRecipes extends AppCompatActivity {
    public ImageButton home;
    public TextView recipeName;
    public TextView ingredients;
    public TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_recipes);

        home = findViewById(R.id.homeButton);
        home.setOnClickListener(v -> startActivity(new Intent(ExtendedRecipes.this, HomeActivity.class)));

        recipeName = findViewById(R.id.extendedRecipeTextView);
        ingredients = findViewById(R.id.ingredientsTextView);
        description = findViewById(R.id.descriptionTextView);

        recipeName.setText(getIntent().getStringExtra("recipeName"));
        ingredients.setText(getIntent().getStringExtra("ingredients"));
        description.setText(getIntent().getStringExtra("description"));
    }
}