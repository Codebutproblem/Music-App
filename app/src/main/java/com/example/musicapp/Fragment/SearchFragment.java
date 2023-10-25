package com.example.musicapp.Fragment;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Class.Music;
import com.example.musicapp.Adapter.MusicAdapter;
import com.example.musicapp.Activity.PlayMusicActivity;
import com.example.musicapp.DataBase.MusicDataBase;
import com.example.musicapp.R;

import java.util.Collections;
import java.util.List;

// Fragment chứa chức năng tìm kiếm âm nhạc
public class SearchFragment extends Fragment{
    private static List<Music> arrayMusic;// Danh sách âm nhạc
    private Intent it;// Intent để mở trang chơi âm nhạc
    private static MusicAdapter adapter;// Adapter để quản lý danh sách âm nhạc
    private Toolbar toolbar;// Toolbar cho giao diện tìm kiếm
    private static SearchView searchView;// Thành phần tìm kiếm
    private RecyclerView rcvMusic;

    private static Context context;

    // Phương thức getter để lấy đối tượng SearchView
    public static SearchView getSearchView() {
        return searchView;
    }

    // Phương thức getter để lấy danh sách âm nhạc
    public static List<Music> getArrayMusic() {
        return arrayMusic;
    }

    // Phương thức setter để cập nhật danh sách âm nhạc
    public static void setArrayMusic(List<Music>newArrayMusic){
        arrayMusic = newArrayMusic;
    }

    public static void setAdapter(List<Music>musicList) {
        if(adapter != null){
            Collections.sort(musicList);
            adapter.setData(context, musicList);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Gắn layout fragment_search.xml vào fragment
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        context = getActivity();
        AnhXa(view);// Ánh xạ các thành phần trong layout
        arrayMusic =  MusicDataBase.getInstance(getActivity()).musicDao().getMusicArray();// Lấy danh sách âm nhạc từ lớp MusicData
        Collections.sort(arrayMusic);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar);// Gắn toolbar vào activity
        activity.getSupportActionBar().setTitle("Search");// Đặt tiêu đề trang tìm kiếm trên toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));// Đặt màu tiêu đề trắng


        adapter = new MusicAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
        rcvMusic.setLayoutManager(linearLayoutManager);
        adapter.setData(getActivity(),arrayMusic);
        rcvMusic.setAdapter(adapter);


        return view;// Trả về giao diện fragment đã được tạo
    }

    // Phương thức để mở trang chơi âm nhạc
    private void openPlayPage(int i){
        PlayMusicActivity.setArrayMusic(arrayMusic);
        it = new Intent(getActivity(),PlayMusicActivity.class);
        it.putExtra("position",i + "");// Truyền vị trí âm nhạc được chọn cho trang chơi âm nhạc
        startActivity(it);
    }

    // Phương thức để ánh xạ các thành phần trong layout
    private void AnhXa(View view) {
        toolbar = view.findViewById(R.id.search_toolbar);// Ánh xạ toolbar
        rcvMusic = view.findViewById(R.id.musicList);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);// Bật chế độ hiển thị Menu trên toolbar
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);// Gắn menu từ menu_main.xml vào toolbar
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();// Lấy đối tượng SearchView từ menu
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));// Đặt thông tin tìm kiếm
        searchView.setMaxWidth(Integer.MAX_VALUE);// Đặt kích thước tối đa cho ô tìm kiếm

        // Xử lý sự kiện khi người dùng tìm kiếm hoặc thay đổi nội dung tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);// Lọc danh sách âm nhạc dựa trên nội dung tìm kiếm
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);// Lọc danh sách âm nhạc khi nội dung tìm kiếm thay đổi
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
