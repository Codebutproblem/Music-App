package com.example.musicapp.Adapter;
//Adapter của ListView trong SearchFragment

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicapp.Fragment.SearchFragment;
import com.example.musicapp.Class.Music;
import com.example.musicapp.Class.NlpUtils;
import com.example.musicapp.R;

import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private ArrayList<Music>arrayMusic;// Danh sách ban đầu của các bài nhạc
    private ArrayList<Music>arrayMusicOld;// Danh sách ban đầu được sao lưu để phục hồi khi tìm kiếm trống

    public MusicAdapter(Context context, int layout, ArrayList<Music> arrayMusic) {
        this.context = context;
        this.layout = layout;
        this.arrayMusic = arrayMusic;
        this.arrayMusicOld = arrayMusic;// Lưu danh sách ban đầu
    }

    @Override
    public int getCount() {
        return arrayMusic.size();// Trả về số lượng bài nhạc trong danh sách
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Gắn layout cho mỗi item trong danh sách
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);


        // Ánh xạ các thành phần giao diện
        TextView txtMusicName = (TextView) view.findViewById(R.id.musicName);
        TextView txtMusicianName = (TextView) view.findViewById(R.id.musicianName);
        ImageView musicImage = (ImageView) view.findViewById(R.id.musicImage);

        // Lấy thông tin bài nhạc tại vị trí i
        Music music = arrayMusic.get(i);

        // Đặt thông tin bài nhạc lên giao diện
        txtMusicName.setText(music.getTenNhac());
        txtMusicianName.setText(music.getTacGia());
        musicImage.setImageResource(music.getHinhNen());

        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    // Nếu tìm kiếm trống, sử dụng danh sách ban đầu
                    arrayMusic = arrayMusicOld;
                }
                else{
                    // Tạo danh sách tạm thời để lưu kết quả tìm kiếm
                    ArrayList<Music> arraySearch = new ArrayList<>();
                    for(Music music : arrayMusic){
                        // Kiểm tra xem tên bài hát có chứa chuỗi tìm kiếm không
                        if(NlpUtils.removeAccent(music.getTenNhac()).toLowerCase().contains(NlpUtils.removeAccent(strSearch).toLowerCase())
                          ||NlpUtils.removeAccent(music.getTacGia()).toLowerCase().contains(NlpUtils.removeAccent(strSearch).toLowerCase())){
                            arraySearch.add(music);
                        }
                    }
                    // Gán danh sách tìm kiếm vào danh sách hiển thị
                    arrayMusic = arraySearch;
                }

                // Cập nhật danh sách bài hát trong SearchFragment
                SearchFragment.setArrayMusic(arrayMusic);

                // Tạo kết quả của việc lọc
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayMusic;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrayMusic = (ArrayList<Music>)filterResults.values;
                notifyDataSetChanged();// Thông báo sự thay đổi trong danh sách hiển thị
            }
        };
    }
}
