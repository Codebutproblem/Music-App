package com.example.musicapp.Class;

import com.example.musicapp.R;

import java.util.ArrayList;

public class MusicianData {
    public static ArrayList<Musician> getListMusician() {
        ArrayList<Musician> musicianList = new ArrayList<>();
        musicianList.add(new Musician(R.drawable.imagedragons,R.drawable.imagedragons,"Imagine Dragons"));
        musicianList.add(new Musician(R.drawable.binz,R.drawable.binz2,"Binz"));
        musicianList.add(new Musician(R.drawable.sontung,R.drawable.sontung,"Sơn Tùng M-TP"));
        musicianList.add(new Musician(R.drawable.denvau,R.drawable.denvau2,"Đen Vâu"));
        return musicianList;
    }
}
