package com.example.musicapp.Class;


public class Music implements Comparable<Music>{
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

    @Override
    public int compareTo(Music music) {
        return NlpUtils.removeAccent(this.getTenNhac()).compareTo(NlpUtils.removeAccent(music.getTenNhac()));
    }
}
