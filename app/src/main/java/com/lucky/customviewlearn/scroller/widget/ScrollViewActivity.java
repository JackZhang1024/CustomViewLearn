package com.lucky.customviewlearn.scroller.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.core.DisplayUtil;

public class ScrollViewActivity extends AppCompatActivity {

    private SmartScrollView mScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        mScrollView = findViewById(R.id.scrollview);
        LinearLayout contentLayout = findViewById(R.id.ll_content);
        addContentViews(contentLayout);
    }

    private void addContentViews(LinearLayout contentLayout) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, DisplayUtil.dip22px(this, 60));
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int index = 0; index < 100; index++) {
            View childView = inflater.inflate(R.layout.layout_list_item, null);
            TextView tvContent = childView.findViewById(R.id.tv_content);
            tvContent.setText(String.format("数据%s", index));
            contentLayout.addView(childView, layoutParams);
        }
    }


}
