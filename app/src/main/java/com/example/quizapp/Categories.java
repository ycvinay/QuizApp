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

//        btnCQuiz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Categories.this, CQuizActivity.class);
//                startActivity(intent);             }
//        });

        btnCQuiz.setOnClickListener(v-> startQuizActivity("C"));

        // Button for Python Quiz
        btnPythonQuiz = findViewById(R.id.btnPythonQuiz);

        btnPythonQuiz.setOnClickListener(v-> startQuizActivity("Python"));
//        btnPythonQuiz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Categories.this, PythonActivity.class);
//                startActivity(intent);             }
//        });

        // Button for Java Quiz

        btnJavaQuiz = findViewById(R.id.btnJavaQuiz);
        btnJavaQuiz.setOnClickListener(v-> startQuizActivity("Java"));

//        btnJavaQuiz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Categories.this, JavaActivity.class);
//                startActivity(intent);             }
//        });

        // Button for JavaScript Quiz

        btnJavaScriptQuiz = findViewById(R.id.btnJavaScriptQuiz);
        btnJavaScriptQuiz.setOnClickListener(v-> startQuizActivity("JavaScript"));

//        btnJavaScriptQuiz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Categories.this, JSActivity.class);
//                startActivity(intent);            }
//        });

        // Button for HTML Quiz

        btnHtmlQuiz = findViewById(R.id.btnHtmlQuiz);
        btnHtmlQuiz.setOnClickListener(v-> startQuizActivity("HTML"));

//        btnHtmlQuiz.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Categories.this, HtmlActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void startQuizActivity(String category) {
        Intent intent = new Intent(Categories.this, QuizActivity.class);
        intent.putExtra("QUIZ_CATEGORY", category);
        startActivity(intent);
    }


}