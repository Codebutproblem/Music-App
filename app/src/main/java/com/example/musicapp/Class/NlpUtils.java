package com.example.musicapp.Class;

import java.text.Normalizer;
import java.util.regex.Pattern;

// Lớp NlpUtils chứa các phương thức hỗ trợ xử lý văn bản (NLP) trong ứng dụng
public class NlpUtils{

    // Phương thức để loại bỏ dấu từ một chuỗi văn bản
    public static String removeAccent(String s) {
        // Chuẩn hóa chuỗi s bằng cách chuyển các ký tự có dấu thành ký tự không dấu
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);

        // Tạo một biểu thức chính quy để tìm và loại bỏ các dấu diacritical (dấu thanh) trong chuỗi
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        // Sử dụng biểu thức chính quy để thay thế các ký tự có dấu thành ký tự không dấu
        temp = pattern.matcher(temp).replaceAll("");

        // Thay thế ký tự "đ" thành "d" (được sử dụng trong tiếng Việt)
        return temp.replaceAll("đ", "d"); 
    }
}