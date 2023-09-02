package com.example.musicapp;

public class Music {
    private String tenNhac;
    private String tacGia;
    private int hinhNen;
    private int file;

    public Music(String tenNhac, String tacGia, int hinhNen, int file) {
        this.tenNhac = tenNhac;
        this.tacGia = tacGia;
        this.hinhNen = hinhNen;
        this.file = file;
    }
    public int getFile() {
        return file;
    }

    public String getTenNhac() {
        return tenNhac;
    }

    public String getTacGia() {
        return tacGia;
    }

    public int getHinhNen() {
        return hinhNen;
    }
}
