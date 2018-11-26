package com.lucky.customviewlearn.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.view.style.BorderStyle;

import java.util.ArrayList;

public class ZiRuLinearLayoutActivity extends AppCompatActivity {

    private ZiRuLinearLayout mZiRuLinearlayout;
    private ZiRuLinearLayoutNew mZiRuLinearLayoutNewOutter;
    private ZiRuLinearLayoutNew mZiRuLinearLayoutInner;
    private ZiRuImageView mZiruImageView;
    private ZiRuImageTextView mZiruImageTextView;
    private ZiRuRelativeLayout mZiRuRelativeLayout;
    private ZiRuOuterRelativeLayout mZiRuRelativeParent;
    private TextView mTvContent;
    private ZiRuRecycleView mZiRuRecycleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zirulinearlayout);
        createImageView();
        roundCorner();
    }


    private void createRecycleView() {
        //mZiRuRecycleView = (ZiRuRecycleView) findViewById(R.id.ziru_recycleview);
        mZiRuRecycleView = new ZiRuRecycleView(this);
        LinearLayout llRootView = (LinearLayout) findViewById(R.id.ll_content);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //mZiRuRecycleView.setBackgroundColor(Color.RED);
        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("Jack1");
        dataList.add("Jack2");
        dataList.add("Jack3");
        //ZiRuViewItem ziRuViewItem = new ZiRuViewItem(this);
        ZiRuRecycleAdapter ziRuRecycleAdapter = new ZiRuRecycleAdapter(dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mZiRuRecycleView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        mZiRuRecycleView.setLayoutManager(layoutManager);
        mZiRuRecycleView.setAdapter(ziRuRecycleAdapter);
        //ziRuRecycleAdapter.notifyDataSetChanged();
        llRootView.addView(mZiRuRecycleView, params);
    }

    private void roundCorner() {
        String url = "http://pic14.nipic.com/20110605/1369025_165540642000_2.jpg";
        ZiRuFlexBoxLayout flexBoxLayout = new ZiRuFlexBoxLayout(this);
        ZiRuImageView imageView = new ZiRuImageView(this);
        imageView.setImageResource(url);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        FlexboxLayout.LayoutParams imageParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        flexBoxLayout.addView(imageView, imageParams);

        LinearLayout llRootView = (LinearLayout) findViewById(R.id.ll_content);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
        params.leftMargin = 20;
        params.rightMargin = 20;
        flexBoxLayout.setRadius(true, 80);
        flexBoxLayout.setBorderWidth(10);
        llRootView.addView(flexBoxLayout, params);
    }

    private void createImageView(){
        String url = "http://pic14.nipic.com/20110605/1369025_165540642000_2.jpg";
        ZiRuImageView ziRuImageView = (ZiRuImageView) findViewById(R.id.img_ziru);
        ziRuImageView.setRadius(true,10);
        ziRuImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ziRuImageView.setImageResource(url);
    }

}
