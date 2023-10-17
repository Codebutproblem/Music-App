package com.example.musicapp.Class;


public class Music implements Comparable<Music>{

    private String id;
    private String tenNhac; // Tên của bản nhạc
    private String caSi; // Tên của tác giả của bản nhạc
    private int hinhNen; // Tài liệu hình ảnh đại diện cho bản nhạc
    private int file; // Tài liệu âm thanh của bản nhạc

    // Constructor (Hàm khởi tạo)

    public Music(String tenNhac, String caSi, int hinhNen, int file) {
        this.tenNhac = tenNhac;
        this.caSi = caSi;
        this.hinhNen = hinhNen;
        this.file = file;
    }

    //Các phương thức Getter và Setter


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }

    // Phương thức compareTo dùng để so sánh hai đối tượng Music theo tên bản nhạc
    @Override
    public int compareTo(Music music) {
        // Sử dụng NlpUtils.removeAccent để loại bỏ dấu và thực hiện so sánh theo tên không dấu
        return NlpUtils.removeAccent(this.getTenNhac()).compareTo(NlpUtils.removeAccent(music.getTenNhac()));
    }
}
