package com.example.calco1.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calco1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class ExtendedFoodList extends AppCompatActivity {
    public ImageButton home;
    public ImageButton mondayAdd;
    public ImageButton tuesdayAdd;
    public ImageButton wednesdayAdd;
    public ImageButton thursdayAdd;
    public ImageButton fridayAdd;
    public ImageButton saturdayAdd;
    public ImageButton sundayAdd;
    public ImageButton plus;
    public ImageButton minus;
    public ImageButton calculate;
    public TextView foodName;
    public TextView calories;
    public TextView totalCalories;
    public TextView quantity;
    public String userId;
    public int totalQuantity = 100;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;

    @SuppressLint("DefaultLocale")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_food_list);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        userId = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();

        totalCalories = findViewById(R.id.totalCaloriesTextView);
        foodName = findViewById(R.id.foodTitleTextView);
        calories = findViewById(R.id.foodCaloriesTextView);
        quantity = findViewById(R.id.quantityTextView);
        plus = findViewById(R.id.plusButton);
        minus = findViewById(R.id.minusButton);
        mondayAdd = findViewById(R.id.mondayAddButton);
        tuesdayAdd = findViewById(R.id.tuesdayAddButton);
        wednesdayAdd = findViewById(R.id.wednesdayAddButton);
        thursdayAdd = findViewById(R.id.thursdayAddButton);
        fridayAdd = findViewById(R.id.fridayAddButton);
        saturdayAdd = findViewById(R.id.saturdayAddButton);
        sundayAdd = findViewById(R.id.sundayAddButton);
        calculate = findViewById(R.id.calculateImageButton);
        home = findViewById(R.id.homeButton);

        foodName.setText(getIntent().getStringExtra("foodName"));
        calories.setText(getIntent().getStringExtra("calories"));

        mondayAdd.setOnClickListener(v -> {
            if (totalCalories.getText().toString().isEmpty()) {
                totalCalories.setError("Nie można dodać pustych kalorii!");
                totalCalories.requestFocus();
            } else {
                mondayAdded();
            }
        });

        tuesdayAdd.setOnClickListener(v -> {
            if (totalCalories.getText().toString().isEmpty()) {
                totalCalories.setError("Nie można dodać pustych kalorii!");
                totalCalories.requestFocus();
            } else {
                tuesdayAdded();
            }
        });

        wednesdayAdd.setOnClickListener(v -> {
            if (totalCalories.getText().toString().isEmpty()) {
                totalCalories.setError("Nie można dodać pustych kalorii!");
                totalCalories.requestFocus();
            } else {
                wednesdayAdded();
            }
        });

        thursdayAdd.setOnClickListener(v -> {
            if (totalCalories.getText().toString().isEmpty()) {
                totalCalories.setError("Nie można dodać pustych kalorii!");
                totalCalories.requestFocus();
            } else {
                thursdayAdded();
            }
        });

        fridayAdd.setOnClickListener(v -> {
            if (totalCalories.getText().toString().isEmpty()) {
                totalCalories.setError("Nie można dodać pustych kalorii!");
                totalCalories.requestFocus();
            } else {
                fridayAdded();
            }
        });

        saturdayAdd.setOnClickListener(v -> {
            if (totalCalories.getText().toString().isEmpty()) {
                totalCalories.setError("Nie można dodać pustych kalorii!");
                totalCalories.requestFocus();
            } else {
                saturdayAdded();
            }
        });

        sundayAdd.setOnClickListener(v -> {
            if (totalCalories.getText().toString().isEmpty()) {
                totalCalories.setError("Nie można dodać pustych kalorii!");
                totalCalories.requestFocus();
            } else {
                sundayAdded();
            }
        });

        home.setOnClickListener(v -> startActivity(new Intent(ExtendedFoodList.this, MenuActivity.class)));

        plus.setOnClickListener(v -> {
            if (totalQuantity < 500) {
                totalQuantity = totalQuantity + 10;
                quantity.setText(String.valueOf(totalQuantity));
            }
        });

        minus.setOnClickListener(v -> {
            if (totalQuantity > 0) {
                totalQuantity = totalQuantity - 10;
                quantity.setText(String.valueOf(totalQuantity));
            }
        });

        calculate.setOnClickListener(v -> {
            double a = Double.parseDouble(calories.getText().toString().replace(",", "."));
            double b = Double.parseDouble(quantity.getText().toString());

            totalCalories.setText(String.format("%.2f", a * b));
        });
    }

    public void mondayAdded() {
        HashMap<String, Object> mondayList = new HashMap<>();

        mondayList.put("foodName", foodName.getText().toString());
        mondayList.put("calories", calories.getText().toString());
        mondayList.put("totalCalories", totalCalories.getText().toString());

        db.collection("MondayAdd").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).collection("User").add(mondayList).addOnCompleteListener(task -> {
            Toast.makeText(ExtendedFoodList.this, "Dodane do Poniedziałku", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void tuesdayAdded() {
        HashMap<String, Object> tuesdayList = new HashMap<>();

        tuesdayList.put("foodName", foodName.getText().toString());
        tuesdayList.put("calories", calories.getText().toString());
        tuesdayList.put("totalCalories", totalCalories.getText().toString());

        db.collection("TuesdayAdd").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).collection("User").add(tuesdayList).addOnCompleteListener(task -> {
            Toast.makeText(ExtendedFoodList.this, "Dodane do Wtorku", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void wednesdayAdded() {
        HashMap<String, Object> wednesdayList = new HashMap<>();

        wednesdayList.put("foodName", foodName.getText().toString());
        wednesdayList.put("calories", calories.getText().toString());
        wednesdayList.put("totalCalories", totalCalories.getText().toString());

        db.collection("WednesdayAdd").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).collection("User").add(wednesdayList).addOnCompleteListener(task -> {
            Toast.makeText(ExtendedFoodList.this, "Dodane do Środy", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void thursdayAdded() {
        HashMap<String, Object> thursdayList = new HashMap<>();

        thursdayList.put("foodName", foodName.getText().toString());
        thursdayList.put("calories", calories.getText().toString());
        thursdayList.put("totalCalories", totalCalories.getText().toString());

        db.collection("ThursdayAdd").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).collection("User").add(thursdayList).addOnCompleteListener(task -> {
            Toast.makeText(ExtendedFoodList.this, "Dodane do Czwartku", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void fridayAdded() {
        HashMap<String, Object> fridayList = new HashMap<>();

        fridayList.put("foodName", foodName.getText().toString());
        fridayList.put("calories", calories.getText().toString());
        fridayList.put("totalCalories", totalCalories.getText().toString());

        db.collection("FridayAdd").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).collection("User").add(fridayList).addOnCompleteListener(task -> {
            Toast.makeText(ExtendedFoodList.this, "Dodane do Piątku", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void saturdayAdded() {
        HashMap<String, Object> saturdayList = new HashMap<>();

        saturdayList.put("foodName", foodName.getText().toString());
        saturdayList.put("calories", calories.getText().toString());
        saturdayList.put("totalCalories", totalCalories.getText().toString());

        db.collection("SaturdayAdd").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).collection("User").add(saturdayList).addOnCompleteListener(task -> {
            Toast.makeText(ExtendedFoodList.this, "Dodane do Soboty", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void sundayAdded() {
        HashMap<String, Object> sundayList = new HashMap<>();

        sundayList.put("foodName", foodName.getText().toString());
        sundayList.put("calories", calories.getText().toString());
        sundayList.put("totalCalories", totalCalories.getText().toString());

        db.collection("SundayAdd").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).collection("User").add(sundayList).addOnCompleteListener(task -> {
            Toast.makeText(ExtendedFoodList.this, "Dodane do Niedzieli", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}