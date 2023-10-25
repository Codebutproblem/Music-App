package com.example.musicapp.Data;

import com.example.musicapp.Activity.FlashActivity;
import com.example.musicapp.Class.Book;
import com.example.musicapp.Class.Music;
import com.example.musicapp.Class.NlpUtils;
import com.example.musicapp.DataBase.MusicDataBase;
import com.example.musicapp.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MusicData {
    private static List<Music> arrayMusic;
    private static HashMap<Integer,Music> musicMap;

    // Phương thức để lấy danh sách các bản nhạc
    public static void setArrayMusic(){
        arrayMusic = new ArrayList<>();
        musicMap = new HashMap<>();




        //Thêm bài hát ở đây bằng cách add thêm object của lớp Music, chạy xong rồi xóa đi cũng được vì nó lưu vào database rồi
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
        arrayMusic.add(new Music("Sơn Tùng - Hãy trao cho anh","Sơn Tùng M-TP",R.drawable.hay_trao_cho_anh,R.raw.hay_trao_cho_anh));
        arrayMusic.add(new Music("Sơn Tùng - Nơi này có anh","Sơn Tùng M-TP",R.drawable.noi_nay_co_anh,R.raw.noi_nay_co_anh));
        arrayMusic.add(new Music("Arcade","Duncan Laurence",R.drawable.arcade,R.raw.arcade));
        arrayMusic.add(new Music("Dù Cho Mai Về Sau","Buitruonglinh",R.drawable.duchomaivesau,R.raw.duchomaivesau));
        arrayMusic.add(new Music("Hãy Cứ Vô Tư Và Lạc Quan Lên Em Ơi", "Hạ Vũ",R.drawable.haycuvotu,R.raw.haycuvotu));
        arrayMusic.add(new Music("Seasons","Rival, Cadmium, Harley Bird",R.drawable.seasons,R.raw.seasons));
        arrayMusic.add(new Music("Coldplay - Hymn For The Weekend","Bely Basarte",R.drawable.hymnfortheweekend,R.raw.hymn_for_the_weekend));
        arrayMusic.add(new Music("Waiting For Love","Avicii",R.drawable.waittingforlove,R.raw.waittingforlove));
        arrayMusic.add(new Music("All For Love","Tungevaag & Raaban",R.drawable.all_for_love,R.raw.all_for_love));
        arrayMusic.add(new Music("Legends Never Die","Against The Current và Mako",R.drawable.legend_never_die,R.raw.legend_never_die));
        //.......
        //.......



        for (Music music: arrayMusic){
            if (!checkContains(music)){
                music.setLove(false);
                MusicDataBase.getInstance(FlashActivity.getContext()).musicDao().insertMusic(music);
            }
        }
        arrayMusic = MusicDataBase.getInstance(FlashActivity.getContext()).musicDao().getMusicArray();
        for(Music music : arrayMusic){
            musicMap.put(music.getId(),music);
        }
    }

    private static boolean checkContains(Music music){
        List<Music> list = MusicDataBase.getInstance(FlashActivity.getContext()).musicDao().checkExist(music.getTenNhac());
        return list != null && !list.isEmpty();
    }
    // Phương thức để lọc danh sách các bản nhạc theo tên của nghệ sĩ (tác giả)
    public static List<Music> musicianList(String musicianName, List<Music>musicArrayList){
        List<Music>arrayMusic = new ArrayList<>();

        // Duyệt qua danh sách các bản nhạc và thêm vào danh sách mới nếu tên nghệ sĩ trùng khớp
        for(Music music : musicArrayList){
            if(NlpUtils.removeAccent(music.getCaSi()).compareToIgnoreCase(NlpUtils.removeAccent(musicianName)) == 0){
                arrayMusic.add(music);
            }
        }
        return arrayMusic;
    }
    public static List<Music> getMusicList(List<Book>books){
        List<Music>resultList = new ArrayList<>();
        for(Book book: books){
            resultList.add(musicMap.get(book.getId()));
        }
        return resultList;
    }
    public static int getPosition(int id,List<Music>arrayList){
        int res = 0;
        try {
            for(int i = 0; i < arrayList.size(); i++){
                if (arrayList.get(i).getId() == id){
                    res = i;
                    break;
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }
}
