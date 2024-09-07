package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.dao.*;
import com.example.quizapp.database.AppDatabase;
import com.example.quizapp.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPhone;
    private Button btnRegister;
    private TextView tvSignIn;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnRegister = findViewById(R.id.btnRegister);
        tvSignIn = findViewById(R.id.tvSignIn);

        appDatabase = AppDatabase.getDatabase(this);

        btnRegister.setOnClickListener(v -> registerUser());
        tvSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
            startActivity(intent);
        });
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            User existingUser = appDatabase.userDao().getUserByEmail(email);
            if (existingUser == null) {
                User user = new User();
                user.setUsername(username);
                user.setEmail(email);
                user.setPhone(phone);
                appDatabase.userDao().insert(user);
                runOnUiThread(() -> Toast.makeText(this, "User registered!", Toast.LENGTH_SHORT).show());
            } else {
                runOnUiThread(() -> Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
