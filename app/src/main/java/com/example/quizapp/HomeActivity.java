package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private Button btnStartCQuiz, btnSelectCategories;

    SharedPreferences sharedPreferences;
    private static final String MY_PREFS = "My_Prefs";
    private static final String USER_NAME_KEY = "username";
    private static final String  is_logged_in_KEY = "isLoggedIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(MY_PREFS,MODE_PRIVATE);
        String username = sharedPreferences.getString(USER_NAME_KEY, "User");
        TextView txtWelcome = findViewById(R.id.tvWelcome);
        txtWelcome.setText("Welcome, " + username);


        btnStartCQuiz = findViewById(R.id.btnStartCQuiz);
        btnSelectCategories = findViewById(R.id.categories);

        btnStartCQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CQuizActivity.class);
            startActivity(intent);
        });

        btnSelectCategories.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, Categories.class);
            startActivity(intent);
        });







    }
}