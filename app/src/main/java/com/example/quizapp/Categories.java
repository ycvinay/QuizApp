package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Categories extends AppCompatActivity {

    private Button btnCQuiz, btnPythonQuiz, btnJavaQuiz, btnJavaScriptQuiz, btnHtmlQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        btnCQuiz = findViewById(R.id.btnCQuiz);

        // Button for C Quiz
        btnCQuiz.setOnClickListener(v-> startQuizActivity("C"));

        // Button for Python Quiz
        btnPythonQuiz = findViewById(R.id.btnPythonQuiz);
        btnPythonQuiz.setOnClickListener(v-> startQuizActivity("Python"));

        // Button for Java Quiz
        btnJavaQuiz = findViewById(R.id.btnJavaQuiz);
        btnJavaQuiz.setOnClickListener(v-> startQuizActivity("Java"));

        // Button for JavaScript Quiz
        btnJavaScriptQuiz = findViewById(R.id.btnJavaScriptQuiz);
        btnJavaScriptQuiz.setOnClickListener(v-> startQuizActivity("JavaScript"));


        // Button for HTML Quiz
        btnHtmlQuiz = findViewById(R.id.btnHtmlQuiz);
        btnHtmlQuiz.setOnClickListener(v-> startQuizActivity("HTML"));

    }

    private void startQuizActivity(String category) {
        Intent intent = new Intent(Categories.this, QuizActivity.class);
        intent.putExtra("QUIZ_CATEGORY", category);
        startActivity(intent);
    }

}