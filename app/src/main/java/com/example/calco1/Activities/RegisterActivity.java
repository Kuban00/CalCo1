package com.example.calco1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calco1.R;
import com.example.calco1.Model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText editTextName;
    public EditText editTextPassword;
    public EditText editTextEmail;
    public EditText editTextCaloricNeeds;
    public ImageButton registerButton;
    public String email;
    public String password;
    public String name;
    public String caloricNeeds;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
        editTextCaloricNeeds = findViewById(R.id.caloricNeeds);
        editTextName = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
    }

    @Override
    public void onClick(@NonNull View v) {
        if (v.getId() == R.id.registerButton) {
            registerButton();
        }
    }

    private void registerButton() {
        email = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        name = editTextName.getText().toString().trim();
        caloricNeeds = editTextCaloricNeeds.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("Wymagana nazwa użytkownika!");
            editTextName.requestFocus();
            return;
        }
        if (caloricNeeds.isEmpty()) {
            editTextCaloricNeeds.setError("Wymagana zapotrzebowanie kaloryczne!");
            editTextCaloricNeeds.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Wymagany e-mail!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Proszę wpisać prawidłowy adres e-mail!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Wymagane hasło!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Minimalna liczba znaków 6!");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                UserModel userModel = new UserModel(name, email, caloricNeeds);
                FirebaseDatabase.getInstance().getReference("users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(userModel).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Pomyślnie zarejestrowano!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Nieudana próba rejestracji, " + "Spróbuj ponownie", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(RegisterActivity.this, "Nieudana próba rejestracji, " + "\nSpróbuj ponownie", Toast.LENGTH_LONG).show();
            }
        });
    }
}