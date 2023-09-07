package com.example.musicapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicianAdapter extends RecyclerView.Adapter<MusicianAdapter.MusicianViewHolder>{

    private ArrayList<Musician>musicianList;
    private Context context;

    public void setData(Context context,ArrayList<Musician>list){
        this.context = context;
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
        final Musician musician = musicianList.get(position);
        if(musician == null){
            return;
        }
        holder.imgMusician.setImageResource(musician.getImageId());
        holder.musicianName.setText(musician.getName());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(musician);
            }
        });
    }
    private void onClickGoToDetail(Musician musician){
        Intent it = new Intent(context,MusicianPlaylistActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_musician",musician);
        it.putExtras(bundle);
        context.startActivity(it);
    }
    @Override
    public int getItemCount() {
        if(musicianList != null){
            return musicianList.size();
        }
        return 0;
    }

    public class MusicianViewHolder extends RecyclerView.ViewHolder{
        CardView layoutItem;
        private ImageView imgMusician;
        private TextView musicianName;
        public MusicianViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_item);
            imgMusician = itemView.findViewById(R.id.img_musician);
            musicianName = itemView.findViewById(R.id.musician_name);
        }
    }
}
