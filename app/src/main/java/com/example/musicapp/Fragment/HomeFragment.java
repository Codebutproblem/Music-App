package com.example.musicapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.musicapp.Class.Musician;
import com.example.musicapp.Adapter.MusicianAdapter;
import com.example.musicapp.R;

import java.util.ArrayList;

// Fragment chứa danh sách các nhạc sĩ được hiển thị trong giao diện Home
public class HomeFragment extends Fragment {
    private RecyclerView rcvMusician;// RecyclerView để hiển thị danh sách nhạc sĩ
    private MusicianAdapter adapter;// Adapter để quản lý danh sách nhạc sĩ
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Gắn layout fragment_home.xml vào fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        AnhXa(view);// Ánh xạ các thành phần trong layout
        adapter = new MusicianAdapter();// Khởi tạo adapter
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rcvMusician.setLayoutManager(staggeredGridLayoutManager);// Thiết lập layout cho RecyclerView
        adapter.setData(this.getActivity(),getListMusician());// Đổ dữ liệu danh sách nhạc sĩ vào adapter
        rcvMusician.setAdapter(adapter);// Gắn adapter vào RecyclerView
        return view;// Trả về giao diện fragment đã được tạo
    }

    // Phương thức để lấy danh sách các nhạc sĩ mẫu
    private ArrayList<Musician> getListMusician() {
        ArrayList<Musician> musicianList = new ArrayList<>();
        musicianList.add(new Musician(R.drawable.imagedragons,"Imagine Dragons"));
        musicianList.add(new Musician(R.drawable.binz,"Binz"));
        musicianList.add(new Musician(R.drawable.sontung,"Sơn Tùng M-TP"));
        musicianList.add(new Musician(R.drawable.denvau,"Đen Vâu"));
        return musicianList;
    }

    // Phương thức để ánh xạ các thành phần trong layout
    private void AnhXa(View view) {
        rcvMusician = view.findViewById(R.id.rcv_musician);
    }
}
