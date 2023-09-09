package com.example.musicapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.musicapp.Class.Music;
import com.example.musicapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayMusicActivity extends AppCompatActivity {
    RelativeLayout playMusicLayout;
    private TextView musicName, runTime, totalTime;
    private CircleImageView musicImage;
    private SeekBar seekBar;
    private ImageButton playPauseButton,preButton,nextButton,replayButton;
    private MediaPlayer mediaPlayer;
    private Animation animation;
    private static ArrayList<Music> arrayMusic;
    private int position;
    private boolean replay = false;
    private GestureDetector gestureDetector;
    private int SWIPE_THRESHOLD = 100;
    private int SWIPE_VELOCITY_THRESHOLD = 100;

    public static void setArrayMusic(ArrayList<Music> arrayMusic) {
        PlayMusicActivity.arrayMusic = arrayMusic;
    }

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
                    playPauseButton.setImageResource(R.drawable.ic_play);
                }
                else{
                    mediaPlayer.start();
                    musicImage.startAnimation(animation);
                    playPauseButton.setImageResource(R.drawable.ic_pause);
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
                playPauseButton.setImageResource(R.drawable.ic_pause);
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
                playPauseButton.setImageResource(R.drawable.ic_pause);
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
                    playPauseButton.setImageResource(R.drawable.ic_pause);
                    musicImage.startAnimation(animation);
                    updateRunTime();
                    mediaPlayer.start();
                }
            }
        });
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (replay){
                    replayButton.setImageResource(R.drawable.ic_replay_1);
                    replay = false;
                }
                else{
                    replayButton.setImageResource(R.drawable.ic_replay_2);
                    replay = true;
                }
            }
        });
        gestureDetector = new GestureDetector(this, new MyGesture());
        playMusicLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
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
                        if (!replay){
                            position++;
                            if (position >= arrayMusic.size()){
                                position = 0;
                            }

                            if(mediaPlayer.isPlaying()){
                                mediaPlayer.stop();
                            }
                        }
                        setMusicPlayImage();
                        setMusicName();
                        khoiTaoMediaPlayer();
                        setTimeTotal();
                        updateRunTime();
                        playPauseButton.setImageResource(R.drawable.ic_pause);
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
        Intent it = getIntent();
        position = Integer.parseInt(it.getStringExtra("position"));
    }
    private void AnhXa() {
        playMusicLayout = findViewById(R.id.layout_play_music);
        musicName = findViewById(R.id.headMusicName);
        runTime = findViewById(R.id.runTime);
        totalTime = findViewById(R.id.totalTime);
        musicImage = findViewById(R.id.musicPlayImage);
        seekBar = findViewById(R.id.seekBar);
        playPauseButton = findViewById(R.id.playPauseButton);
        preButton = findViewById(R.id.preButton);
        nextButton = findViewById(R.id.nextButton);
        replayButton = findViewById(R.id.replay_button);
    }
    @Override
    public void onBackPressed() {
        // Dừng phát nhạc khi người dùng bấm nút "Back"
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        super.onBackPressed();
    }
    class MyGesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            //Kéo sang phải
            if(e2.getX() - e1.getX() > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
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
                playPauseButton.setImageResource(R.drawable.ic_pause);
                mediaPlayer.start();
            }
            if(e1.getX() - e2.getX() > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
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
                playPauseButton.setImageResource(R.drawable.ic_pause);
                mediaPlayer.start();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}