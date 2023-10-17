package com.example.musicapp.Adapter;
//Adapter của BottomNavigationView (Bottom Bar)

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.musicapp.Fragment.LibraryFragment;
import com.example.musicapp.Fragment.HomeFragment;
import com.example.musicapp.Fragment.SearchFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    // Constructor của Adapter, nhận FragmentManager và behavior (chế độ hoạt động) làm đối số
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    // Phương thức để lấy Fragment dựa trên vị trí (position) trong ViewPager
    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Dựa vào vị trí (position), trả về fragment tương ứng
        if(position == 0) return new HomeFragment();// Màn hình chính (HomeFragment)
        if(position == 1) return new SearchFragment();// Màn hình tìm kiếm (SearchFragment)
        if(position == 2) return new LibraryFragment();// Màn hình thư viện (LibraryFragment)
        return new HomeFragment();// Trường hợp mặc định, trả về HomeFragment()
    }

    // Phương thức để lấy số lượng fragment trong ViewPager
    @Override
    public int getCount() {
        return 3;// Có tổng cộng 3 fragment trong ViewPager
    }
}
