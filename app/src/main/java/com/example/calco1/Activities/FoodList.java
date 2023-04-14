package com.example.calco1.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calco1.Adapter.FoodAdapter;
import com.example.calco1.Model.FoodModel;
import com.example.calco1.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class FoodList extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ImageButton home;
    public ProgressDialog progressDialog;
    public ArrayList<FoodModel> foodModelArrayList;
    private FoodAdapter foodAdapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        home = findViewById(R.id.homeButton);
        home.setOnClickListener(v -> startActivity(new Intent(FoodList.this, MenuActivity.class)));

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Przetwarzanie danych");
        progressDialog.show();

        recyclerView = findViewById(R.id.foodRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(null);

        db = FirebaseFirestore.getInstance();
        foodModelArrayList = new ArrayList<>();
        foodAdapter = new FoodAdapter(FoodList.this, foodModelArrayList);
        recyclerView.setAdapter(foodAdapter);

        EventChangeListener();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void EventChangeListener() {
        db.collection("Food").orderBy("foodName", Query.Direction.ASCENDING).addSnapshotListener((value, error) -> {
            if (error != null) {
                if (progressDialog.isShowing()) progressDialog.dismiss();
                Log.e("Firestore error", error.getMessage());
                return;
            }
            assert value != null;
            for (DocumentChange dc : value.getDocumentChanges()) {

                if (dc.getType() == DocumentChange.Type.ADDED) {

                    foodModelArrayList.add(dc.getDocument().toObject(FoodModel.class));
                }
                foodAdapter.notifyDataSetChanged();
                if (progressDialog.isShowing()) progressDialog.dismiss();
            }
        });
    }
}
