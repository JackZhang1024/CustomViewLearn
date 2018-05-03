package com.lucky.customviewlearn.canvas.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.lucky.customviewlearn.BaseActivity;
import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.canvas.ArcProgress;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zfz
 * Created by zfz on 2018/2/25.
 */

public class ArcProgressActivity extends AppCompatActivity {

    @BindView(R.id.arc_progress)
    ArcProgress mArcProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcprogress);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mHandler.postDelayed(progressTask, 1000);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mArcProgress.setProgress(msg.arg1);
            mHandler.postDelayed(progressTask, 1000);
        }
    };

    private int progress = 0;

    private Runnable progressTask = new Runnable() {

        @Override
        public void run() {
            progress += 100;
            mHandler.sendMessage(mHandler.obtainMessage(0, progress, 0));
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(progressTask);
        mHandler = null;
    }

}
