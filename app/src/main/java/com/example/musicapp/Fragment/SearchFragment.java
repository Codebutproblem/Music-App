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

import com.example.musicapp.Class.Music;
import com.example.musicapp.Adapter.MusicAdapter;
import com.example.musicapp.Data.MusicData;
import com.example.musicapp.Activity.PlayMusicActivity;
import com.example.musicapp.R;

import java.util.ArrayList;

// Fragment chứa chức năng tìm kiếm âm nhạc
public class SearchFragment extends Fragment{
    private ListView lvMusic;// ListView để hiển thị danh sách âm nhạc
    private static ArrayList<Music> arrayMusic;// Danh sách âm nhạc
    private Intent it;// Intent để mở trang chơi âm nhạc
    private MusicAdapter adapter;// Adapter để quản lý danh sách âm nhạc
    private Toolbar toolbar;// Toolbar cho giao diện tìm kiếm
    private static SearchView searchView;// Thành phần tìm kiếm

    // Phương thức getter để lấy đối tượng SearchView
    public static SearchView getSearchView() {
        return searchView;
    }

    // Phương thức getter để lấy danh sách âm nhạc
    public static ArrayList<Music> getArrayMusic() {
        return arrayMusic;
    }

    // Phương thức setter để cập nhật danh sách âm nhạc
    public static void setArrayMusic(ArrayList<Music>newArrayMusic){
        arrayMusic = newArrayMusic;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Gắn layout fragment_search.xml vào fragment
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        AnhXa(view);// Ánh xạ các thành phần trong layout
        arrayMusic = MusicData.getArrayMusic();// Lấy danh sách âm nhạc từ lớp MusicData
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(toolbar);// Gắn toolbar vào activity
        activity.getSupportActionBar().setTitle("Search");// Đặt tiêu đề trang tìm kiếm trên toolbar
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));// Đặt màu tiêu đề trắng
        adapter = new MusicAdapter(getContext(), R.layout.music_line, arrayMusic);// Khởi tạo adapter
        lvMusic.setAdapter(adapter);// Gắn adapter vào ListView
        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                openPlayPage(i);// Mở trang chơi âm nhạc khi người dùng nhấn vào một mục trong danh sách
            }
        });
        return view;// Trả về giao diện fragment đã được tạo
    }

    // Phương thức để mở trang chơi âm nhạc
    private void openPlayPage(int i){
        PlayMusicActivity.setArrayMusic(arrayMusic);
        it = new Intent(getActivity(),PlayMusicActivity.class);
        it.putExtra("position",i + "");// Truyền vị trí âm nhạc được chọn cho trang chơi âm nhạc
        it.putExtra("from","Search");
        startActivity(it);
    }

    // Phương thức để ánh xạ các thành phần trong layout
    private void AnhXa(View view) {
        toolbar = view.findViewById(R.id.search_toolbar);// Ánh xạ toolbar
        lvMusic = view.findViewById(R.id.musicList);// Ánh xạ ListView
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
