package com.example.calco1.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calco1.Adapter.AddItemAdapter;
import com.example.calco1.Model.AddItemModel;
import com.example.calco1.R;
import com.example.calco1.Model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class SaturdayActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public FirebaseFirestore db;
    public AddItemAdapter addItemAdapter;
    public ArrayList<AddItemModel> addItemList;
    public FirebaseAuth firebaseAuth;
    public FirebaseUser user;
    public DatabaseReference reference;
    public String userID;
    public ImageButton home;
    public ImageButton addItem;
    public ImageButton monday;
    public ImageButton tuesday;
    public ImageButton wednesday;
    public ImageButton thursday;
    public ImageButton friday;
    public ImageButton sunday;
    public TextView totalCaloriesTextView;
    public TextView userTextView;
    public TextView calorieTextView;

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saturday);

        userTextView = findViewById(R.id.user);
        calorieTextView = findViewById(R.id.calorieText);
        totalCaloriesTextView = findViewById(R.id.allTotalCaloriesTextView);
        home = findViewById(R.id.homeButton);
        home.setOnClickListener(v -> startActivity(new Intent(SaturdayActivity.this, MenuActivity.class)));

        monday = findViewById(R.id.mondayButton);
        monday.setOnClickListener(v -> startActivity(new Intent(SaturdayActivity.this, MondayActivity.class)));

        tuesday = findViewById(R.id.tuesdayButton);
        tuesday.setOnClickListener(v -> startActivity(new Intent(SaturdayActivity.this, TuesdayActivity.class)));

        wednesday = findViewById(R.id.wednesdayButton);
        wednesday.setOnClickListener(v -> startActivity(new Intent(SaturdayActivity.this, WednesdayActivity.class)));

        thursday = findViewById(R.id.thursdayButton);
        thursday.setOnClickListener(v -> startActivity(new Intent(SaturdayActivity.this, ThursdayActivity.class)));

        friday = findViewById(R.id.fridayButton);
        friday.setOnClickListener(v -> startActivity(new Intent(SaturdayActivity.this, FridayActivity.class)));

        sunday = findViewById(R.id.sundayButton);
        sunday.setOnClickListener(v -> startActivity(new Intent(SaturdayActivity.this, SundayActivity.class)));

        addItem = findViewById(R.id.addItemButton);
        addItem.setOnClickListener(v -> startActivity(new Intent(SaturdayActivity.this, FoodList.class)));

        recyclerView = findViewById(R.id.saturdayRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(null);

        db = FirebaseFirestore.getInstance();
        addItemList = new ArrayList<>();
        addItemAdapter = new AddItemAdapter(SaturdayActivity.this, addItemList);
        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView.setAdapter(addItemAdapter);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        db.collection("SaturdayAdd").document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid()).collection("User").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    String documentId = documentSnapshot.getId();
                    AddItemModel itemModel = documentSnapshot.toObject(AddItemModel.class);
                    assert itemModel != null;
                    itemModel.setDocumentId(documentId);
                    addItemList.add(itemModel);
                    addItemAdapter.notifyDataSetChanged();
                }
                double totalCalories = addItemList.stream().mapToDouble(item -> Double.parseDouble(item.totalCalories.trim())).sum();
                totalCaloriesTextView.setText("Dzisiejsza suma kalorii: " + totalCalories + " kCal");
            }
        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModelProfile = snapshot.getValue(UserModel.class);
                if (userModelProfile != null) {
                    String name = userModelProfile.name;
                    String caloricNeeds = userModelProfile.caloricNeeds;
                    calorieTextView.setText("Twoje zapotrzebowanie kaloryczne: " + caloricNeeds + " " + "kCal");
                    userTextView.setText("Witaj : " + name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SaturdayActivity.this, "Coś poszło nie tak!", Toast.LENGTH_LONG).show();
            }
        });
    }
}