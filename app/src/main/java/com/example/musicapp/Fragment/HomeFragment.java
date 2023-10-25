package com.example.musicapp.Fragment;

import android.content.Context;
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

import com.example.musicapp.Activity.MainActivity;
import com.example.musicapp.Adapter.MusicianAdapter;
import com.example.musicapp.Class.Musician;
import com.example.musicapp.Data.MusicianData;
import com.example.musicapp.DataBase.MusicianDataBase;
import com.example.musicapp.R;

import java.util.Collections;
import java.util.List;

// Fragment chứa danh sách các nhạc sĩ được hiển thị trong giao diện Home
public class HomeFragment extends Fragment {
    private RecyclerView rcvMusician;// RecyclerView để hiển thị danh sách nhạc sĩ
    private MusicianAdapter adapter;// Adapter để quản lý danh sách nhạc sĩ

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Gắn layout fragment_home.xml vào fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        AnhXa(view);
        adapter = new MusicianAdapter();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rcvMusician.setLayoutManager(staggeredGridLayoutManager);
        List<Musician> musicianList =  MusicianDataBase.getInstance(getActivity()).musicianDao().getMusicianArray();
        Collections.sort(musicianList);
        adapter.setData(getActivity(), musicianList);
        rcvMusician.setAdapter(adapter);
        return view;
    }
    //Quay về trang đăng nhập

    // Phương thức để ánh xạ các thành phần trong layout
    private void AnhXa(View view) {
        rcvMusician = view.findViewById(R.id.rcv_musician);
    }
}
