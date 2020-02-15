package com.lucky.customviewlearn.view.nestedviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.SlidingTabLayout;
import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.view.nestedviewpager.fragment.JoyTabFragment;
import com.lucky.customviewlearn.view.nestedviewpager.fragment.NewsTabFragment;
import com.lucky.customviewlearn.view.nestedviewpager.fragment.SportsTabFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//
public class NestedViewPagerActivity extends AppCompatActivity {
    private String[] mTabTitles = new String[]{"新闻", "娱乐", "体育"};
    private CommonFragmentPagerAdapter pagerAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nestedviewpager);
        mSlidingTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.main_vp_container);
        pagerAdapter = new CommonFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.setFragments(getFragments());
        pagerAdapter.setPageTitles(Arrays.asList(mTabTitles));
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(mTabTitles.length);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    private List<Fragment> getFragments(){
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewsTabFragment());
        fragments.add(new JoyTabFragment());
        fragments.add(new SportsTabFragment());
        return fragments;
    }





}
