package com.example.musicapp.Adapter;

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

import com.example.musicapp.Activity.LoginActivity;
import com.example.musicapp.Activity.MusicianPlaylistActivity;
import com.example.musicapp.Activity.PlayMusicActivity;
import com.example.musicapp.Class.Book;
import com.example.musicapp.Class.LibraryData;
import com.example.musicapp.Class.Music;
import com.example.musicapp.Class.MusicData;
import com.example.musicapp.Class.Musician;
import com.example.musicapp.Class.MusicianData;
import com.example.musicapp.Class.NlpUtils;
import com.example.musicapp.R;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{
    private ArrayList<Book>mBooks;
    private Context mContext;

    public void setData(Context mContext, ArrayList<Book>list){
        this.mContext = mContext;
        this.mBooks = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        final Book book = mBooks.get(position);
        if (book == null){
            return;
        }
        holder.imgBook.setImageResource(book.getResourceId());
        holder.tvTitle.setText(book.getTitle());

        holder.bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGotoDetail(book);
            }
        });
    }

    private void onClickGotoDetail(Book book) {

        if(book.getCategory().equals("favMusic")){
            Intent it = new Intent(mContext, PlayMusicActivity.class);
            LibraryData libraryData = new LibraryData();
            ArrayList<Book>books = libraryData.getFavlist();
            ArrayList<Music> arrayMusic = MusicData.getMusicList(books);
            PlayMusicActivity.setArrayMusic(arrayMusic);
            it.putExtra("position",MusicData.getPosition(book.getId(),arrayMusic) + "");
            it.putExtra("from","Library");
            mContext.startActivity(it);
        }
        if(book.getCategory().equals("hisMusic")){
            Intent it = new Intent(mContext, PlayMusicActivity.class);
            LibraryData libraryData = new LibraryData();
            ArrayList<Book>books = libraryData.getHisList();
            ArrayList<Music> arrayMusic = MusicData.getMusicList(books);
            PlayMusicActivity.setArrayMusic(arrayMusic);
            it.putExtra("position",MusicData.getPosition(book.getId(),arrayMusic) + "");
            it.putExtra("from","Library");
            mContext.startActivity(it);
        }
        if (book.getCategory().equals("musician")){
            Intent it = new Intent(mContext, MusicianPlaylistActivity.class);
            it.putExtra("musician",book.getId());
            mContext.startActivity(it);
        }
    }
    @Override
    public int getItemCount() {
        if(mBooks != null){
            return mBooks.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{
        private CardView bookCard;
        private ImageView imgBook;
        private TextView tvTitle;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBook = itemView.findViewById(R.id.img_book);
            tvTitle = itemView.findViewById(R.id.tv_title);
            bookCard = itemView.findViewById(R.id.book_layout);
        }
    }
}
