package com.lucky.customviewlearn.materialdesign;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zfz on 2017/7/13.
 */

public class TabViewPagerAdapter<V extends View> extends PagerAdapter {

    private List<V> viewList;
    private List<String> pageTitles;

    public TabViewPagerAdapter(List<V> viewList, List<String> pageTitles) {
        this.viewList = viewList;
        this.pageTitles = pageTitles;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles.get(position);
    }
}
