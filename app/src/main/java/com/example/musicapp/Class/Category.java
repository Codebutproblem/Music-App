package com.example.musicapp.Class;

import java.util.ArrayList;

public class Category {
    private String nameCategory;
    private ArrayList<Book>books;

    public Category(String nameCategory, ArrayList<Book> books) {
        this.nameCategory = nameCategory;
        this.books = books;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
