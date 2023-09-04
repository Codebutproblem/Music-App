package com.example.musicapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchFragment extends Fragment{
    private ListView lvMusic;
    private static ArrayList<Music> arrayMusic;
    private Intent it;
    private MusicAdapter adapter;
    private Toolbar toolbar;
    private SearchView searchView;

    public static ArrayList<Music> getArrayMusic() {
        return arrayMusic;
    }
    public static void setArrayMusic(ArrayList<Music>newArrayMusic){
        arrayMusic = newArrayMusic;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search,container,false);
        AnhXa(view);
        getMusicData();
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Search");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        adapter = new MusicAdapter(getContext(), R.layout.music_line, arrayMusic);
        lvMusic.setAdapter(adapter);
        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openPlayPage(i);
            }
        });
        return view;
    }

    private void openPlayPage(int i){
        it = new Intent(getActivity(),PlayMusicActivity.class);
        it.putExtra("position",i + "");
        startActivity(it);
    }
    private void AnhXa(View view) {
        toolbar = view.findViewById(R.id.search_toolbar);
        lvMusic = view.findViewById(R.id.musicList);
    }
    private void getMusicData() {
        arrayMusic = new ArrayList<>();
        arrayMusic.add(new Music("Arcade","Duncan Laurence",R.drawable.arcane,R.raw.arcane));
        arrayMusic.add(new Music("Dù Cho Mai Về Sau","Buitruonglinh",R.drawable.duchomaivesau,R.raw.duchomaivesau));
        arrayMusic.add(new Music("Hãy Cứ Vô Tư Và Lạc Quan Lên Em Ơi", "Hạ Vũ",R.drawable.haycuvotu,R.raw.haycuvotu));
        arrayMusic.add(new Music("Seasons - Rival, Cadmium, Harley Bird","Rival, Cadmium, Harley Bird",R.drawable.seasons,R.raw.seasons));
        arrayMusic.add(new Music("Coldplay - Hymn For The Weekend","Bely Basarte",R.drawable.hymnfortheweekend,R.raw.hymnfortheweekend));
        arrayMusic.add(new Music("Waiting For Love","Avicii",R.drawable.waittingforlove,R.raw.waittingforlove));
        arrayMusic.add(new Music("Enemy - Arcane", "Imagine Dragons", R.drawable.enemy_arcane,R.raw.enemy_arcane));
        arrayMusic.add(new Music("All For Love","Tungevaag & Raaban",R.drawable.all_for_love,R.raw.all_for_love));
        arrayMusic.add(new Music("Demons (Imagine Dragons Cover)","Tayler Buono",R.drawable.demons,R.raw.demons));
        arrayMusic.add(new Music("Legends Never Die","Against The Current và Mako",R.drawable.legend_never_die,R.raw.legend_never_die)) ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
