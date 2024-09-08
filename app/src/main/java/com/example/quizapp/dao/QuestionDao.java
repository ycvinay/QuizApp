package com.example.quizapp.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizapp.model.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insertQuestion(Question question);

    @Query("select * from questions where category = :category")
    List<Question> getQuestionsByCategory(String category);

    @Query("select * from questions")
    List<Question> getAllQuestions();
}
