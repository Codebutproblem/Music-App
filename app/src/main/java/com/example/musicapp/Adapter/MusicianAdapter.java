package com.example.musicapp.Adapter;
//Adapter của RecycleView trong HomeFragment
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

import com.example.musicapp.Class.Musician;
import com.example.musicapp.Activity.MusicianPlaylistActivity;
import com.example.musicapp.R;

import java.util.ArrayList;

public class MusicianAdapter extends RecyclerView.Adapter<MusicianAdapter.MusicianViewHolder>{

    private ArrayList<Musician>musicianList;// Danh sách các nhạc sĩ
    private Context context;

    // Phương thức để cập nhật dữ liệu cho Adapter
    public void setData(Context context,ArrayList<Musician>list){
        this.context = context;
        this.musicianList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MusicianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo một ViewHolder mới bằng cách gắn layout item_musician.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_musician,parent,false);
        return new MusicianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicianViewHolder holder, int position) {
        final Musician musician = musicianList.get(position);
        if(musician == null){
            return;
        }

        // Thiết lập dữ liệu của ViewHolder từ đối tượng Musician tương ứng
        holder.imgMusician.setImageResource(musician.getImageId());
        holder.musicianName.setText(musician.getName());

        // Xử lý sự kiện khi người dùng nhấn vào một mục trong danh sách
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(musician);
            }
        });
    }

    // Phương thức để chuyển đến màn hình chi tiết của một nhạc sĩ khi người dùng nhấn vào mục
    private void onClickGoToDetail(Musician musician){
        Intent it = new Intent(context, MusicianPlaylistActivity.class);
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

    // ViewHolder để lưu trữ các thành phần giao diện trong một mục danh sách
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
