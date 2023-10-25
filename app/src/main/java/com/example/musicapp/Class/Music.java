package com.example.musicapp.Class;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "music")
public class Music implements Comparable<Music>{

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String tenNhac; // Tên của bản nhạc
    private String caSi; // Tên của tác giả của bản nhạc
    private int hinhNen; // Tài liệu hình ảnh đại diện cho bản nhạc
    private int sourceMp3; // Tài liệu âm thanh của bản nhạc
    private boolean love; //Lượt thích

    // Constructor (Hàm khởi tạo)
    public Music(String tenNhac, String caSi, int hinhNen, int sourceMp3) {
        this.tenNhac = tenNhac;
        this.caSi = caSi;
        this.hinhNen = hinhNen;
        this.sourceMp3 = sourceMp3;
    }

    public boolean getLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNhac() {
        return tenNhac;
    }

    public void setTenNhac(String tenNhac) {
        this.tenNhac = tenNhac;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }

    public int getHinhNen() {
        return hinhNen;
    }

    public void setHinhNen(int hinhNen) {
        this.hinhNen = hinhNen;
    }

    public int getSourceMp3() {
        return sourceMp3;
    }

    public void setSourceMp3(int sourceMp3) {
        this.sourceMp3 = sourceMp3;
    }

    // Phương thức compareTo dùng để so sánh hai đối tượng Music theo tên bản nhạc
    @Override
    public int compareTo(Music music) {
        // Sử dụng NlpUtils.removeAccent để loại bỏ dấu và thực hiện so sánh theo tên không dấu
        return NlpUtils.removeAccent(this.getTenNhac()).compareTo(NlpUtils.removeAccent(music.getTenNhac()));
    }
}
