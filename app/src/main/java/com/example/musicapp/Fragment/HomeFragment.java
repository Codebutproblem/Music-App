package com.example.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.musicapp.Activity.LoginActivity;
import com.example.musicapp.Adapter.MusicianAdapter;
import com.example.musicapp.Data.MusicianData;
import com.example.musicapp.R;

// Fragment chứa danh sách các nhạc sĩ được hiển thị trong giao diện Home
public class HomeFragment extends Fragment {
    private RecyclerView rcvMusician;// RecyclerView để hiển thị danh sách nhạc sĩ
    private MusicianAdapter adapter;// Adapter để quản lý danh sách nhạc sĩ
    private static TextView userName; //Lời chao username
    private ImageButton logoutBtn;//Nút đăng xuất

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Gắn layout fragment_home.xml vào fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        AnhXa(view);// Ánh xạ các thành phần trong layout
        if (!LoginActivity.checkLogin()){
            userName.setText("Đăng nhập");
        }
        else{
            setUserName();
        }
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!LoginActivity.checkLogin()){
                    openLoginPage();
                }
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            //Xử lý sự kiện khi nút đang xuất đươợc nhấn
            @Override
            public void onClick(View v) {
                if(!LoginActivity.checkLogin()){
                    Toast.makeText(getActivity(), "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
                }
                else{
                    openLoginPage();
                }
            }
        });
        adapter = new MusicianAdapter();// Khởi tạo adapter
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rcvMusician.setLayoutManager(staggeredGridLayoutManager);// Thiết lập layout cho RecyclerView
        adapter.setData(this.getActivity(), MusicianData.getListMusician());// Đổ dữ liệu danh sách nhạc sĩ vào adapter
        rcvMusician.setAdapter(adapter);// Gắn adapter vào RecyclerView
        return view;// Trả về giao diện fragment đã được tạo
    }
    //Quay về trang đăng nhập
    private void openLoginPage() {
        Intent it = new Intent(this.getActivity(), LoginActivity.class);
        getActivity().finish();
        startActivity(it);
    }

    //Đặt lời chào tới user
    private void setUserName() {
        Intent it = getActivity().getIntent();
        String username = it.getStringExtra("user");
        userName.setText(username);
    }

    // Phương thức để ánh xạ các thành phần trong layout
    private void AnhXa(View view) {
        userName = view.findViewById(R.id.txtwelcome);
        logoutBtn = view.findViewById(R.id.logout);
        rcvMusician = view.findViewById(R.id.rcv_musician);
    }
}
