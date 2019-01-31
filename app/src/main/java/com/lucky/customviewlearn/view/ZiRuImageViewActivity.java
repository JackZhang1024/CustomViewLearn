package com.lucky.customviewlearn.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lucky.customviewlearn.R;

public class ZiRuImageViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ziruimage_view);
        createImageView();
    }

    private void createImageView() {
        String url = "http://pic14.nipic.com/20110605/1369025_165540642000_2.jpg";
        ZiRuImageViewOld ziRuImageViewOld = (ZiRuImageViewOld) findViewById(R.id.img_display);
        ziRuImageViewOld.setRadius(true, 10);
        ziRuImageViewOld.setScaleType(ImageView.ScaleType.FIT_XY);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_scenary);
        RoundImageDrawable roundImageDrawable = new RoundImageDrawable(bitmap);
        roundImageDrawable.setRound(10);
        ziRuImageViewOld.setBackground(roundImageDrawable);
        ziRuImageViewOld.setImageResource(url);
//        ziRuImageView.setOutlineProvider(new CustomOutlineProvider());
//        ziRuImageView.setClipToOutline(true);
    }

}
