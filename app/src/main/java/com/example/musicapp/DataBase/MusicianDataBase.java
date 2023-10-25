package com.example.musicapp.DataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.musicapp.Class.Music;
import com.example.musicapp.Class.Musician;

@Database(entities = {Musician.class},version = 1)
public abstract class MusicianDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "musician_data.db";
    private static MusicianDataBase instance;
    public static synchronized MusicianDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MusicianDataBase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract MusicianDao musicianDao();
}
