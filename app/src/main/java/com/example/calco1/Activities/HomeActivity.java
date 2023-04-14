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


public class HomeActivity extends AppCompatActivity {

    public FirebaseUser user;
    public DatabaseReference reference;
    public String userID;
    public ImageButton logOut;
    public ImageButton recipes;
    public ImageButton menu;
    public TextView userTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userTextView = findViewById(R.id.user);
        recipes = (ImageButton) findViewById(R.id.recipesImageButton);
        recipes.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, RecipesActivity.class)));

        menu = (ImageButton) findViewById(R.id.menuImageButton);
        menu.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, MenuActivity.class)));

        logOut = (ImageButton) findViewById(R.id.logOutImageButton);
        logOut.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

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
                Toast.makeText(HomeActivity.this, "Coś poszło nie tak!", Toast.LENGTH_LONG).show();
            }
        });
    }
}