package com.example.musicapp;


public class Music {
    private String tenNhac; //Ten nhac
    private String tacGia; //Ten tac gia
    private int hinhNen; //file hinh anh am nhac
    private int file; //file am thanh

    //Constructor
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
