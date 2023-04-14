package com.example.calco1.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calco1.R;
import com.example.calco1.Adapter.RecipesAdapter;
import com.example.calco1.Model.RecipesModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class RecipesActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public ArrayList<RecipesModel> recipesModelArrayList;
    public RecipesAdapter recipesAdapter;
    public FirebaseFirestore db;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Przetwarzanie danych");
        progressDialog.show();

        recyclerView = findViewById(R.id.recipeRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(null);

        recipesModelArrayList = new ArrayList<>();
        recipesAdapter = new RecipesAdapter(RecipesActivity.this, recipesModelArrayList);
        recyclerView.setAdapter(recipesAdapter);
        db = FirebaseFirestore.getInstance();

        EventChangeListener();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void EventChangeListener() {
        db.collection("Recipes").orderBy("img_url", Query.Direction.ASCENDING).addSnapshotListener((value, error) -> {
            if (error != null) {
                if (progressDialog.isShowing()) progressDialog.dismiss();
                Log.e("Firestore error", error.getMessage());
                return;
            }
            assert value != null;
            for (DocumentChange dc : value.getDocumentChanges()) {
                if (dc.getType() == DocumentChange.Type.ADDED) {
                    recipesModelArrayList.add(dc.getDocument().toObject(RecipesModel.class));
                }
                recipesAdapter.notifyDataSetChanged();
                if (progressDialog.isShowing()) progressDialog.dismiss();
            }
        });
    }
}