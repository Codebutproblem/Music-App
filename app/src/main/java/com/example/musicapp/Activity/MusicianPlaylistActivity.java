package com.example.musicapp.Activity;
//Giao diện chứa các bài nhạc của từng ca sỹ
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
import com.example.musicapp.Class.MusicianData;
import com.example.musicapp.R;

import java.util.ArrayList;

public class MusicianPlaylistActivity extends AppCompatActivity{
    // Khai báo các biến và thành phần giao diện
    RelativeLayout layoutPlayList;
    TextView name;
    private ListView lvMusic;
    private static ArrayList<Music> arrayMusic;
    private Intent it;
    private MusicAdapter adapter;

    // Phương thức để lấy danh sách nhạc
    public static ArrayList<Music> getArrayMusic() {
        return arrayMusic;
    }

    // Phương thức để cài đặt danh sách nhạc
    public static void setArrayMusic(ArrayList<Music>newArrayMusic){
        arrayMusic = newArrayMusic;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musician_playlist_activity);

        // Ánh xạ các thành phần giao diện
        AnhXa();

        Intent it = getIntent();
        String musicianName = it.getStringExtra("musician");

        Musician musician = MusicianData.getMusicianMap().get(musicianName);
        arrayMusic = MusicData.musicianList(musicianName,MusicData.getArrayMusic());
        name.setText(musicianName);
        layoutPlayList.setBackgroundResource(musician.getImageBg());


        // Tạo adapter cho danh sách nhạc
        adapter = new MusicAdapter(MusicianPlaylistActivity.this, R.layout.music_line, arrayMusic);
        lvMusic.setAdapter(adapter);

        // Xử lý sự kiện khi người dùng chọn một bài hát
        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openPlayPage(i);
            }
        });
    }

    // Mở trang chơi nhạc với bài hát được chọn
    private void openPlayPage(int i){
        PlayMusicActivity.setArrayMusic(arrayMusic);
        it = new Intent(this,PlayMusicActivity.class);
        it.putExtra("position",i + "");
        it.putExtra("from","MusicianPlaylist");
        startActivity(it);
    }

    // Ánh xạ các thành phần giao diện
    private void AnhXa() {
        layoutPlayList = findViewById(R.id.layout_play_list);
        name = findViewById(R.id.text_name);
        lvMusic = findViewById(R.id.play_list);
    }
    @Override
    public void onBackPressed() {
        // Dừng phát nhạc khi người dùng bấm nút "Back"
        Intent it = new Intent(this,MainActivity.class);
        it.putExtra("user",LoginActivity.getUsername());
        startActivity(it);
        super.onBackPressed();
    }
}