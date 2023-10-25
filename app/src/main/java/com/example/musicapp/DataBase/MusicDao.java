package com.example.musicapp.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.musicapp.Class.Music;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MusicDao {

    @Insert
    void insertMusic(Music music);

    @Query("SELECT * FROM music ORDER BY music.tenNhac")
    List<Music> getMusicArray();

    @Query("SELECT * FROM music WHERE tenNhac = :tenNhac")
    List<Music>checkExist(String tenNhac);
    @Update
    void updateMusic(Music music);
    @Delete
    void deleteMusic(Music music);

    @Query("SELECT * FROM music WHERE (music.tenNhacFormat LIKE '%' || :name || '%') OR  (music.caSiFormat LIKE '%' || :name || '%') ")
    List<Music>searchMusic(String name);
}
