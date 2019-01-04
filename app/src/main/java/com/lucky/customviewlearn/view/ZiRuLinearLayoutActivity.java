package com.lucky.customviewlearn.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.utils.PicassoUtils;
import com.squareup.picasso.Target;

import java.util.ArrayList;

//https://blog.csdn.net/CSDN_LQR/article/details/79581190
public class ZiRuLinearLayoutActivity extends AppCompatActivity {

    private ZiRuLinearLayout mZiRuLinearlayout;
    private ZiRuLinearLayoutNew mZiRuLinearLayoutNewOutter;
    private ZiRuLinearLayoutNew mZiRuLinearLayoutInner;
    private ZiRuImageView mZiruImageView;
    private ZiRuImageTextView mZiruImageTextView;
    private ZiRuRelativeLayout mZiRuRelativeLayout;
    private ZiRuOuterRelativeLayout mZiRuRelativeParent;
    private TextView mTvContent;
    private ZiRuRecycleView mZiRuRecycleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zirulinearlayout);
        //displayImageView();
        //createImageView();
        //roundCorner();
        createRoundTextView();
    }

    private void displayImageView() {
        ZiRuImageView imageView = (ZiRuImageView) findViewById(R.id.img_ziru_display);
        imageView.setImageResource("http://img.dafy.com/mobile/img3.0/dianhua.png");
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }


    private void createRecycleView() {
        //mZiRuRecycleView = (ZiRuRecycleView) findViewById(R.id.ziru_recycleview);
        mZiRuRecycleView = new ZiRuRecycleView(this);
        LinearLayout llRootView = (LinearLayout) findViewById(R.id.ll_content);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //mZiRuRecycleView.setBackgroundColor(Color.RED);
        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("Jack1");
        dataList.add("Jack2");
        dataList.add("Jack3");
        //ZiRuViewItem ziRuViewItem = new ZiRuViewItem(this);
        ZiRuRecycleAdapter ziRuRecycleAdapter = new ZiRuRecycleAdapter(dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mZiRuRecycleView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        mZiRuRecycleView.setLayoutManager(layoutManager);
        mZiRuRecycleView.setAdapter(ziRuRecycleAdapter);
        //ziRuRecycleAdapter.notifyDataSetChanged();
        llRootView.addView(mZiRuRecycleView, params);
    }

    //12-21 14:00:16.609 13228-13228/com.ziru.host E/ZiRuImageView: onBitmapLoaded: 17 22 100021 0 0
    //12-21 14:00:16.665 13228-13228/com.ziru.host E/ZiRuImageView: onDraw: 48 62 100021
    //http://img.dafy.com/mobile/img3.0/dianhua.png
    private void roundCorner() {
        // http://pic14.nipic.com/20110605/1369025_165540642000_2.jpg
        // http://img.dafy.com/mobile/img3.0/incomeBg.png
        String url = "http://pic14.nipic.com/20110605/1369025_165540642000_2.jpg";

        ZiRuFlexBoxLayout flexBoxLayout = new ZiRuFlexBoxLayout(this);
        ZiRuImageView imageView = new ZiRuImageView(this);
        imageView.setImageResource(url);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        FlexboxLayout.LayoutParams imageParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        //flexBoxLayout.addView(imageView, imageParams);

        LinearLayout llRootView = (LinearLayout) findViewById(R.id.ll_content);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
        params.leftMargin = 20;
        params.rightMargin = 20;
        flexBoxLayout.setCornerRadius(true, 0, 0, 60, 60);
        //flexBoxLayout.setBorder(20, Color.RED);
        flexBoxLayout.setAllBorders(20, 20, 20, 20);
        flexBoxLayout.setAllBorderColors("red", "red", "red", "red");
        int backgroundColor = Color.parseColor("#fff000");
        //flexBoxLayout.setBackgroundColor(backgroundColor);
        //flexBoxLayout.setBackgroundBitmap(R.drawable.bg_scenary);

        if (!TextUtils.isEmpty(url)) {
            PicassoUtils.getinstance().loadImageBitmap(this, url, flexBoxLayout);
        }

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_scenary);
//        RoundImageDrawable roundImageDrawable = new RoundImageDrawable(bitmap);
//        roundImageDrawable.setRound(80);
//        flexBoxLayout.setBackground(roundImageDrawable);

//        RoundColorDrawable roundColorDrawable = new RoundColorDrawable(Color.YELLOW);
//        roundColorDrawable.setRound(80);
//        flexBoxLayout.setBackground(roundColorDrawable);

        llRootView.addView(flexBoxLayout, params);

    }

    private void createImageView() {
        String url = "http://pic14.nipic.com/20110605/1369025_165540642000_2.jpg";
        ZiRuImageView ziRuImageView = (ZiRuImageView) findViewById(R.id.img_ziru);
        ziRuImageView.setRadius(true, 10);
        ziRuImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_scenary);
        RoundImageDrawable roundImageDrawable = new RoundImageDrawable(bitmap);
        roundImageDrawable.setRound(10);
        ziRuImageView.setBackground(roundImageDrawable);
        ziRuImageView.setImageResource(url);
//        ziRuImageView.setOutlineProvider(new CustomOutlineProvider());
//        ziRuImageView.setClipToOutline(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public class CustomOutlineProvider extends ViewOutlineProvider {
        private static final String TAG = "CustomOutlineProvider";

        @Override
        public void getOutline(View view, Outline outline) {
            Rect rect = new Rect();
            view.getGlobalVisibleRect(rect);
            int leftMargin = 0;
            int topMargin = 0;
            Rect selfRect = new Rect(leftMargin, topMargin, rect.right - rect.left - leftMargin, rect.bottom - rect.top - topMargin);
            int width = view.getWidth();
            int height = view.getHeight();
            Log.e(TAG, "getOutline: " + width + " " + height);
            outline.setRoundRect(selfRect, width / 2);
        }
    }

    private void clipBackGround(ImageView imageView) {
        Rect originRect = new Rect();
        //getGlobalVisibleRect()相对与父布局的rect
        imageView.getGlobalVisibleRect(originRect);
        int centerX = (originRect.right - originRect.left) / 2;
        int centerY = (originRect.bottom - originRect.top) / 2;
        //设置View的显示区域，坐标是自身
        Rect tmp = new Rect(centerX - 150, centerY - 150, centerX + 150, centerY + 150);
        imageView.setClipBounds(tmp);
    }

    // 圆角支持 四个圆角都支持 [0.2rem, 0.2rem , 0.2rem, 0.2rem]
    private void createRoundTextView() {
        ZiRuTextView textView = new ZiRuTextView(this);
        LinearLayout llRootView = (LinearLayout) findViewById(R.id.ll_content);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        params.leftMargin  = 20;
        params.rightMargin = 20;
        textView.setAllBorders(3, 3, 3, 3);
        textView.setAllBorderColors("red", "red", "red", "red");
        textView.setCornerRadius(true, 20, 20, 10, 10);
        textView.setBackgroundColor(Color.YELLOW);
        textView.setTextColor(Color.RED);
        llRootView.addView(textView, params);
        textView.setText("Hello World!");
        textView.setGravity(Gravity.CENTER);
    }


}
