package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        AnhXa();
        getMusicData();
        setSupportActionBar(toolbar);
        adapter = new MusicAdapter(SearchActivity.this, R.layout.music_line, arrayMusic);
        lvMusic.setAdapter(adapter);



        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openPlayPage(i);
            }
        });
    }

    private void openPlayPage(int i){
        it = new Intent(this,PlayMusicActivity.class);
        it.putExtra("position",i + "");
        startActivity(it);
    }
    private void AnhXa() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        lvMusic = (ListView) findViewById(R.id.musicList);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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
        return true;
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}