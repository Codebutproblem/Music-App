package com.example.musicapp.Class;

import com.example.musicapp.R;

import java.util.ArrayList;
import java.util.Collections;

public class MusicData {

    // Phương thức để lấy danh sách các bản nhạc
    public static ArrayList<Music> getArrayMusic(){
        ArrayList<Music> arrayMusic = new ArrayList<>();
        // Thêm các bản nhạc vào danh sách
        arrayMusic.add(new Music("Imagine Dragons - Enemy", "Imagine Dragons", R.drawable.enemy_arcane,"https://docs.google.com/uc?id=1KXAl9Kfh53KVISOWn8_tH02CwJmN13wJ"));
        arrayMusic.add(new Music("Imagine Dragons - Demons","Imagine Dragons",R.drawable.demons,"https://docs.google.com/uc?id=1gQ3SVAsFcR3j7i8YVX66BPjQa1ZL50E1"));
        arrayMusic.add(new Music("Imagine Dragons - Believer","Imagine Dragons",R.drawable.believer,"https://docs.google.com/uc?id=1GA0I1KrinyAFPGu_n12pzCO-sZ0ydMZX"));
        arrayMusic.add(new Music("Imagine Dragons - Thunder","Imagine Dragons",R.drawable.thunder,"https://docs.google.com/uc?id=1fvnB-aHCh_7kM6XHxiyMJDCSFXtU1hkA"));
        arrayMusic.add(new Music("Imagine Dragons - Bad Liar","Imagine Dragons",R.drawable.bad_liar,"https://docs.google.com/uc?id=193YUIdGGo1kieTkFCpj_zC41HgHX3lIN"));
        arrayMusic.add(new Music("Imagine Dragons - Radioactive","Imagine Dragons",R.drawable.radioactive,"https://docs.google.com/uc?id=10BFRRqDWuzsAKcMi3CtL0ntl8tqDA-ma"));
        arrayMusic.add(new Music("Imagine Dragons - Natural","Imagine Dragons",R.drawable.natural,"https://docs.google.com/uc?id=1upbZTCaqCct-xBNl-rGUC297Ce3p3PWW"));
        arrayMusic.add(new Music("Imagine Dragons - Bones","Imagine Dragons",R.drawable.bones,"https://docs.google.com/uc?id=18k6cLFHFXo4mEaFs-2UTqtch3xDp8M5C"));
        arrayMusic.add(new Music("Imagine Dragons - Whatever It Takes","Imagine Dragons",R.drawable.whatever_it_takes,"https://docs.google.com/uc?id=1dRy0qvKsHYxTagoQ7_0pDER-Bhitf5Gc"));
        arrayMusic.add(new Music("Imagine Dragons - Warriors","Imagine Dragons",R.drawable.warriors,"https://docs.google.com/uc?id=19Px66PQ8-ql5T4izlFNChMPm96Lhiex1"));
        arrayMusic.add(new Music("Sơn Tùng - Âm thầm bên em","Sơn Tùng M-TP",R.drawable.am_tham_ben_em,"https://docs.google.com/uc?id=1THiErRLQIRA4ktSfumsyT2IVltAAcUUx"));
        arrayMusic.add(new Music("Sơn Tùng - Có chắc yêu là đây","Sơn Tùng M-TP",R.drawable.co_chac_yeu_la_day,"https://docs.google.com/uc?id=1K96dbogHAv62OrjizjMTHW8rkY_f3RBC"));
        arrayMusic.add(new Music("Sơn Tùng - Hãy trao cho anh","Sơn Tùng M-TP",R.drawable.hay_trao_cho_anh,"https://docs.google.com/uc?id=1pmOo-9UIVaFDVURzSVL-SQ5eGqpRlDJZ"));
        arrayMusic.add(new Music("Sơn Tùng - Nơi này có anh","Sơn Tùng M-TP",R.drawable.noi_nay_co_anh,"https://docs.google.com/uc?id=1KftTKTj72a3B-C7ouG3AG0lStLFMX3QU"));
        arrayMusic.add(new Music("Arcade","Duncan Laurence",R.drawable.arcane,"https://docs.google.com/uc?id=1Wu8IL5iBjV31lWBQOjvjaPQGy9KaezBz"));
        arrayMusic.add(new Music("Dù Cho Mai Về Sau","Buitruonglinh",R.drawable.duchomaivesau,"https://docs.google.com/uc?id=1evpSR7AX65XABJA9hFu1EzU2N9xa55lr"));
        arrayMusic.add(new Music("Hãy Cứ Vô Tư Và Lạc Quan Lên Em Ơi", "Hạ Vũ",R.drawable.haycuvotu,"https://docs.google.com/uc?id=1-mmoZMvz3-KL7KjVcUs7AtMQRZd5YiAl"));
        arrayMusic.add(new Music("Seasons","Rival, Cadmium, Harley Bird",R.drawable.seasons,"https://docs.google.com/uc?id=1BmT3L5YlVhECpjSpXocZinVmjZ6eANUx"));
        arrayMusic.add(new Music("Coldplay - Hymn For The Weekend","Bely Basarte",R.drawable.hymnfortheweekend,"https://docs.google.com/uc?id=17qGLs-mlZNcI08uLx0Rc9wFIlNDipmcB"));
        arrayMusic.add(new Music("Waiting For Love","Avicii",R.drawable.waittingforlove,"https://docs.google.com/uc?id=1pZZXACXkvfO6Rv4LeBNKJETPZEGVvzYf"));
        arrayMusic.add(new Music("All For Love","Tungevaag & Raaban",R.drawable.all_for_love,"https://docs.google.com/uc?id=1BOxk1AaBqXCGF6oN9T-HmCTppLWZGhJI"));
        arrayMusic.add(new Music("Legends Never Die","Against The Current và Mako",R.drawable.legend_never_die,"https://docs.google.com/uc?id=1_4K8VjBmtw9nlX_9nH52JDldTggwGMgk"));

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
