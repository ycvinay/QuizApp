package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private int currentQuestionIndex = 0;  // Initialize to 0

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

        // Commented out to prevent unnecessary database population
        // DatabaseInitializer.populateDB(db);

        loadQuestion();

        btnNext.setOnClickListener(v -> navigateToNextQuestion());
        btnPrevious.setOnClickListener(v -> navigateToPreviousQuestion());
        btnSkip.setOnClickListener(v -> skipQuestion());
    }

    private void loadQuestion() {
        executor.execute(() -> {
            questionList = db.questionDao().getQuestionsByCategory(category);

            runOnUiThread(() -> {
                if (questionList != null && !questionList.isEmpty()) {
                    Log.d("QuizActivity", "Number of questions: " + questionList.size());
                    displayQuestion();
                } else {
                    tvQuestion.setText("No questions available.");
                }
            });
        });
    }

    private void displayQuestion() {
        if (questionList != null && !questionList.isEmpty() && currentQuestionIndex >= 0 && currentQuestionIndex < questionList.size()) {
            Question currentQuestion = questionList.get(currentQuestionIndex);

            tvQuestion.setText(currentQuestion.getQuestion());
            btnOption1.setText(currentQuestion.getOption1());
            btnOption2.setText(currentQuestion.getOption2());
            btnOption3.setText(currentQuestion.getOption3());
            btnOption4.setText(currentQuestion.getOption4());

            // Update button states
            btnNext.setEnabled(currentQuestionIndex < questionList.size() - 1);
            btnPrevious.setEnabled(currentQuestionIndex > 0);
        } else {
            // Handle case where no questions are available
            tvQuestion.setText("No more questions.");
            btnOption1.setText("");
            btnOption2.setText("");
            btnOption3.setText("");
            btnOption4.setText("");
        }
    }

    private void navigateToNextQuestion() {
        if (questionList != null && currentQuestionIndex < questionList.size() - 1) {
            currentQuestionIndex++;
            displayQuestion();
        } else {
            Toast.makeText(this, "You have reached the end of the quiz.", Toast.LENGTH_SHORT).show();
            btnNext.setEnabled(false);  // Disable Next button
        }
    }

    private void navigateToPreviousQuestion() {
        if (questionList != null && currentQuestionIndex > 0) {
            currentQuestionIndex--;
            displayQuestion();
        } else {
            Toast.makeText(this, "You are at the first question.", Toast.LENGTH_SHORT).show();
        }
    }

    private void skipQuestion() {
        navigateToNextQuestion();
    }
}
