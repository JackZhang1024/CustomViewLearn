package com.lucky.customviewlearn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by zfz on 2017/7/5.
 *
 * ScrollTo与ScrollBy方法的两个参数dx,dy
 * dx>0 表示向左移动 dx<0 表示向右移动
 * dy>0 表示向上移动 dy<0 表示向下移动
 *
 */

public class ScrollerLearnActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller_learn);
        mContent = (LinearLayout) findViewById(R.id.content_scroll_method);
        findViewById(R.id.btn_scroll_to).setOnClickListener(this);
        findViewById(R.id.btn_scroll_by).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_scroll_to:
                mContent.scrollTo(100, 100);
                break;
            case R.id.btn_scroll_by:
                mContent.scrollBy(100, 20);
                break;
        }
    }
}
