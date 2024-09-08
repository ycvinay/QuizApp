package com.example.quizapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quizapp.dao.UserDao;
import com.example.quizapp.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDB extends RoomDatabase {
    private static volatile UserDB INSTANCE;

    public abstract UserDao userDao();

    public static UserDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    UserDB.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
