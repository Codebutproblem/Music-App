package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.musicapp.Adapter.MusicAdapter;
import com.example.musicapp.Class.Music;
import com.example.musicapp.Class.MusicData;
import com.example.musicapp.Class.Musician;
import com.example.musicapp.R;

import java.util.ArrayList;

public class MusicianPlaylistActivity extends AppCompatActivity{

    RelativeLayout layoutPlayList;
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
        name = findViewById(R.id.text_name);
        lvMusic = findViewById(R.id.play_list);
    }
}