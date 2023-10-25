package com.example.musicapp.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.musicapp.Class.Music;

@Database(entities = {Music.class},version = 1)
public abstract class MusicDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "musicdata.db";
    private static MusicDataBase instance;

    public static synchronized MusicDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MusicDataBase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract MusicDao musicDao();
}
