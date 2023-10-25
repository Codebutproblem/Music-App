package com.example.musicapp.Class;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "history")
public class Book implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int key;
    private int id;
    private String category;
    private int resourceId;
    private String title;
    public Book(){

    }
    public Book(int id, String category, int resourceId, String title) {
        this.id = id;
        this.category = category;
        this.resourceId = resourceId;
        this.title = title;
    }
    public Book(String category, int resourceId, String title) {
        this.category = category;
        this.resourceId = resourceId;
        this.title = title;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
