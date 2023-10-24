package com.example.musicapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musicapp.Class.Music;
import com.example.musicapp.ConnectionSQL.ConClass;
import com.example.musicapp.Fragment.HomeFragment;
import com.example.musicapp.R;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayMusicActivity extends AppCompatActivity {
    private RelativeLayout playMusicLayout;
    private TextView musicName, runTime, totalTime;
    private CircleImageView musicImage;
    private SeekBar seekBar;
    private ImageButton playPauseButton,preButton,nextButton,replayButton,favBtn;
    private MediaPlayer mediaPlayer;
    private Animation animation;
    private static ArrayList<Music> arrayMusic;
    private Connection connection;
    private String parentPage;
    private int position;
    private boolean replay = false;
    private GestureDetector gestureDetector;
    private int SWIPE_THRESHOLD = 300;
    private int SWIPE_VELOCITY_THRESHOLD = 100;

    // Phương thức để thiết lập danh sách nhạc
    public static void setArrayMusic(ArrayList<Music> arrayMusic) {
        PlayMusicActivity.arrayMusic = arrayMusic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        AnhXa();
        setAnimation();
        getData();
        setMusicPlayImage();
        setMusicName();
        khoiTaoMediaPlayer();
        setTimeTotal();
        if(LoginActivity.checkLogin()){
            ConnectionSQL();
            setFavButton();
        }

        // Xử lý sự kiện khi nút Play/Pause được nhấn
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
                    addHistory();
                }
                updateRunTime();
            }
        });

        // Xử lý sự kiện khi nút Next được nhấn
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextSong();
            }
        });

        // Xử lý sự kiện khi nút Previous được nhấn
        preButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preSong();
            }
        });

        // Xử lý sự kiện khi người dùng thay đổi vị trí trên SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // Xử lý khi người dùng thay đổi vị trí SeekBar
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Xử lý khi người dùng bắt đầu chạm vào SeekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Xử lý khi người dùng kết thúc chạm vào SeekBar
                mediaPlayer.seekTo(seekBar.getProgress());
                if(!mediaPlayer.isPlaying()){
                    playPauseButton.setImageResource(R.drawable.ic_pause);
                    musicImage.startAnimation(animation);
                    updateRunTime();
                    addHistory();
                    mediaPlayer.start();
                }
            }
        });

        // Xử lý sự kiện khi nút Replay được nhấn
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
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favBtnClick();
            }
        });
        // Thiết lập Gesture Detector để nhận diện cử chỉ vuốt trên màn hình
        gestureDetector = new GestureDetector(this, new MyGesture());
        playMusicLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    private void addHistory() {
        if (!LoginActivity.checkLogin()){
            return;
        }
        String sql = "INSERT INTO HISTORY VALUES(?,?,CURRENT_TIMESTAMP)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,LoginActivity.getUsername());
            stm.setString(2,arrayMusic.get(position).getId());
            stm.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void ConnectionSQL() {
        ConClass con = new ConClass();
        connection = con.conclass();
    }
    private void setFavButton() {
        if(!LoginActivity.checkLogin()){
            return;
        }
        if(checkContains(arrayMusic.get(position).getId())){
            favBtn.setImageResource(R.drawable.ic_favorite);
        }
        else{
            favBtn.setImageResource(R.drawable.ic_favorite_border);
        }
    }

    private int cntLike(String id){
        String sql = "SELECT LIKE_COUNT FROM MUSIC WHERE MUSIC_ID = '" + id + "'";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()){
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addLike(String id){
        String sql = "UPDATE MUSIC SET LIKE_COUNT = ? WHERE MUSIC_ID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,cntLike(id)+1);
            stm.setString(2,id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void subLike(String id){
        String sql = "UPDATE MUSIC SET LIKE_COUNT = ? WHERE MUSIC_ID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            if (cntLike(id) > 0){
                stm.setInt(1,cntLike(id)-1);
            }
            else{
                stm.setInt(1,0);
            }
            stm.setString(2,id);
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void favBtnClick() {
        if(!LoginActivity.checkLogin()){
            Toast.makeText(this, "Bạn chưa đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checkContains(arrayMusic.get(position).getId())){
            favBtn.setImageResource(R.drawable.ic_favorite_border);
            deleteMusic(arrayMusic.get(position).getId());
            subLike(arrayMusic.get(position).getId());
            Toast.makeText(this, "Đã xóa khỏi mục yêu thích", Toast.LENGTH_SHORT).show();
        }
        else{
            favBtn.setImageResource(R.drawable.ic_favorite);
            insertMusic(arrayMusic.get(position).getId());
            addLike(arrayMusic.get(position).getId());
            Toast.makeText(this, "Đã thêm vào mục yêu thích", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteMusic(String id){
        String sql = "DELETE FROM FAVOURITE WHERE MUSIC_ID = '" + id + "' AND USERNAME = '" + LoginActivity.getUsername() +"'" ;
        try {
            Statement stm = connection.createStatement();
            stm.execute(sql);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void insertMusic(String id){
        String sql = "INSERT INTO FAVOURITE VALUES(?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,LoginActivity.getUsername());
            stm.setString(2,id);
            stm.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean checkContains(String id){
        String query = "SELECT * FROM FAVOURITE WHERE USERNAME = '"+ LoginActivity.getUsername() + "' AND MUSIC_ID = '" + id + "'";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if(rs.next()){
                return true;
            }
            return false;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Phương thức để chuyển đến bài hát kế tiếp trong danh sách
    private void nextSong(){
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
        setFavButton();
        addHistory();
        musicImage.startAnimation(animation);
        playPauseButton.setImageResource(R.drawable.ic_pause);
        mediaPlayer.start();
    }

    // Phương thức để chuyển đến bài hát trước đó trong danh sách
    private void preSong(){
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
        setFavButton();
        addHistory();
        musicImage.startAnimation(animation);
        playPauseButton.setImageResource(R.drawable.ic_pause);
        mediaPlayer.start();
    }

    // Phương thức để thiết lập rotate animation
    private void setAnimation() {
        animation = AnimationUtils.loadAnimation(this,R.anim.rotate);
    }

    // Phương thức để cập nhật thời gian hiện tại của bài hát đang phát
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
                        // Xử lý sự kiện khi bài hát kết thúc
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
        // Phương thức để thiết lập thời gian tổng của bài hát
        SimpleDateFormat total = new SimpleDateFormat("mm:ss");
        totalTime.setText(total.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }
    private void khoiTaoMediaPlayer(){
        mediaPlayer = new MediaPlayer();
        // Khởi tạo MediaPlayer để phát nhạc từ tệp âm thanh tại vị trí hiện tại trong danh sách nhạc
        try {
            do{
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(arrayMusic.get(position).getLinkMp3());
                mediaPlayer.prepare();
            }while (mediaPlayer == null);
        } catch (Exception e) {
            Log.e("ERROR",e.getMessage());
        }
    }
    private void setMusicName() {
        // Đặt tên bài hát hiện tại lên giao diện
        musicName.setText(arrayMusic.get(position).getTenNhac());
    }

    private void setMusicPlayImage() {
        // Đặt hình ảnh của bài hát hiện tại lên giao diện
        musicImage.setImageResource(arrayMusic.get(position).getHinhNen());
    }

    private void getData() {
        // Lấy dữ liệu vị trí bài hát được chọn từ Intent
        Intent it = getIntent();
        position = Integer.parseInt(it.getStringExtra("position"));
        parentPage = it.getStringExtra("from");
    }
    private void AnhXa() {
        // Ánh xạ các thành phần giao diện
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
        favBtn = findViewById(R.id.fav_btn);
    }
    @Override
    public void onBackPressed() {
        // Dừng phát nhạc khi người dùng bấm nút "Back"
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        if(parentPage.equals("Search")){
            Intent it = new Intent(this,MainActivity.class);
            it.putExtra("user",LoginActivity.getUsername());
            startActivity(it);
        }
        if(parentPage.equals("MusicianPlaylist")){
            Intent it = new Intent(this,MusicianPlaylistActivity.class);
            it.putExtra("musician",arrayMusic.get(position).getCaSi());
            startActivity(it);
        }
        if (parentPage.equals("Library")){
            Intent it = new Intent(this,MainActivity.class);
            it.putExtra("user",LoginActivity.getUsername());
            startActivity(it);
        }
        super.onBackPressed();
    }
    class MyGesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            // Xử lý cử chỉ vuốt trái hoặc phải trên màn hình
            if(e2.getX() - e1.getX() > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
                nextSong();// Chuyển đến bài hát tiếp theo khi vuốt sang phải
            }
            if(e1.getX() - e2.getX() > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
                preSong();// Chuyển đến bài hát trước đó khi vuốt sang trái
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}