package com.example.musicapp.Activity;
//Giao diện chính sử dụng file activity_main.xml
//chứa ViewPager và BottomNavigationView
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.musicapp.Fragment.SearchFragment;
import com.example.musicapp.R;
import com.example.musicapp.Adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khai báo và khởi tạo các thành phần giao diện
        navigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.view_pager);

        // Cài đặt ViewPager và liên kết với BottomNavigationView(Bottom Bar)
        setupViewPager();

        // Lắng nghe sự kiện khi người dùng chọn mục menu trên BottomNavigationView(Bottom Bar)
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.action_home){
                    viewPager.setCurrentItem(0);
                }
                if(item.getItemId() == R.id.action_search){
                    viewPager.setCurrentItem(1);
                }
                if(item.getItemId() == R.id.action_favourite){
                    viewPager.setCurrentItem(2);
                }
                return true;
            }
        });
    }

    // Phương thức này được sử dụng để cài đặt ViewPager và các sự kiện liên quan
    private void setupViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        // Lắng nghe sự kiện khi người dùng vuốt ViewPager để thay đổi trang hiện tại
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Xử lý khi người dùng vuốt ViewPager
            }

            @Override
            public void onPageSelected(int position) {
                // Cập nhật mục menu tương ứng trên BottomNavigationView
                if (position == 0){
                    navigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                }
                if (position == 1){
                    navigationView.getMenu().findItem(R.id.action_search).setChecked(true);
                }
                if(position == 2){
                    navigationView.getMenu().findItem(R.id.action_favourite).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Xử lý khi trạng thái của ViewPager thay đổi
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Kiểm tra trạng thái của ô tìm kiếm trong SearchFragment và xử lý nút Back
        SearchView searchView = SearchFragment.getSearchView();
        if(!searchView.isIconified()){
            searchView.setIconified(true);// Tắt ô tìm kiếm
            return;
        }
        super.onBackPressed();// Xử lý nút Back theo cách mặc định
    }
}