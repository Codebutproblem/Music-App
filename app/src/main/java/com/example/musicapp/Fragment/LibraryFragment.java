package com.example.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.Activity.LoginActivity;
import com.example.musicapp.Adapter.CategoryAdapter;
import com.example.musicapp.Class.Book;
import com.example.musicapp.Class.Category;
import com.example.musicapp.Data.LibraryData;
import com.example.musicapp.R;

import java.sql.Connection;
import java.util.ArrayList;

//Chưa làm =))
public class LibraryFragment extends Fragment {
    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private TextView loginBtn,category;
    private Connection connection;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library,container,false);
        AnhXa(view);

        categoryAdapter = new CategoryAdapter(this.getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity(),RecyclerView.VERTICAL,false);
        rcvCategory.setLayoutManager(linearLayoutManager);
        categoryAdapter.setData(getListCategory());
        rcvCategory.setAdapter(categoryAdapter);

        if(LoginActivity.checkLogin()){
            loginBtn.setVisibility(View.INVISIBLE);
        }
        else{
            loginBtn.setVisibility(View.VISIBLE);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openLoginPage();
                }
            });
        }

        return view;
    }
    private void openLoginPage() {
        Intent it = new Intent(this.getActivity(), LoginActivity.class);
        startActivity(it);
    }

    private ArrayList<Category> getListCategory() {
        ArrayList<Book>musicianList = new ArrayList<>();
        ArrayList<Book>musicList = new ArrayList<>();
        ArrayList<Book>historyList = new ArrayList<>();

        if(LoginActivity.checkLogin()){
            LibraryData libraryData = new LibraryData();

            musicianList =libraryData.getMusicianData();
            musicList = libraryData.getFavlist();
            historyList = libraryData.getHisList();
        }


        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Ca Sĩ",musicianList));
        categories.add(new Category("Yêu thích",musicList));
        categories.add(new Category("Lịch sử phát",historyList));
        return categories;
    }
    private void AnhXa(View view) {
        rcvCategory = view.findViewById(R.id.rcv_category);
        loginBtn = view.findViewById(R.id.lib_login_btn);
    }
}
