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


public class HomeFragment extends Fragment {
    private RecyclerView rcvMusician;
    private MusicianAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        AnhXa(view);
        adapter = new MusicianAdapter();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rcvMusician.setLayoutManager(staggeredGridLayoutManager);
        adapter.setData(this.getActivity(),getListMusician());
        rcvMusician.setAdapter(adapter);
        return view;
    }

    private ArrayList<Musician> getListMusician() {
        ArrayList<Musician> musicianList = new ArrayList<>();
        musicianList.add(new Musician(R.drawable.imagedragons,"Imagine Dragons"));
        musicianList.add(new Musician(R.drawable.binz,"Binz"));
        musicianList.add(new Musician(R.drawable.sontung,"Sơn Tùng M-TP"));
        musicianList.add(new Musician(R.drawable.denvau,"Đen Vâu"));
        return musicianList;
    }

    private void AnhXa(View view) {
        rcvMusician = view.findViewById(R.id.rcv_musician);
    }
}
