package com.lucky.customviewlearn.material;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lucky.customviewlearn.BaseActivity;
import com.lucky.customviewlearn.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zfz on 2017/7/12.
 */

public class NavigationViewActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titles = new String[]{"实事", "娱乐", "体育", "视频"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("实事"), 0);
        tabLayout.addTab(tabLayout.newTab().setText("娱乐"), 1);
        tabLayout.addTab(tabLayout.newTab().setText("体育"), 2);
        tabLayout.addTab(tabLayout.newTab().setText("视频"), 3);
        viewPager.setAdapter(new TabViewPagerAdapter<LinearLayout>(createTabContents(), Arrays.asList(titles)));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START, true);
            } else {
                drawerLayout.openDrawer(GravityCompat.START, true);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.primary_page:
                showToast("我的首页");
                break;
            case R.id.blog:
                showToast("我的博客");
                break;
            case R.id.about:
                showToast("关于我");
                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawer(Gravity.START, true);
        return true;
    }

    private List<LinearLayout> createTabContents() {
        List<LinearLayout> tabContents = new ArrayList<>();
        TabContentLayout shishi  = new TabContentLayout.Builder(this).setTabContentText("实事").build();
        TabContentLayout yule    = new TabContentLayout.Builder(this).setTabContentText("娱乐").build();
        TabContentLayout tiyu    = new TabContentLayout.Builder(this).setTabContentText("体育").build();
        TabContentLayout shiping = new TabContentLayout.Builder(this).setTabContentText("视频").build();
        tabContents.add(shishi);
        tabContents.add(yule);
        tabContents.add(tiyu);
        tabContents.add(shiping);
        return tabContents;
    }
}


