package com.example.musicapp.Data;

import com.example.musicapp.Class.Book;
import com.example.musicapp.Class.Music;
import com.example.musicapp.Class.Musician;
import com.example.musicapp.ConnectionSQL.ConClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class LibraryData {
    Connection connection;

    public LibraryData() {
        ConClass con = new ConClass();
        connection = con.conclass();
    }
    public ArrayList<Book> getFavlist(){
        ArrayList<Book> resultArray = new ArrayList<>();
        try {
            String sql = "SELECT * FROM FAVOURITE";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                Music music = MusicData.getMusicMap().get(rs.getString(2));
                resultArray.add(new Book(music.getId(),"favMusic",music.getHinhNen(),music.getTenNhac()));
            }
            return resultArray;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Book> getHisList(){
        ArrayList<Book> resultArray = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HISTORY ORDER BY PLAY_HISTORY DESC";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            int cnt = 0;
            while (rs.next() && cnt < 8){
                Music music = MusicData.getMusicMap().get(rs.getString(2));
                resultArray.add(new Book(music.getId(),"hisMusic",music.getHinhNen(),music.getTenNhac()));
                cnt++;
            }
            return resultArray;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Book> getMusicianData(){
        ArrayList<Book>musicianList = new ArrayList<>();
        ArrayList<Musician>musicianListOld = MusicianData.getListMusician();
        Collections.shuffle(musicianListOld);
        for(int i = 0 ; i < 7; i++){
            if(i < musicianListOld.size()){
                musicianList.add(new Book(musicianListOld.get(i).getId(),"musician",musicianListOld.get(i).getImageId(),musicianListOld.get(i).getName()));
            }
        }
        return musicianList;
    }
}
