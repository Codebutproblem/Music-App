package com.example.musicapp.Class;

import com.example.musicapp.ConnectionSQL.ConClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableData {
    private static Connection connection;
    public static void create(){
        connection = (new ConClass()).conclass();
        try {
            Statement stm = connection.createStatement();
            try {
                stm.execute("SELECT * FROM ACCOUNT");
            }
            catch (Exception ex){
                stm.execute("CREATE TABLE ACCOUNT(USERNAME VARCHAR(255),PASSWORD VARCHAR(255),PRIMARY KEY(USERNAME));");
            }

            try {
                stm.execute("SELECT * FROM MUSIC");
            }
            catch (Exception ex){
                stm.execute("CREATE TABLE MUSIC(MUSIC_ID VARCHAR(255),LIKE_COUNT INT,PRIMARY KEY(MUSIC_ID));");
            }

            try {
                stm.execute("SELECT * FROM FAVOURITE");
            }
            catch (Exception ex){
                stm.execute("CREATE TABLE FAVOURITE(USERNAME VARCHAR(255),MUSIC_ID VARCHAR(255), FOREIGN KEY(USERNAME) REFERENCES ACCOUNT(USERNAME), FOREIGN KEY(MUSIC_ID) REFERENCES MUSIC(MUSIC_ID));");
            }
            try {
                stm.execute("SELECT * FROM HISTORY");
            }
            catch (Exception ex){
                stm.execute("CREATE TABLE HISTORY(USERNAME VARCHAR(255),MUSIC_ID VARCHAR(255),PLAY_HISTORY DATETIME, FOREIGN KEY(USERNAME) REFERENCES ACCOUNT(USERNAME), FOREIGN KEY(MUSIC_ID) REFERENCES MUSIC(MUSIC_ID));");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
