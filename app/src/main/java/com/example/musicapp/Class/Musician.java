package com.example.musicapp.Class;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "musician")
public class Musician implements Serializable,Comparable<Musician> {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int imageId; // ID của tài liệu hình ảnh đại diện cho nghệ sĩ
    private int imageBg;
    private String name; // Tên của nghệ sĩ

    // Constructor để khởi tạo một đối tượng Musician với thông tin hình ảnh và tên
    public Musician(int imageId, int imageBg, String name) {
        this.imageId = imageId;
        this.imageBg = imageBg;
        this.name = name;
    }
    //Các phương thức Getter và Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getImageBg() {
        return imageBg;
    }
    public void setImageBg(int imageBg) {
        this.imageBg = imageBg;
    }
    public int getImageId() {
        return imageId;
    }
    public String getName() {
        return name;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Musician o) {
        return this.name.compareTo(o.name);
    }
}
