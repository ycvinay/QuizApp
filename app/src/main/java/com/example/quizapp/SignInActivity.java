package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.database.AppDatabase;
import com.example.quizapp.model.User;

public class SignInActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String MY_PREFS = "My_Prefs";
    private static final String USER_NAME_KEY = "username";
    private static final String  is_logged_in_KEY = "isLoggedIn";

    private EditText etEmailSignIn, etPhoneSignIn;
    private Button btnSignIn;
    private TextView tvRegister;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sharedPreferences = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean(is_logged_in_KEY, false);
        if (isLoggedIn) {
            Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        etEmailSignIn = findViewById(R.id.etEmailSignIn);
        etPhoneSignIn = findViewById(R.id.etPhoneSignIn);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvRegister = findViewById(R.id.tvRegister);

        appDatabase = AppDatabase.getDatabase(this);

        btnSignIn.setOnClickListener(v -> signInUser());
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void signInUser() {
        String email = etEmailSignIn.getText().toString().trim();
        String phone = etPhoneSignIn.getText().toString().trim();

        if (email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            User user = appDatabase.userDao().getUserByEmail(email);
            if (user != null && user.getPhone().equals(phone)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USER_NAME_KEY, user.getUsername());
                editor.putBoolean(is_logged_in_KEY, true);
                editor.apply();

                Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(intent);
                runOnUiThread(() -> Toast.makeText(this, "Sign-in successful!", Toast.LENGTH_SHORT).show());
            } else {
                runOnUiThread(() -> Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
