package com.example.quizapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quizapp.dao.QuestionDao;
import com.example.quizapp.model.Question;

@Database(entities = {Question.class}, version = 2, exportSchema = false)
public abstract class QuizDB extends RoomDatabase {
    public abstract QuestionDao questionDao();

    private static volatile QuizDB INSTANCE;

    public static QuizDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuizDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuizDB.class, "quiz_db").fallbackToDestructiveMigration().build();
                }
            }
        }

        return INSTANCE;
    }

    public static void clearDB() {
        if (INSTANCE != null) {
            INSTANCE.clearAllTables();
        }
    }


}
