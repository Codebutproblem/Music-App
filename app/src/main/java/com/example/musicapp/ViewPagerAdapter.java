package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0) return new HomeFragment();
        if(position == 1) return new SearchFragment();
        if(position == 2) return new FavouriteFragment();
        return new SearchFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
