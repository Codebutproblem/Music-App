package com.example.musicapp.Data;

import com.example.musicapp.Activity.MainActivity;
import com.example.musicapp.Class.Book;
import com.example.musicapp.Class.Music;
import com.example.musicapp.Class.Musician;
import com.example.musicapp.DataBase.HistoryDataBase;
import com.example.musicapp.DataBase.MusicDataBase;
import com.example.musicapp.DataBase.MusicianDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibraryData {

    public static List<Book> getFavlist(){
        List<Music>musicList = MusicDataBase.getInstance(MainActivity.getContext()).musicDao().getMusicArray();
        List<Book>bookList = new ArrayList<>();
        for (Music music: musicList){
            if (music.getLove()){
                bookList.add(new Book(music.getId(),"favMusic",music.getHinhNen(), music.getTenNhac()));
            }
        }
        return bookList;
    }
    public static List<Book> getHisList(){
        List<Book>bookList = HistoryDataBase.getInstance(MainActivity.getContext()).historyDao().getBookArray();
        Collections.reverse(bookList);
        return bookList;
    }
    public static List<Book> getMusicianData(){
        List<Book>musicianList = new ArrayList<>();
        List<Musician>musicianListOld = MusicianDataBase.getInstance(MainActivity.getContext()).musicianDao().getMusicianArray();
        Collections.shuffle(musicianListOld);
        for(int i = 0 ; i < 7; i++){
            if(i < musicianListOld.size()){
                musicianList.add(new Book(musicianListOld.get(i).getId(),"musician",musicianListOld.get(i).getImageId(),musicianListOld.get(i).getName()));
            }
        }
        return musicianList;
    }
}
