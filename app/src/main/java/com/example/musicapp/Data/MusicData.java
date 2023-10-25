package com.example.musicapp.Data;

import com.example.musicapp.Activity.FlashActivity;
import com.example.musicapp.Activity.MainActivity;
import com.example.musicapp.Class.Book;
import com.example.musicapp.Class.Music;
import com.example.musicapp.Class.NlpUtils;
import com.example.musicapp.DataBase.MusicDataBase;
import com.example.musicapp.R;
import java.util.ArrayList;
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
        arrayMusic.add(new Music("Imagine Dragons - Enemy", "Imagine Dragons", R.drawable.ms10_enemy_arcane,R.raw.ms10_enemy_arcane));
        arrayMusic.add(new Music("Imagine Dragons - Demons","Imagine Dragons",R.drawable.ms08_demons,R.raw.ms08_demons));
        arrayMusic.add(new Music("Imagine Dragons - Believer","Imagine Dragons",R.drawable.ms05_believer,R.raw.ms05_believer));
        arrayMusic.add(new Music("Imagine Dragons - Thunder","Imagine Dragons",R.drawable.ms19_thunder,R.raw.ms19_thunder));
        arrayMusic.add(new Music("Imagine Dragons - Bad Liar","Imagine Dragons",R.drawable.ms04_bad_liar,R.raw.ms04_bad_liar));
        arrayMusic.add(new Music("Imagine Dragons - Radioactive","Imagine Dragons",R.drawable.ms17_radioactive,R.raw.ms17_radioactive));
        arrayMusic.add(new Music("Imagine Dragons - Natural","Imagine Dragons",R.drawable.ms15_natural,R.raw.ms15_natural));
        arrayMusic.add(new Music("Imagine Dragons - Bones","Imagine Dragons",R.drawable.ms06_bones,R.raw.ms06_bones));
        arrayMusic.add(new Music("Imagine Dragons - Whatever It Takes","Imagine Dragons",R.drawable.ms22_whatever_it_takes,R.raw.ms22_whatever_it_takes));
        arrayMusic.add(new Music("Imagine Dragons - Warriors","Imagine Dragons",R.drawable.ms21_warriors,R.raw.ms21_warriors));
        arrayMusic.add(new Music("Sơn Tùng - Âm thầm bên em","Sơn Tùng M-TP",R.drawable.ms02_am_tham_ben_em,R.raw.ms02_am_tham_ben_em));
        arrayMusic.add(new Music("Sơn Tùng - Có chắc yêu là đây","Sơn Tùng M-TP",R.drawable.ms07_co_chac_yeu_la_day,R.raw.ms07_co_chac_yeu_la_day));
        arrayMusic.add(new Music("Sơn Tùng - Hãy trao cho anh","Sơn Tùng M-TP",R.drawable.ms11_hay_trao_cho_anh,R.raw.ms11_hay_trao_cho_anh));
        arrayMusic.add(new Music("Sơn Tùng - Nơi này có anh","Sơn Tùng M-TP",R.drawable.ms16_noi_nay_co_anh,R.raw.ms16_noi_nay_co_anh));
        arrayMusic.add(new Music("Arcade","Duncan Laurence",R.drawable.ms03_arcade,R.raw.ms03_arcade));
        arrayMusic.add(new Music("Dù Cho Mai Về Sau","Buitruonglinh",R.drawable.ms09_duchomaivesau,R.raw.ms09_duchomaivesau));
        arrayMusic.add(new Music("Hãy Cứ Vô Tư Và Lạc Quan Lên Em Ơi", "Hạ Vũ",R.drawable.ms12_haycuvotu,R.raw.ms12_haycuvotu));
        arrayMusic.add(new Music("Seasons","Rival, Cadmium, Harley Bird",R.drawable.ms18_seasons,R.raw.ms18_seasons));
        arrayMusic.add(new Music("Coldplay - Hymn For The Weekend","Bely Basarte",R.drawable.ms13_hymnfortheweekend,R.raw.ms13_hymn_for_the_weekend));
        arrayMusic.add(new Music("Waiting For Love","Avicii",R.drawable.ms20_waittingforlove,R.raw.ms20_waittingforlove));
        arrayMusic.add(new Music("All For Love","Tungevaag & Raaban",R.drawable.ms01_all_for_love,R.raw.ms01_all_for_love));
        arrayMusic.add(new Music("Legends Never Die","Against The Current và Mako",R.drawable.ms14_legend_never_die,R.raw.ms14_legend_never_die));
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
        List<Music>list = MusicDataBase.getInstance(MainActivity.getContext()).musicDao().getMusicArray();
        for (Music music : list){
            for(Book book: books){
                if(book.getId() == music.getId()){
                    resultList.add(music);
                }
            }
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
