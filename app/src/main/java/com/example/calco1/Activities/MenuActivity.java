package com.example.calco1.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calco1.R;
import com.example.calco1.Model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuActivity extends AppCompatActivity {
    public FirebaseUser user;
    public DatabaseReference reference;
    public String userID;
    public ImageButton home;
    public ImageButton monday;
    public ImageButton tuesday;
    public ImageButton wednesday;
    public ImageButton thursday;
    public ImageButton friday;
    public ImageButton saturday;
    public ImageButton sunday;
    public TextView userTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        home = findViewById(R.id.homeButton);
        home.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, HomeActivity.class)));

        monday = findViewById(R.id.mondayButton);
        monday.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, MondayActivity.class)));

        tuesday = findViewById(R.id.tuesdayButton);
        tuesday.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, TuesdayActivity.class)));

        wednesday = findViewById(R.id.wednesdayButton);
        wednesday.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, WednesdayActivity.class)));

        thursday = findViewById(R.id.thursdayButton);
        thursday.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, ThursdayActivity.class)));

        friday = findViewById(R.id.fridayButton);
        friday.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, FridayActivity.class)));

        saturday = findViewById(R.id.saturdayButton);
        saturday.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, SaturdayActivity.class)));

        sunday = findViewById(R.id.sundayButton);
        sunday.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, SundayActivity.class)));

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();
        userTextView = findViewById(R.id.user);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userModelProfile = snapshot.getValue(UserModel.class);
                if (userModelProfile != null) {
                    String name = userModelProfile.name;
                    userTextView.setText("Witaj : " + name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MenuActivity.this, "Coś poszło nie tak!", Toast.LENGTH_LONG).show();
            }
        });
    }
}

