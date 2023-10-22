package com.example.musicapp.Class;

import java.io.Serializable;

public class Book implements Serializable {

    private String id;
    private String category;
    private int resourceId;
    private String title;

    public Book(String id, String category, int resourceId, String title) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
