package com.lucky.customviewlearn.material.ui.taransitions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

import com.lucky.customviewlearn.R;


public class ExplodeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setAllowEnterTransitionOverlap(true);
            getWindow().setAllowReturnTransitionOverlap(true);
            Explode explode = new Explode();
            explode.setDuration(1000);
            getWindow().setEnterTransition(explode);
            getWindow().setExitTransition(explode);
        }
        setContentView(R.layout.activity_animation);
    }

    @TargetApi(21)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(Build.VERSION.SDK_INT >= 21){
           finishAfterTransition();
        }
    }
}
