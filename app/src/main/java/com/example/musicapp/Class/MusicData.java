package com.example.musicapp.Class;

import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.Collections;

public class MusicData {

    // Phương thức để lấy danh sách các bản nhạc
    public static ArrayList<Music> getArrayMusic(){
        ArrayList<Music> arrayMusic = new ArrayList<>();
        // Thêm các bản nhạc vào danh sách
        arrayMusic.add(new Music("Imagine Dragons - Enemy", "Imagine Dragons", R.drawable.enemy_arcane,R.raw.enemy_arcane));
        arrayMusic.add(new Music("Imagine Dragons - Demons","Imagine Dragons",R.drawable.demons,R.raw.demons));
        arrayMusic.add(new Music("Imagine Dragons - Believer","Imagine Dragons",R.drawable.believer,R.raw.believer));
        arrayMusic.add(new Music("Imagine Dragons - Thunder","Imagine Dragons",R.drawable.thunder,R.raw.thunder));
        arrayMusic.add(new Music("Imagine Dragons - Bad Liar","Imagine Dragons",R.drawable.bad_liar,R.raw.bad_liar));
        arrayMusic.add(new Music("Imagine Dragons - Radioactive","Imagine Dragons",R.drawable.radioactive,R.raw.radioactive));
        arrayMusic.add(new Music("Imagine Dragons - Natural","Imagine Dragons",R.drawable.natural,R.raw.natural));
        arrayMusic.add(new Music("Imagine Dragons - Bones","Imagine Dragons",R.drawable.bones,R.raw.bones));
        arrayMusic.add(new Music("Imagine Dragons - Whatever It Takes","Imagine Dragons",R.drawable.whatever_it_takes,R.raw.whatever_it_takes));
        arrayMusic.add(new Music("Imagine Dragons - Warriors","Imagine Dragons",R.drawable.warriors,R.raw.warriors));
        arrayMusic.add(new Music("Sơn Tùng - Âm thầm bên em","Sơn Tùng M-TP",R.drawable.am_tham_ben_em,R.raw.am_tham_ben_em));
        arrayMusic.add(new Music("Sơn Tùng - Có chắc yêu là đây","Sơn Tùng M-TP",R.drawable.co_chac_yeu_la_day,R.raw.co_chac_yeu_la_day));
        arrayMusic.add(new Music("Sơn Tùng - Hãy chao cho anh","Sơn Tùng M-TP",R.drawable.hay_chao_cho_anh,R.raw.hay_chao_cho_anh));
        arrayMusic.add(new Music("Sơn Tùng - Nơi này có anh","Sơn Tùng M-TP",R.drawable.noi_nay_co_anh,R.raw.noi_nay_co_anh));
        arrayMusic.add(new Music("Arcade","Duncan Laurence",R.drawable.arcane,R.raw.arcane));
        arrayMusic.add(new Music("Dù Cho Mai Về Sau","Buitruonglinh",R.drawable.duchomaivesau,R.raw.duchomaivesau));
        arrayMusic.add(new Music("Hãy Cứ Vô Tư Và Lạc Quan Lên Em Ơi", "Hạ Vũ",R.drawable.haycuvotu,R.raw.haycuvotu));
        arrayMusic.add(new Music("Seasons","Rival, Cadmium, Harley Bird",R.drawable.seasons,R.raw.seasons));
        arrayMusic.add(new Music("Coldplay - Hymn For The Weekend","Bely Basarte",R.drawable.hymnfortheweekend,R.raw.hymnfortheweekend));
        arrayMusic.add(new Music("Waiting For Love","Avicii",R.drawable.waittingforlove,R.raw.waittingforlove));
        arrayMusic.add(new Music("All For Love","Tungevaag & Raaban",R.drawable.all_for_love,R.raw.all_for_love));
        arrayMusic.add(new Music("Legends Never Die","Against The Current và Mako",R.drawable.legend_never_die,R.raw.legend_never_die)) ;

        // Sắp xếp danh sách bản nhạc theo thứ tự bảng chữ cái (không phân biệt hoa thường)
        Collections.sort(arrayMusic);
        for(int i = 0; i < arrayMusic.size(); i++){
            arrayMusic.get(i).setId(String.format("S%05d",i));
        }
        return arrayMusic;
    }

    // Phương thức để lọc danh sách các bản nhạc theo tên của nghệ sĩ (tác giả)
    public static ArrayList<Music> musicianList(String musicianName, ArrayList<Music>musicArrayList){
        ArrayList<Music>arrayMusic = new ArrayList<>();

        // Duyệt qua danh sách các bản nhạc và thêm vào danh sách mới nếu tên nghệ sĩ trùng khớp
        for(Music music : musicArrayList){
            if(NlpUtils.removeAccent(music.getCaSi()).compareToIgnoreCase(NlpUtils.removeAccent(musicianName)) == 0){
                arrayMusic.add(music);
            }
        }
        return arrayMusic;
    }
}
