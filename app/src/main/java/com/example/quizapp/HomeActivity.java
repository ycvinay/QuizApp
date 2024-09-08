package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String MY_PREFS = "My_Prefs";
    private static final String USER_NAME_KEY = "username";
    private static final String  is_logged_in_KEY = "isLoggedIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences(MY_PREFS,MODE_PRIVATE);
        String username = sharedPreferences.getString(USER_NAME_KEY, "User");
        TextView txtWelcome = findViewById(R.id.tvWelcome);
        txtWelcome.setText("Welcome, " + username);







    }
}