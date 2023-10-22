package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.ConnectionSQL.ConClass;
import com.example.musicapp.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {
    private EditText txtUsername,tPassword,ctPassword;
    private Button regBtn;
    private TextView tNotification;
    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();// Gán các UI element vào biến tương ứng

        // Thiết lập sự kiện click cho nút đăng ký
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConClass con = new ConClass();
                connection = con.conclass();
                String user = String.valueOf(txtUsername.getText());
                String pass = String.valueOf(tPassword.getText());
                String cpass = String.valueOf(ctPassword.getText());

                // Kiểm tra xem tài khoản đã tồn tại chưa
                if (checkContainsAccount(user)){
                    tNotification.setText("Your username already exists");
                }
                // Kiểm tra xem mật khẩu và mật khẩu xác nhận khớp nhau không
                else if(!pass.equals(cpass)){
                    tNotification.setText("You have confirmed the wrong password");
                }
                // Nếu thông tin hợp lệ, thêm dữ liệu vào cơ sở dữ liệu và hiển thị thông báo thành công
                else{
                    insertData(user,pass);
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    openLoginPage();
                }
                // Xóa dữ liệu trên giao diện sau khi đăng ký
                txtUsername.setText("");
                tPassword.setText("");
                ctPassword.setText("");
            }
        });

    }

    // Mở trang đăng nhập
    private void openLoginPage() {
        Intent it = new Intent(this, LoginActivity.class);
        startActivity(it);
    }

    // Thêm dữ liệu người dùng vào cơ sở dữ liệu
    private void insertData(String user, String pass) {
        String query = "INSERT INTO ACCOUNT VALUES(?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1,user);
            stm.setString(2,pass);
            stm.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Kiểm tra xem tài khoản đã tồn tại trong cơ sở dữ liệu chưa
    private boolean checkContainsAccount(String user){
        String sql = "SELECT * FROM ACCOUNT";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                if(rs.getString(1).equals(user)) return true;
            }
            return false;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Gán các UI element vào biến tương ứng
    private void AnhXa() {
        txtUsername = findViewById(R.id.usernameR);
        tPassword = findViewById(R.id.passwordR);
        ctPassword = findViewById(R.id.passwordRC);
        tNotification = findViewById(R.id.notification);
        regBtn = findViewById(R.id.registerbtn);
    }
}