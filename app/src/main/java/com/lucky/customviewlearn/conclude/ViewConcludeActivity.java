package com.lucky.customviewlearn.conclude;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.scroller.widget.ListViewActivity;
import com.lucky.customviewlearn.scroller.widget.RecyclerViewActivity;
import com.lucky.customviewlearn.scroller.widget.ScrollViewActivity;
import com.lucky.customviewlearn.scroller.widget.SimpleRefreshLayoutActivity;
import com.lucky.customviewlearn.scroller.widget.SimpleRefreshLayoutTwoActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

// 自定义控件总结篇
// 1. 自定义View
// 2. 自定义ViewGroup
public class ViewConcludeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview_conclude);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_customcircle)
    public void onCustomCircleClick() {
        startActivity(new Intent(this, CustomCircleActivity.class));
    }

    @OnClick(R.id.btn_custom_viewgroup)
    public void onCustomLinearLayoutClick() {
        startActivity(new Intent(this, CustomViewGroupActivity.class));
    }

    @OnClick(R.id.btn_simple_refresh)
    public void onSimpleRefreshClick() {
        startActivity(new Intent(this, SimpleRefreshLayoutActivity.class));
    }


    @OnClick(R.id.btn_listview)
    public void onListViewClick() {
        startActivity(new Intent(this, ListViewActivity.class));
    }


    @OnClick(R.id.btn_recycleview)
    public void onRecycleViewClick() {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }


    @OnClick(R.id.btn_scrollview)
    public void onScrollViewClick() {
        startActivity(new Intent(this, ScrollViewActivity.class));
    }


    @OnClick(R.id.btn_scroll_conflict_resolve)
    public void onScrollConflictResolveClick() {
        startActivity(new Intent(this, SimpleRefreshLayoutTwoActivity.class));
    }

}

