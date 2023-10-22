package com.example.musicapp.Class;

import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MusicianData {

    private static ArrayList<Musician> musicianList;
    private static HashMap<String,Musician>musicianMap;
    public static ArrayList<Musician> getListMusician() {
        musicianList = new ArrayList<>();
        musicianMap = new HashMap<>();
        musicianList.add(new Musician(R.drawable.imagedragons,R.drawable.imagedragons,"Imagine Dragons"));
        musicianList.add(new Musician(R.drawable.binz,R.drawable.binz2,"Binz"));
        musicianList.add(new Musician(R.drawable.sontung,R.drawable.sontung,"Sơn Tùng M-TP"));
        musicianList.add(new Musician(R.drawable.denvau,R.drawable.denvau2,"Đen Vâu"));
        Collections.sort(musicianList);
        for(Musician musician: musicianList){
            musicianMap.put(musician.getName(),musician);
        }
        return musicianList;
    }

    public static HashMap<String, Musician> getMusicianMap() {
        return musicianMap;
    }
}
