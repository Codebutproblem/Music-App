package com.example.musicapp.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.musicapp.Class.Music;
import com.example.musicapp.Class.Musician;

import java.util.List;

@Dao
public interface MusicianDao {
    @Insert
    void insertMusician(Musician musician);

    @Query("SELECT * FROM musician ORDER BY musician.name")
    List<Musician> getMusicianArray();
    @Query("SELECT * FROM musician WHERE name = :name")
    List<Musician>checkExist(String name);

    @Delete
    void deleteMusician(Musician musician);
}
