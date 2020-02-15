package com.lucky.customviewlearn.view.nestedviewpager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.view.nestedviewpager.CommonFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoyTabFragment extends Fragment {

    private String[] mTabTitles = new String[]{"娱乐八卦", "时尚芭莎", "电影上映"};
    private CommonFragmentPagerAdapter pagerAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_entertainment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initJoyWidgets(view);
    }

    private void initJoyWidgets(View view){
        mSlidingTabLayout = view.findViewById(R.id.sub_tablayout);
        mViewPager = view.findViewById(R.id.sub_vp_container);
        pagerAdapter = new CommonFragmentPagerAdapter(getChildFragmentManager());
        pagerAdapter.setFragments(getFragments());
        pagerAdapter.setPageTitles(Arrays.asList(mTabTitles));
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(mTabTitles.length);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    private List<Fragment> getFragments(){
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new GossipFragment());
        fragments.add(new FashionFragment());
        fragments.add(new MovieFragment());
        return fragments;
    }



}
