package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
    private Button btnNext, btnPrevious, btnSkip, btnSubmit, btnEnd;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;  // Initialize to 0
    private Button selectedBtn = null;
    private int score;
    private int totalQuestions;
    private boolean answered = false;

    private String category;
    private QuizDB db;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        category = intent.getStringExtra("QUIZ_CATEGORY");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(category+ " Quiz");
        setSupportActionBar(toolbar);

        tvQuestion = findViewById(R.id.tvQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnSkip = findViewById(R.id.btnSkip);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnEnd = findViewById(R.id.btnEnd);



        db = QuizDB.getDatabase(this);

        // Commented out to prevent unnecessary database population
        // DatabaseInitializer.populateDB(db);

        loadQuestion();

        btnNext.setOnClickListener(v -> navigateToNextQuestion());
        btnPrevious.setOnClickListener(v -> navigateToPreviousQuestion());
        btnSkip.setOnClickListener(v -> skipQuestion());
        btnSubmit.setOnClickListener(v-> submitQuiz());
        btnEnd.setOnClickListener(v -> endQuiz());

        setOptionButtons();
    }

    private void loadQuestion() {
        executor.execute(() -> {
            questionList = db.questionDao().getQuestionsByCategory(category);
            totalQuestions = questionList.size();

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

            resetButtonColors();

            answered = false;
            // Update button states
            updateBtnStates();
        } else {
            // Handle case where no questions are available
            tvQuestion.setText("No more questions.");
            btnOption1.setText("");
            btnOption2.setText("");
            btnOption3.setText("");
            btnOption4.setText("");
        }
    }

    private void updateBtnStates() {
        btnNext.setEnabled(currentQuestionIndex < questionList.size() -1);
        btnSkip.setEnabled(currentQuestionIndex < questionList.size() -1);
        btnPrevious.setEnabled(currentQuestionIndex > 0);
    }

    private void resetButtonColors() {
        btnOption1.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));;
        btnOption2.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        btnOption3.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        btnOption4.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

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

    private void setOptionButtons() {
        btnOption1.setOnClickListener(v -> handleOptionClick(btnOption1));
        btnOption2.setOnClickListener(v -> handleOptionClick(btnOption2));
        btnOption3.setOnClickListener(v -> handleOptionClick(btnOption3));
        btnOption4.setOnClickListener(v -> handleOptionClick(btnOption4));

    }

    private void handleOptionClick(Button button) {

        if (!answered) {

            Question currentQuestion = questionList.get(currentQuestionIndex);
            if (button.getText().equals(currentQuestion.getAnswer())) {
                score++;
            }

            if (selectedBtn != null) {
                selectedBtn.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            }

            button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            selectedBtn = button;

            answered = true;
        }
    }



    private void submitQuiz() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Submit Quiz")
                .setMessage("Do you want to Submit?")
                .setPositiveButton("Submit", (dialog1, which) -> {
                    Toast.makeText(QuizActivity.this, "Quiz Submitted. Your Score is " + score + " out of " + totalQuestions, Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void endQuiz(){
        new AlertDialog.Builder(this)
                .setTitle("Cancel Quiz")
                .setMessage("Are you sure want to cancel the quiz? Your progress will not be saved.")
                .setPositiveButton("Yes", (dialog, which) -> {
                    finish();
                })
                .setNegativeButton("No", null)
                .show();
    }

}
