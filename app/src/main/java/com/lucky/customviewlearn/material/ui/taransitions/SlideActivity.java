package com.lucky.customviewlearn.material.ui.taransitions;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Window;

import com.lucky.customviewlearn.R;

public class SlideActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);
            Slide slide = new Slide();
            slide.setDuration(1000);
            getWindow().setEnterTransition(slide);
            getWindow().setExitTransition(slide);
        }
        setContentView(R.layout.activity_animation);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
    }
}
