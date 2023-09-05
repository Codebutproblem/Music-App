package com.example.musicapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicianAdapter extends RecyclerView.Adapter<MusicianAdapter.MusicianViewHolder>{

    private ArrayList<Musician>musicianList;

    public void setData(ArrayList<Musician>list){
        this.musicianList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MusicianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_musician,parent,false);
        return new MusicianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicianViewHolder holder, int position) {
        Musician musician = musicianList.get(position);
        if(musician == null){
            return;
        }
        holder.imgMusician.setImageResource(musician.getImageId());
        holder.musicianName.setText(musician.getName());
    }

    @Override
    public int getItemCount() {
        if(musicianList != null){
            return musicianList.size();
        }
        return 0;
    }

    public class MusicianViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgMusician;
        private TextView musicianName;
        public MusicianViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMusician = itemView.findViewById(R.id.img_musician);
            musicianName = itemView.findViewById(R.id.musician_name);
        }
    }
}
