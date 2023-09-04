package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayMusicActivity extends AppCompatActivity {

    private TextView musicName, runTime, totalTime;
    private ImageView musicImage;
    private SeekBar seekBar;
    private ImageButton playPauseButton,preButton,nextButton;
    private MediaPlayer mediaPlayer;
    private Animation animation;
    private ArrayList<Music> arrayMusic;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        setAnimation();
        AnhXa();
        getData();
        setMusicPlayImage();
        setMusicName();
        khoiTaoMediaPlayer();
        setTimeTotal();
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    animation.cancel();
                    musicImage.setAnimation(null);
                    playPauseButton.setImageResource(R.drawable.play);
                }
                else{
                    mediaPlayer.start();
                    musicImage.startAnimation(animation);
                    playPauseButton.setImageResource(R.drawable.pause);
                }
                updateRunTime();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if (position >= arrayMusic.size()){
                    position = 0;
                }

                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                khoiTaoMediaPlayer();
                setMusicPlayImage();
                setMusicName();
                setTimeTotal();
                updateRunTime();
                musicImage.startAnimation(animation);
                playPauseButton.setImageResource(R.drawable.pause);
                mediaPlayer.start();
            }
        });
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if(position < 0){
                    position = arrayMusic.size()-1;
                }

                if ((mediaPlayer.isPlaying())){
                    mediaPlayer.stop();
                }
                khoiTaoMediaPlayer();
                setMusicPlayImage();
                setMusicName();
                setTimeTotal();
                updateRunTime();
                musicImage.startAnimation(animation);
                playPauseButton.setImageResource(R.drawable.pause);
                mediaPlayer.start();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                if(!mediaPlayer.isPlaying()){
                    playPauseButton.setImageResource(R.drawable.pause);
                    updateRunTime();
                    mediaPlayer.start();
                }
            }
        });
    }

    private void setAnimation() {
        animation = AnimationUtils.loadAnimation(this,R.anim.rotate);
    }

    private void updateRunTime(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat running = new SimpleDateFormat("mm:ss");
                runTime.setText(running.format(mediaPlayer.getCurrentPosition()));
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position >= arrayMusic.size()){
                            position = 0;
                        }

                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        setMusicPlayImage();
                        setMusicName();
                        khoiTaoMediaPlayer();
                        setTimeTotal();
                        updateRunTime();
                        playPauseButton.setImageResource(R.drawable.pause);
                        mediaPlayer.start();
                    }
                });
                handler.postDelayed(this,500);
            }
        },100);
    }
    private void setTimeTotal(){
        SimpleDateFormat total = new SimpleDateFormat("mm:ss");
        totalTime.setText(total.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }
    private void khoiTaoMediaPlayer(){
        mediaPlayer = MediaPlayer.create(PlayMusicActivity.this, arrayMusic.get(position).getFile());
    }
    private void setMusicName() {
        musicName.setText(arrayMusic.get(position).getTenNhac());
    }

    private void setMusicPlayImage() {
        musicImage.setImageResource(arrayMusic.get(position).getHinhNen());
    }

    private void getData() {
        arrayMusic = SearchFragment.getArrayMusic();
        Intent it = getIntent();
        position = Integer.parseInt(it.getStringExtra("position"));
    }
    private void AnhXa() {
        musicName = (TextView) findViewById(R.id.headMusicName);
        runTime= (TextView) findViewById(R.id.runTime);
        totalTime = (TextView) findViewById(R.id.totalTime);
        musicImage = (ImageView) findViewById(R.id.musicPlayImage);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        playPauseButton = (ImageButton) findViewById(R.id.playPauseButton);
        preButton = (ImageButton) findViewById(R.id.preButton);
        nextButton = (ImageButton) findViewById(R.id.nextButton);
    }
    @Override
    public void onBackPressed() {
        // Dừng phát nhạc khi người dùng bấm nút "Back"
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        super.onBackPressed();
    }
}