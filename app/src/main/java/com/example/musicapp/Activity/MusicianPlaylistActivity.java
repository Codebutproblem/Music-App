package com.example.musicapp.Activity;
//Giao diện chứa các bài nhạc của từng ca sỹ
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.musicapp.Adapter.MusicAdapter;
import com.example.musicapp.Class.Music;
import com.example.musicapp.Data.MusicData;
import com.example.musicapp.Class.Musician;
import com.example.musicapp.Data.MusicianData;
import com.example.musicapp.DataBase.MusicDataBase;
import com.example.musicapp.R;

import java.util.Collections;
import java.util.List;

public class MusicianPlaylistActivity extends AppCompatActivity{
    // Khai báo các biến và thành phần giao diện
    private RelativeLayout layoutPlayList;
    private TextView name;
    private RecyclerView rcvMusic;
    private static List<Music> arrayMusic;
    private Intent it;
    private static MusicAdapter adapter;

    private String parentPage;

    private static Context context;
    public static Context getContext(){
        return context;
    }

    // Phương thức để lấy danh sách nhạc
    public static List<Music> getArrayMusic() {
        return arrayMusic;
    }

    // Phương thức để cài đặt danh sách nhạc
    public static void setArrayMusic(List<Music>newArrayMusic){
        arrayMusic = newArrayMusic;
    }

    public static void setAdapter(List<Music>list){
        if (adapter != null ){
            Collections.sort(list);
            adapter.setData(context,list);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musician_playlist_activity);
        context = this;
        // Ánh xạ các thành phần giao diện
        AnhXa();

        Intent it = getIntent();

        String id = it.getStringExtra("musician");
        String musicianName = it.getStringExtra("musician");
        parentPage = it.getStringExtra("parent");

        Musician musician = MusicianData.getMusicianHashMap().get(id);
        arrayMusic = MusicData.musicianList(musicianName, MusicDataBase.getInstance(this).musicDao().getMusicArray());
        Collections.sort(arrayMusic);
        name.setText(musician.getName());
        layoutPlayList.setBackgroundResource(musician.getImageBg());

        adapter = new MusicAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcvMusic.setLayoutManager(linearLayoutManager);
        adapter.setData(this,arrayMusic);
        rcvMusic.setAdapter(adapter);


    }

    // Ánh xạ các thành phần giao diện
    private void AnhXa() {
        layoutPlayList = findViewById(R.id.layout_play_list);
        name = findViewById(R.id.text_name);
        rcvMusic = findViewById(R.id.play_list);
    }
    @Override
    public void onBackPressed() {
        if (parentPage.equals("home")){
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }
        super.onBackPressed();
    }
}