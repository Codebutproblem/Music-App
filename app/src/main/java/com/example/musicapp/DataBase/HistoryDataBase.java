package com.example.musicapp.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.musicapp.Class.Book;
import com.example.musicapp.Class.Music;

@Database(entities = {Book.class},version = 1)
public abstract class HistoryDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "history_data.db";
    private static HistoryDataBase instance;
    public static synchronized HistoryDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), HistoryDataBase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract HistoryDao historyDao();
}
