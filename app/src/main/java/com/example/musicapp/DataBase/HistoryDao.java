package com.example.musicapp.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.musicapp.Class.Book;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    void insertHistory(Book book);

    @Query("SELECT * FROM history")
    List<Book> getBookArray();

    @Delete
    void deleteBook(Book book);

}
