package com.example.musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private int layout;
    private ArrayList<Music>arrayMusic;
    private ArrayList<Music>arrayMusicOld;

    public MusicAdapter(Context context, int layout, ArrayList<Music> arrayMusic) {
        this.context = context;
        this.layout = layout;
        this.arrayMusic = arrayMusic;
        this.arrayMusicOld = arrayMusic;
    }

    @Override
    public int getCount() {
        return arrayMusic.size();
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

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        TextView txtMusicName = (TextView) view.findViewById(R.id.musicName);
        TextView txtMusicianName = (TextView) view.findViewById(R.id.musicianName);
        ImageView musicImage = (ImageView) view.findViewById(R.id.musicImage);

        Music music = arrayMusic.get(i);

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
                    arrayMusic = arrayMusicOld;
                }
                else{
                    ArrayList<Music> arraySearch = new ArrayList<>();
                    for(Music music : arrayMusic){
                        if(NlpUtils.removeAccent(music.getTenNhac()).toLowerCase().contains(NlpUtils.removeAccent(strSearch).toLowerCase())){
                            arraySearch.add(music);
                        }
                    }
                    arrayMusic = arraySearch;
                }
                SearchFragment.setArrayMusic(arrayMusic);
                SearchFragment.setArrayMusic(arrayMusic);
                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayMusic;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrayMusic = (ArrayList<Music>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
