package com.lucky.customviewlearn.eventdispatch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2017/7/7.
 *
 * 滑动冲突 https://www.jianshu.com/p/8bc0765dffc9
 *
 * @author zfz
 */

public class EventDispatchActivity extends AppCompatActivity{
    private static final String TAG = "EventDispatchActivity";
    private LinearLayout parent;
    private View child;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatchevent);
        parent = (LinearLayout) findViewById(R.id.viewgroupA);
        child  = findViewById(R.id.child);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent: ");
        return true;
    }

}
