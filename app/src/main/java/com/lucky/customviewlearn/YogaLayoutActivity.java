package com.lucky.customviewlearn;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.soloader.SoLoader;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.android.YogaLayout;

//https://cloud.tencent.com/developer/article/1006148
//https://github.com/facebook/yoga/tree/master/android
//https://github.com/nickma1986/YogaLayoutDemo
//https://www.jianshu.com/p/d4289b16a133

public class YogaLayoutActivity extends AppCompatActivity {

    private static final String TAG = "YogaLayoutActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_love);
        SoLoader.init(this, false);
        create();
    }

    private void create() {
        LinearLayout llRootContent = (LinearLayout) findViewById(R.id.ll_root);

        int width = getWindowManager().getDefaultDisplay().getWidth()/2;
        int height = getWindowManager().getDefaultDisplay().getHeight()/2;
        Log.e(TAG, "create: width "+width+" height "+height);
        // 1. 设置节点
        YogaNode root = new YogaNode();
        root.setWidth(width);
        root.setHeight(height);
        //root.setJustifyContent(YogaJustify.SPACE_BETWEEN);
        root.setFlexDirection(YogaFlexDirection.ROW);
        //root.setAlignItems(YogaAlign.CENTER);

        llRootContent.getLayoutParams().width = width;
        llRootContent.getLayoutParams().height = height;
        llRootContent.setBackgroundColor(Color.RED);
        llRootContent.requestLayout();

        YogaNode textMusic = new YogaNode();
        // 2. 设置属性
        textMusic.setWidth(300);
        textMusic.setHeight(600);
        textMusic.setMargin(YogaEdge.ALL, 15 * getResources().getDisplayMetrics().density);
        root.addChildAt(textMusic, 0);

        YogaNode imageMovie = new YogaNode();
        imageMovie.setWidth(200);
        imageMovie.setHeight(200);
        //imageMovie.setMargin(YogaEdge.ALL, 15 * getResources().getDisplayMetrics().density);
        imageMovie.setAlignSelf(YogaAlign.FLEX_END);
        root.addChildAt(imageMovie, 1);

        // 3. 计算布局
        root.calculateLayout(width*2, height*2);

        // 4. 添加子View到根布局
        TextView imageViewMusic = new TextView(this);
        imageViewMusic.setX(textMusic.getLayoutX());
        imageViewMusic.setY(textMusic.getLayoutY());
        imageViewMusic.setText("music");
        imageViewMusic.setBackgroundColor(Color.YELLOW);
        llRootContent.addView(imageViewMusic);

        TextView imageViewMovie = new TextView(this);
        imageViewMovie.setX(imageMovie.getLayoutX());
        imageViewMovie.setY(imageMovie.getLayoutY());
        imageViewMovie.setText("movie");
        imageViewMovie.setBackgroundColor(Color.YELLOW);
        llRootContent.addView(imageViewMovie);

        Log.e(TAG, "create: music x "+ textMusic.getLayoutX()+" y "+ textMusic.getLayoutY());
        Log.e(TAG, "create: movie x "+imageMovie.getLayoutX()+" y "+imageMovie.getLayoutY());

        YogaLayout yogaLayout = new YogaLayout(this);
        //yogaLayout.addView();
    }

}
