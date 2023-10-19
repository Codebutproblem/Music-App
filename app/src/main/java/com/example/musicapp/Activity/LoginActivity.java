package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.musicapp.ConnectionSQL.ConClass;
import com.example.musicapp.R;
import com.google.android.material.button.MaterialButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    private EditText eUsername,ePassword;
    private Button loginBtn;
    private TextView tvtRegister,alertLogin;
    private Connection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnhXa();// Gán các UI element vào biến tương ứng

        // Thiết lập sự kiện click cho nút đăng nhập
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConClass con = new ConClass();
                connection = con.conclass();
                String user = String.valueOf(eUsername.getText());
                String pass = String.valueOf(ePassword.getText());
                // Kiểm tra đăng nhập có thành công không
                if(checkLogin(user,pass) && !user.equals("") && !user.equals("Dang nhap") && !pass.equals("")){
                    openMainPage(user);// Mở trang chính cho người dùng
                }
                else{
                    alertLogin.setText("Log in failed");
                }
            }
        });

        // Thiết lập sự kiện click cho văn bản "Đăng ký"
        tvtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterPage();// Mở trang đăng ký
            }
        });
    }

    // Mở trang đăng ký
    private void openRegisterPage() {
        Intent it = new Intent(this, RegisterActivity.class);
        startActivity(it);
    }

    // Mở trang chính với thông tin người dùng
    private void openMainPage(String user){
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("user",user);
        startActivity(it);
    }

    // Kiểm tra tính hợp lệ của thông tin đăng nhập bằng cách truy vấn cơ sở dữ liệu
    private  boolean checkLogin(String user,String pass){
        String sql = "SELECT * FROM ACCOUNT";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                if(rs.getString(1).equals(user) && rs.getString(2).equals(pass)){
                    return true;// Thông tin đăng nhập hợp lệ
                }
            }
            return false;// Thông tin đăng nhập không hợp lệ
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Gán các UI element vào biến tương ứng
    private void AnhXa() {
        eUsername = findViewById(R.id.username);
        ePassword = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginbtn);
        tvtRegister = findViewById(R.id.register_page);
        alertLogin = findViewById(R.id.alert);
    }
}