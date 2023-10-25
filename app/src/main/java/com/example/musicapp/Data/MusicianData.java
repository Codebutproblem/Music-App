package com.example.musicapp.Data;

import com.example.musicapp.Activity.FlashActivity;
import com.example.musicapp.Class.Musician;
import com.example.musicapp.DataBase.MusicianDataBase;
import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MusicianData {

    private static List<Musician> musicianList;
    private static HashMap<String,Musician>musicianHashMap;
    public static void setListMusician() {
        musicianList = new ArrayList<>();
        musicianHashMap = new HashMap<>();

        //Thêm nhạc sĩ bằng cách thêm Object ở đây, chạy xong rồi xóa đi cũng được vì nó lưu vào database rồi
        musicianList.add(new Musician(R.drawable.imagedragons,R.drawable.imagedragons,"Imagine Dragons"));
        musicianList.add(new Musician(R.drawable.binz,R.drawable.binz2,"Binz"));
        musicianList.add(new Musician(R.drawable.sontung,R.drawable.sontung,"Sơn Tùng M-TP"));
        musicianList.add(new Musician(R.drawable.denvau,R.drawable.denvau2,"Đen Vâu"));
        //................
        //................




        for(Musician musician: musicianList){
            if (!checkContains(musician)){
                MusicianDataBase.getInstance(FlashActivity.getContext()).musicianDao().insertMusician(musician);
            }

        }
        musicianList = MusicianDataBase.getInstance(FlashActivity.getContext()).musicianDao().getMusicianArray();
        for(Musician musician: musicianList){
            musicianHashMap.put(musician.getName(),musician);
        }
    }
    private static boolean checkContains(Musician musician){
        List<Musician> list = MusicianDataBase.getInstance(FlashActivity.getContext()).musicianDao().checkExist(musician.getName());
        return list != null && !list.isEmpty();
    }
    public static HashMap<String, Musician> getMusicianHashMap() {
        return musicianHashMap;
    }
}
