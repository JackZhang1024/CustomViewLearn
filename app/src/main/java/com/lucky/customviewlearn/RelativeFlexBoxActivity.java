package com.lucky.customviewlearn;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.JustifyContent;
import com.lucky.customviewlearn.view.ZiRuFlexBoxLayout;
import com.lucky.customviewlearn.view.ZiRuRelativeFlexLayout;

public class RelativeFlexBoxActivity extends AppCompatActivity {

    private LinearLayout llRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_flex);
        llRootView = (LinearLayout) findViewById(R.id.ll_root);
        addRelativeFlexBoxInFlexBox();
        addFlexBoxInRelativeFlexBox();
    }

    //relativeFlex添加FlexBox 文本添加到FlexBox  一个添加到相对布局上
    //FlexBox添加relativeFlex

    // FlexBox添加RelativeLayout
    private void addRelativeFlexBoxInFlexBox() {
        ZiRuFlexBoxLayout flexBoxLayout = new ZiRuFlexBoxLayout(this);
        //flexBoxLayout.setBackgroundColor(Color.GREEN);

        ZiRuRelativeFlexLayout ziRuRelativeFlexLayout = new ZiRuRelativeFlexLayout(this);
        ziRuRelativeFlexLayout.setBackgroundColor(Color.GREEN);
//        ZiRuRelativeLayout ziRuRelativeFlexLayout = new ZiRuRelativeLayout(this);

        FlexboxLayout.LayoutParams flexParmas = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //对外提供的是FlexBox参数
        //ziRuRelativeFlexLayout.setLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);// 300
        // TODO: 2018/12/7 很重要 这样RelativeLayout和内部的FlexBoxLayout是宽高一样的
        // 600 300 是从styleObject中拿到的
        //ziRuRelativeFlexLayout.setLayoutParams(600, 300, flexParmas);// 300
        ziRuRelativeFlexLayout.setLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, flexParmas);// 300
        // TODO: 2018/12/7 需要设置内部的FlexBoxLayout的样式参数
        ziRuRelativeFlexLayout.setFlexLayoutParams(0, "");
//        flexParmas.width = 600;
//        flexParmas.height = 300;
//        flexParmas.leftMargin = 20;
//        flexParmas.topMargin = 20;
        flexBoxLayout.addView(ziRuRelativeFlexLayout, flexParmas);
        //添加到Flex布局中后如何控制真正的宽高?

        LinearLayout.LayoutParams flexLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llRootView.addView(flexBoxLayout, flexLayoutParams);


        // 添加到FlexBox中
        TextView tvTitle = new TextView(this);
        tvTitle.setText("手机密码登录");
        tvTitle.setGravity(Gravity.CENTER);
        // TODO: 2018/12/7 根据position来确定需要FlexBox布局参数 还是RelativeLayout参数 position不为空 Relativelayout参数
        FlexboxLayout.LayoutParams titleFlexParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //titleFlexParams.setFlexBasisPercent(1.0f);
        ziRuRelativeFlexLayout.addView("", tvTitle, titleFlexParams);

        TextView tvPositive = new TextView(this);
        tvPositive.setText("确定");
        tvPositive.setGravity(Gravity.CENTER);
        // TODO: 2018/12/7 根据position来确定需要FlexBox布局参数 还是RelativeLayout参数 position不为空 Relativelayout参数
        FlexboxLayout.LayoutParams tvPositiveFlexParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //titleFlexParams.setFlexBasisPercent(1.0f);
        ziRuRelativeFlexLayout.addView("", tvPositive, tvPositiveFlexParams);

        // 添加到Relative中
        TextView tvCancel = new TextView(this);
        tvCancel.setText("取消");
        RelativeLayout.LayoutParams cancelRelativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cancelRelativeParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        cancelRelativeParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        cancelRelativeParams.topMargin = 0;
        cancelRelativeParams.rightMargin = 0;
        ziRuRelativeFlexLayout.addView("fixed", tvCancel, cancelRelativeParams);
    }

    // 相对布局中添加FlexBoxLayout
    private void addFlexBoxInRelativeFlexBox() {
        ZiRuRelativeFlexLayout ziRuRelativeFlexLayout = new ZiRuRelativeFlexLayout(this);

        ZiRuFlexBoxLayout ziRuFlexBoxLayout = new ZiRuFlexBoxLayout(this);
        ziRuFlexBoxLayout.setBackgroundColor(Color.YELLOW);
        ziRuFlexBoxLayout.setFlexDirection(FlexDirection.ROW);
        ziRuFlexBoxLayout.setJustifyContent(JustifyContent.SPACE_AROUND);
        FlexboxLayout.LayoutParams flexLayoutParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        TextView tvMusic = new TextView(this);
        tvMusic.setText("音乐");
        tvMusic.setGravity(Gravity.CENTER);
        FlexboxLayout.LayoutParams musicParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //titleFlexParams.setFlexBasisPercent(1.0f);
        ziRuFlexBoxLayout.addView(tvMusic, musicParams);

        TextView tvMovie = new TextView(this);
        tvMovie.setText("电影");
        tvMovie.setGravity(Gravity.CENTER);
        FlexboxLayout.LayoutParams movieParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //titleFlexParams.setFlexBasisPercent(1.0f);
        ziRuFlexBoxLayout.addView(tvMovie, movieParams);

        TextView tvMTV = new TextView(this);
        tvMTV.setText("MTV");
        tvMTV.setGravity(Gravity.CENTER);
        FlexboxLayout.LayoutParams mtvParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //titleFlexParams.setFlexBasisPercent(1.0f);
        ziRuFlexBoxLayout.addView(tvMTV, mtvParams);


        //ziRuRelativeFlexLayout.addView(ziRuFlexBoxLayout,  flexLayoutParams);
        ziRuRelativeFlexLayout.addView("", ziRuFlexBoxLayout, flexLayoutParams);

        ImageView imageTopRight = new ImageView(this);
        imageTopRight.setImageResource(R.drawable.ic_launcher);
        RelativeLayout.LayoutParams imageRelativeParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 600);
        // 这块虽然高度如果父容器的高度，但是图片的高度还是以父容器的高度为大小
        imageRelativeParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        imageRelativeParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        imageRelativeParams.topMargin = 0;
        imageRelativeParams.leftMargin = 0;
        //ziRuRelativeFlexLayout.addView("fixed", imageTopRight, imageRelativeParams);

        LinearLayout.LayoutParams ziRuRelativeParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        llRootView.addView(ziRuRelativeFlexLayout, ziRuRelativeParams);

    }


}
