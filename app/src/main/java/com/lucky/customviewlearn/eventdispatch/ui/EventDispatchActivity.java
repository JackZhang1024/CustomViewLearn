package com.lucky.customviewlearn.eventdispatch.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2017/7/7.
 */

public class EventDispatchActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout parent;
    private View child;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatchevent);
        parent = (RelativeLayout) findViewById(R.id.parent);
        child  = findViewById(R.id.child);
        parent.setOnClickListener(this);
        //2 Touch事件是否消费掉于是否注册事件没有关系
        //child.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.parent:
               System.out.println("Parent Click!!!!");
               break;
           case R.id.child:
               System.out.println("Child Click!!!");
               break;
       }
    }
}
