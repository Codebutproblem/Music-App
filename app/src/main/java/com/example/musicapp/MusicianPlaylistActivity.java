package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MusicianPlaylistActivity extends AppCompatActivity{

    RelativeLayout layoutPlayList;
    ImageButton play;
    TextView name;
    private ListView lvMusic;
    private static ArrayList<Music> arrayMusic;
    private Intent it;
    private MusicAdapter adapter;
    public static ArrayList<Music> getArrayMusic() {
        return arrayMusic;
    }
    public static void setArrayMusic(ArrayList<Music>newArrayMusic){
        arrayMusic = newArrayMusic;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musician_playlist_activity);
        AnhXa();
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Musician musician = (Musician) bundle.get("object_musician");
        String musicianName = musician.getName();
        arrayMusic = MusicData.musicianList(musicianName,MusicData.getArrayMusic());
        name.setText(musicianName);
        layoutPlayList.setBackgroundResource(musician.getImageId());
        adapter = new MusicAdapter(MusicianPlaylistActivity.this, R.layout.music_line, arrayMusic);
        lvMusic.setAdapter(adapter);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlayPage(0);
            }
        });
        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openPlayPage(i);
            }
        });
    }

    private void openPlayPage(int i){
        PlayMusicActivity.setArrayMusic(arrayMusic);
        it = new Intent(this,PlayMusicActivity.class);
        it.putExtra("position",i + "");
        startActivity(it);
    }
    private void AnhXa() {
        layoutPlayList = findViewById(R.id.layout_play_list);
        play = findViewById(R.id.button_play_list);
        name = findViewById(R.id.text_name);
        lvMusic = findViewById(R.id.play_list);
    }
}