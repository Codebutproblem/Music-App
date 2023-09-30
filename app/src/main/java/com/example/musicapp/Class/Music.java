package com.example.musicapp.Class;


public class Music implements Comparable<Music>{
    private String tenNhac; // Tên của bản nhạc
    private String tacGia; // Tên của tác giả của bản nhạc
    private int hinhNen; // Tài liệu hình ảnh đại diện cho bản nhạc
    private int file; // Tài liệu âm thanh của bản nhạc

    // Constructor (Hàm khởi tạo)
    public Music(String tenNhac, String tacGia, int hinhNen, int file) {
        this.tenNhac = tenNhac;
        this.tacGia = tacGia;
        this.hinhNen = hinhNen;
        this.file = file;
    }

    //Các phương thức Getter và Setter
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


    // Phương thức compareTo dùng để so sánh hai đối tượng Music theo tên bản nhạc
    @Override
    public int compareTo(Music music) {
        // Sử dụng NlpUtils.removeAccent để loại bỏ dấu và thực hiện so sánh theo tên không dấu
        return NlpUtils.removeAccent(this.getTenNhac()).compareTo(NlpUtils.removeAccent(music.getTenNhac()));
    }
}
