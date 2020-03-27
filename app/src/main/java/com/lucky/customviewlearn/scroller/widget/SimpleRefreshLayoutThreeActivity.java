package com.lucky.customviewlearn.scroller.widget;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.core.DisplayUtil;

// 更加精细化的实现
// 自定义下拉刷新和加载更多的优化
// 优化HeaderView和FooterView
// https://github.com/Fessible/SimpleRefreshLayout/blob/master/simplerefresh/src/main/java/com/example/com/simplerefresh/SimpleRefreshLayout.java
public class SimpleRefreshLayoutThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplerefresh_two);
        final SimpleRefreshTwoLayout simpleRefreshLayout = findViewById(R.id.simple_refresh_layout);
        simpleRefreshLayout.setOnRefreshListener(new SimpleRefreshTwoLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        simpleRefreshLayout.stopRefresh();
                    }
                }, 2000);

            }

            @Override
            public void onBottomRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        simpleRefreshLayout.stopRefresh();
                    }
                }, 2000);
            }
        });
        initScrollView();
    }

    private void initScrollView() {
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
