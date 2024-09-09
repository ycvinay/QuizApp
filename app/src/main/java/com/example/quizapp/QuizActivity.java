package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.quizapp.database.DatabaseInitializer;
import com.example.quizapp.database.QuizDB;
import com.example.quizapp.model.Question;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private Button btnOption1, btnOption2, btnOption3, btnOption4;
    private Button btnNext, btnPrevious, btnSkip;

    private List<Question> questionList;
    private int currentQuestionIndex;
    private Button selectedBtn = null;

    private String category;
    private QuizDB db;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvQuestion = findViewById(R.id.tvQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnSkip = findViewById(R.id.btnSkip);

        Intent intent = getIntent();
        category = intent.getStringExtra("QUIZ_CATEGORY");

        db = QuizDB.getDatabase(this);

        DatabaseInitializer.populateDB(db);
        loadQuestion();

        btnNext.setOnClickListener(v -> navigateToNextQuestion());
        btnPrevious.setOnClickListener(v -> navigateToPrevioueQuestion());
        btnSkip.setOnClickListener(v -> skipQuestion());


    }

    private void loadQuestion(){
        executor.execute(() ->{
            questionList = db.questionDao().getQuestionsByCategory(category);

            runOnUiThread(() -> {
                displayQuestion();
            });
        });
    }
    private void loadCQuestion() {
        executor.execute( () -> {
            questionList = db.questionDao().getQuestionsByCategory("C");

            runOnUiThread(() -> {
                displayQuestion();
            });

        });
    }

    private void displayQuestion() {
        if (currentQuestionIndex >= 0 && currentQuestionIndex < questionList.size()) {
            Question currentQuestion = questionList.get(currentQuestionIndex);

            tvQuestion.setText(currentQuestion.getQuestion());
            btnOption1.setText(currentQuestion.getOption1());
            btnOption2.setText(currentQuestion.getOption2());
            btnOption3.setText(currentQuestion.getOption3());
            btnOption4.setText(currentQuestion.getOption4());

        }

    }

    private void navigateToNextQuestion() {
        if (currentQuestionIndex < questionList.size() - 1) {
            currentQuestionIndex++;
            displayQuestion();
        }

    }

    private void navigateToPrevioueQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            displayQuestion();
        }

    }


    private void skipQuestion() {
        navigateToNextQuestion();
    }

}