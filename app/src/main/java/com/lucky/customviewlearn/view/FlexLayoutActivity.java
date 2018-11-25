package com.lucky.customviewlearn.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.AlignContent;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.JustifyContent;
import com.lucky.customviewlearn.R;

// https://cloud.tencent.com/developer/article/1354252
// https://www.jianshu.com/p/b3a9c4a99053


/*
 *  * The supported attributes that you can use are:
 * <ul>
 * <li>{@code flexDirection}</li>
 * <li>{@code flexWrap}</li>
 * <li>{@code justifyContent}</li>
 * <li>{@code alignItems}</li>
 * <li>{@code alignContent}</li>
 * <li>{@code showDivider}</li>
 * <li>{@code showDividerHorizontal}</li>
 * <li>{@code showDividerVertical}</li>
 * <li>{@code dividerDrawable}</li>
 * <li>{@code dividerDrawableHorizontal}</li>
 * <li>{@code dividerDrawableVertical}</li>
 * <li>{@code maxLine}</li>
 * </ul>
 * for the FlexboxLayout.
 *
 * And for the children of the FlexboxLayout, you can use:
 * <ul>
 * <li>{@code layout_order}</li>
 * <li>{@code layout_flexGrow}</li>
 * <li>{@code layout_flexShrink}</li>
 * <li>{@code layout_flexBasisPercent}</li>
 * <li>{@code layout_alignSelf}</li>
 * <li>{@code layout_minWidth}</li>
 * <li>{@code layout_minHeight}</li>
 * <li>{@code layout_maxWidth}</li>
 * <li>{@code layout_maxHeight}</li>
 * <li>{@code layout_wrapBefore}</li>
 * </ul>
 *
 *  https://www.jianshu.com/p/6ab9808488f6
 *  与传统CSS弹性布局不同之处
 *  没有flex-flow属性 ：因为没必要 没有flex属性：同样没必要layout_flexBasisPercent 替代了flexBasis。
 *  如果子元素宽高确定了，可以指定具体值或百分比，如果是包裹内容，那只能是百分比不能确定min-width和min-height：因为谷歌还没实现
 *
 *  默认是row 横向布局 如果需要设置竖向 则需要设置成FlexDirection设置成竖向column
 *
 *  比较详细的介绍
 *  https://blog.csdn.net/etwge/article/details/80434100
 *
 * */

public class FlexLayoutActivity extends AppCompatActivity {

    private LinearLayout mRootView;
    FlexboxLayout mFlexboxLayoutNavigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
//        linearLayout.setBackgroundColor(Color.RED);
//        setContentView(linearLayout);

        setContentView(R.layout.activity_flexlayout_ziru);
        mRootView = (LinearLayout) findViewById(R.id.root_content);
        Button btnAddMenu = (Button) mRootView.findViewById(R.id.btn_add_menu);
        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tvFood = new TextView(FlexLayoutActivity.this);
                tvFood.setText("美食");
                tvFood.setGravity(Gravity.CENTER);
                tvFood.setTextSize(16);
                FlexboxLayout.LayoutParams layoutParamsFood = new FlexboxLayout.LayoutParams(160, 160);
                layoutParamsFood.setFlexGrow(1.0f);
                layoutParamsFood.setFlexShrink(1.0f);
                layoutParamsFood.setFlexBasisPercent(0.0f);
                layoutParamsFood.setOrder(4);
                mFlexboxLayoutNavigation.addView(tvFood, layoutParamsFood);
            }
        });
        addFlexBoxLayout();
    }

    private void addFlexBoxLayout() {
        createHorizontalVerticalCenter();
        createNavigation();
        createLeftPicRightText();
        createMultipleColumns();
        createPercentFixedLayout();
        createOneFixedLayout();
        createTwoFixedLayout();
        createThreeFixedLayout();
        createHollyCupLayout();
        createleftRightInputLayout();
        createleftInputLayout();
        createRightInputLayout();
        createBottomFixedLayout();
        createFiveFlowLayout();
        createSevenFlowLayout();
        createComplexFlexBoxLayout();
    }

    private void addViewInFlexBoxLayout(FlexboxLayout flexboxLayout) {
        TextView tvBack = new TextView(this);
        tvBack.setText("返回");
        tvBack.setGravity(Gravity.CENTER);
        FlexboxLayout.LayoutParams layoutParamsBack = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsBack.setOrder(1);
        layoutParamsBack.setFlexBasisPercent(0.1f);
        //layoutParamsBack.setFlexGrow(0.1f);
        flexboxLayout.setAlignItems(AlignItems.CENTER);
        //layoutParamsBack.setAlignSelf(AlignSelf.FLEX_START);
        flexboxLayout.addView(tvBack, layoutParamsBack);

        TextView tvTitle = new TextView(this);
        tvTitle.setText("借款页面");
        tvTitle.setGravity(Gravity.CENTER);
        FlexboxLayout.LayoutParams layoutParamsTitle = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsTitle.setOrder(2);
        layoutParamsTitle.setFlexBasisPercent(0.8f);
        flexboxLayout.setAlignItems(AlignItems.CENTER);
        flexboxLayout.addView(tvTitle, layoutParamsTitle);


        TextView tvMenu = new TextView(this);
        tvMenu.setText("菜单");
        tvMenu.setGravity(Gravity.CENTER);
        FlexboxLayout.LayoutParams layoutParamsMenu = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsMenu.setOrder(3);
        layoutParamsMenu.setFlexBasisPercent(0.1f);
        //layoutParamsMenu.setAlignSelf(AlignSelf.FLEX_END);
        flexboxLayout.setAlignItems(AlignItems.CENTER);
        flexboxLayout.addView(tvMenu, layoutParamsMenu);

    }


    // 1 水平居中 垂直居中 默认是row
    private void createHorizontalVerticalCenter() {
        FlexboxLayout flexboxLayout = new FlexboxLayout(this);

        // 从左向右排列
        flexboxLayout.setFlexDirection(FlexDirection.COLUMN);
        // 水平方向上居中
        flexboxLayout.setJustifyContent(JustifyContent.CENTER);
        // 垂直于显示方向上的对齐方式 居中对齐
        flexboxLayout.setAlignItems(AlignItems.CENTER);
        TextView tvContent = new TextView(this);
        tvContent.setText("这是一个测试");
        tvContent.setTextSize(16);
        FlexboxLayout.LayoutParams layoutParamsBack = new FlexboxLayout.LayoutParams(160, 160);
        flexboxLayout.addView(tvContent, layoutParamsBack);

        TextView tvTitle = new TextView(this);
        tvTitle.setText("这是一个测试");
        tvTitle.setTextSize(16);
        FlexboxLayout.LayoutParams layoutParamsTitle = new FlexboxLayout.LayoutParams(160, 160);
        flexboxLayout.addView(tvTitle, layoutParamsTitle);


        TextView tvMenu = new TextView(this);
        tvMenu.setText("这是一个测试");
        tvMenu.setTextSize(16);
        FlexboxLayout.LayoutParams layoutParamsMenu = new FlexboxLayout.LayoutParams(160, 160);
        //layoutParamsMenu.setOrder(3);
        flexboxLayout.addView(tvMenu, layoutParamsMenu);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(500, 300);
        flexboxLayout.setBackgroundColor(Color.GREEN);
        mRootView.addView(flexboxLayout, llParams);
    }

    // 2. 用flex布局制作导航栏
    private void createNavigation() {
        mFlexboxLayoutNavigation = new FlexboxLayout(this);
        // 从左向右排列
//        mFlexboxLayoutNavigation.setFlexDirection(FlexDirection.ROW);
        mFlexboxLayoutNavigation.setFlexDirection(FlexDirection.ROW);
//        mFlexboxLayoutNavigation.setFlexWrap(FlexWrap.WRAP);
//        mFlexboxLayoutNavigation.setJustifyContent(JustifyContent.CENTER);
//        mFlexboxLayoutNavigation.setAlignItems(AlignItems.STRETCH);
//        mFlexboxLayoutNavigation.setAlignContent(AlignContent.STRETCH);

        TextView tvMusic = new TextView(this);
        tvMusic.setText("音乐");
        tvMusic.setGravity(Gravity.CENTER);
        tvMusic.setTextSize(16);
        tvMusic.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsMusic = new FlexboxLayout.LayoutParams(600, 200);
        // flex:1 == flex-grow:1.0 flex-shrink:1.0 flex-bais:0.0
        layoutParamsMusic.setOrder(1);
        // 如果是具体的值 拉伸问题stretch  根据FlexDirection来设置maxHeight或者maxWidth
        layoutParamsMusic.setMaxHeight(200);
//        layoutParamsBack.setFlexGrow(1.0f);
//        layoutParamsBack.setFlexShrink(1.0f);
//        layoutParamsBack.setFlexBasisPercent(0.1f);
        layoutParamsMusic.setAlignSelf(AlignSelf.FLEX_START);
        mFlexboxLayoutNavigation.addView(tvMusic, layoutParamsMusic);

        TextView tvMovie = new TextView(this);
        tvMovie.setText("影视");
        tvMovie.setBackgroundColor(Color.YELLOW);
        tvMovie.setGravity(Gravity.CENTER);
        tvMovie.setTextSize(16);
        FlexboxLayout.LayoutParams layoutParamsMovie = new FlexboxLayout.LayoutParams(0, 0);
        layoutParamsMovie.setMaxHeight(400);
        layoutParamsMovie.width = 600;
        layoutParamsMovie.height = 400;
        layoutParamsMovie.setAlignSelf(AlignSelf.FLEX_START);
//        layoutParamsTitle.setFlexGrow(1.0f);
//        layoutParamsTitle.setFlexShrink(1.0f);
//        layoutParamsTitle.setFlexBasisPercent(0.0f);

        layoutParamsMovie.setOrder(2);
        mFlexboxLayoutNavigation.addView(tvMovie, layoutParamsMovie);
//
//
//        TextView tvMenu = new TextView(this);
//        tvMenu.setText("旅游");
//        tvMenu.setTextSize(16);
//        tvMenu.setBackgroundColor(Color.BLUE);
//        tvMenu.setGravity(Gravity.CENTER);
//        FlexboxLayout.LayoutParams layoutParamsMenu = new FlexboxLayout.LayoutParams(200, 100);
//        layoutParamsMenu.setOrder(3);
////        layoutParamsMenu.setMaxHeight(50);
////        layoutParamsMenu.setFlexGrow(1.0f);
////        layoutParamsMenu.setFlexShrink(1.0f);
////        layoutParamsMenu.setFlexBasisPercent(0.0f);
//        mFlexboxLayoutNavigation.addView(tvMenu, layoutParamsMenu);

//        for (int index=0; index< 3; index++){
//            TextView tvOrange = new TextView(this);
//            tvOrange.setText("旅游"+index);
//            tvOrange.setTextSize(16);
//            tvOrange.setBackgroundColor(Color.BLUE);
//            tvOrange.setGravity(Gravity.CENTER);
//            FlexboxLayout.LayoutParams layoutParamsOrange = new FlexboxLayout.LayoutParams(400, 100);
//            layoutParamsOrange.setOrder(3);
//            //        layoutParamsMenu.setMaxHeight(50);
//            //        layoutParamsMenu.setFlexGrow(1.0f);
//            //        layoutParamsMenu.setFlexShrink(1.0f);
//            //        layoutParamsMenu.setFlexBasisPercent(0.0f);
//            mFlexboxLayoutNavigation.addView(tvOrange, layoutParamsOrange);
//        }

        // TODO: 2018/11/13 平分布局 如果FlexBoxLayout的布局参数是MATCH_PARENT或者固定值 设置flex参数之后 子控件之前设置的布局参数失效 只有相应的FlexBoxLayout布局参数生效
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1000);
        mFlexboxLayoutNavigation.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(mFlexboxLayoutNavigation, llParams);
    }


    // 3. 有时候需要做成左图右文字的样式，如下图所示：
    /*
     * Iphone7 PLUS XXXXXXXXXX
     * 总人数99 剩余人数33
     * 立即参与
     * */
    private void createLeftPicRightText() {
        FlexboxLayout flexboxLayout = new FlexboxLayout(this);
        // 从左向右排列
        flexboxLayout.setFlexDirection(FlexDirection.ROW);
        flexboxLayout.setJustifyContent(JustifyContent.SPACE_AROUND);

        LinearLayout llPicLeft = new LinearLayout(this);
        FlexboxLayout.LayoutParams layoutParamsBack = new FlexboxLayout.LayoutParams(200, 200);
        llPicLeft.setBackgroundColor(Color.RED);
        flexboxLayout.addView(llPicLeft, layoutParamsBack);


        LinearLayout llContentRight = new LinearLayout(this);
        FlexboxLayout.LayoutParams llContentRightParams = new FlexboxLayout.LayoutParams(380, 200);
        llContentRight.setBackgroundColor(Color.BLUE);
        flexboxLayout.addView(llContentRight, llContentRightParams);

        TextView tvIphone = new TextView(this);
        tvIphone.setText("Iphone7 PLUS XXXXXXXXXX");
        tvIphone.setTextSize(16);
        LinearLayout.LayoutParams layoutParamsTitle = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llContentRight.addView(tvIphone, layoutParamsTitle);


        TextView tvTotalPeople = new TextView(this);
        tvTotalPeople.setText("总人数99 剩余人数33");
        tvTotalPeople.setTextSize(16);
        FlexboxLayout.LayoutParams layoutParamsMenu = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llContentRight.addView(tvTotalPeople, layoutParamsMenu);

        // TODO: 2018/11/13 注意 设置填充父布局 MATCH_PARENT
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        flexboxLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(flexboxLayout, llParams);
    }

    // 15. 创建多列布局
    private void createMultipleColumns() {
        FlexboxLayout flexboxLayout = new FlexboxLayout(this);
        // 从左向右排列
        flexboxLayout.setFlexDirection(FlexDirection.ROW);
        flexboxLayout.setJustifyContent(JustifyContent.SPACE_AROUND);

        // 左
        LinearLayout llPicLeft = new LinearLayout(this);
        FlexboxLayout.LayoutParams layoutParamsLeft = new FlexboxLayout.LayoutParams(200, 200);
        llPicLeft.setBackgroundColor(Color.RED);
        flexboxLayout.addView(llPicLeft, layoutParamsLeft);

        // 中
        LinearLayout llCenter = new LinearLayout(this);
        llCenter.setOrientation(LinearLayout.VERTICAL);
        FlexboxLayout.LayoutParams layoutParamsCenter = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llCenter.setBackgroundColor(Color.RED);
        for (int index = 0; index < 3; index++) {
            TextView tvPositive = new TextView(this);
            tvPositive.setText("description");
            tvPositive.setTextSize(16);
            LinearLayout.LayoutParams layoutParamsDescription = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            llCenter.addView(tvPositive, layoutParamsDescription);
        }
        flexboxLayout.addView(llCenter, layoutParamsCenter);

        // 右
        LinearLayout llRight = new LinearLayout(this);
        FlexboxLayout.LayoutParams llContentParamsRight = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llRight.setBackgroundColor(Color.BLUE);

        TextView tvPositive = new TextView(this);
        tvPositive.setText("确认");
        tvPositive.setTextSize(16);
        LinearLayout.LayoutParams layoutParamsPositive = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llRight.addView(tvPositive, layoutParamsPositive);


        TextView tvCancel = new TextView(this);
        tvCancel.setText("取消");
        tvCancel.setTextSize(16);
        FlexboxLayout.LayoutParams layoutParamsCancel = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llRight.addView(tvCancel, layoutParamsCancel);
        flexboxLayout.addView(llRight, llContentParamsRight);

        // TODO: 2018/11/13 注意 设置填充父布局 MATCH_PARENT
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(800, 300);
        flexboxLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(flexboxLayout, llParams);

    }

    // 4. 固定百分比布局：
    private void createPercentFixedLayout() {
        FlexboxLayout mSpaceEvenLayout = new FlexboxLayout(this);
        mSpaceEvenLayout.setFlexDirection(FlexDirection.ROW);

        TextView tvBack = new TextView(this);
        tvBack.setText("1/4");
        tvBack.setGravity(Gravity.CENTER);
        tvBack.setTextSize(16);
        tvBack.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsBack = new FlexboxLayout.LayoutParams(160, 160);
        layoutParamsBack.setFlexGrow(1.0f);
        layoutParamsBack.setFlexShrink(1.0f);
        //layoutParamsBack.setFlexBasisPercent(0.0f);
        mSpaceEvenLayout.addView(tvBack, layoutParamsBack);

        TextView tvTitle = new TextView(this);
        tvTitle.setText("1/4");
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setTextSize(16);
        tvTitle.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsTitle = new FlexboxLayout.LayoutParams(160, 160);
        layoutParamsTitle.setFlexGrow(1.0f);
        layoutParamsTitle.setFlexShrink(1.0f);
        //layoutParamsTitle.setFlexBasisPercent(0.0f);
        mSpaceEvenLayout.addView(tvTitle, layoutParamsTitle);


        TextView tvMenu = new TextView(this);
        tvMenu.setText("1/4");
        tvMenu.setTextSize(16);
        tvMenu.setGravity(Gravity.CENTER);
        tvMenu.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsMenu = new FlexboxLayout.LayoutParams(160, 160);
        layoutParamsMenu.setFlexGrow(1.0f);
        layoutParamsMenu.setFlexShrink(1.0f);
        //layoutParamsMenu.setFlexBasisPercent(0.0f);
        mSpaceEvenLayout.addView(tvMenu, layoutParamsMenu);


        TextView tvCamera = new TextView(this);
        tvCamera.setText("1/4");
        tvCamera.setTextSize(16);
        tvCamera.setGravity(Gravity.CENTER);
        tvCamera.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsCamera = new FlexboxLayout.LayoutParams(160, 160);
        layoutParamsCamera.setFlexGrow(1.0f);
        layoutParamsCamera.setFlexShrink(1.0f);
        //layoutParamsMenu.setFlexBasisPercent(0.0f);
        mSpaceEvenLayout.addView(tvCamera, layoutParamsCamera);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 300);
        mSpaceEvenLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(mSpaceEvenLayout, llParams);
    }


    //5. 某一个固定
    private void createOneFixedLayout() {
        FlexboxLayout mSpaceEvenLayout = new FlexboxLayout(this);
        mSpaceEvenLayout.setFlexDirection(FlexDirection.ROW);

        TextView tvBack = new TextView(this);
        tvBack.setText("auto");
        tvBack.setGravity(Gravity.CENTER);
        tvBack.setTextSize(16);
        tvBack.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsBack = new FlexboxLayout.LayoutParams(160, 160);
        layoutParamsBack.setFlexGrow(1.0f);
        layoutParamsBack.setFlexShrink(1.0f);
        layoutParamsBack.setFlexBasisPercent(0.0f);
        mSpaceEvenLayout.addView(tvBack, layoutParamsBack);

        //flex  flex-grow 0 flex-shrink 0 flex-bais 0.5
        TextView tvTitle = new TextView(this);
        tvTitle.setText("1/2");
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setTextSize(16);
        tvTitle.setBackgroundColor(Color.BLUE);
        FlexboxLayout.LayoutParams layoutParamsTitle = new FlexboxLayout.LayoutParams(160, 160);
        layoutParamsTitle.setFlexGrow(0.0f);
        layoutParamsTitle.setFlexShrink(0.0f);
        layoutParamsTitle.setFlexBasisPercent(0.5f);
        mSpaceEvenLayout.addView(tvTitle, layoutParamsTitle);


        TextView tvMenu = new TextView(this);
        tvMenu.setText("auto");
        tvMenu.setTextSize(16);
        tvMenu.setGravity(Gravity.CENTER);
        tvMenu.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsMenu = new FlexboxLayout.LayoutParams(160, 160);
        layoutParamsMenu.setFlexGrow(1.0f);
        layoutParamsMenu.setFlexShrink(1.0f);
        layoutParamsMenu.setFlexBasisPercent(0.0f);
        mSpaceEvenLayout.addView(tvMenu, layoutParamsMenu);


        TextView tvCamera = new TextView(this);
        tvCamera.setText("auto");
        tvCamera.setTextSize(16);
        tvCamera.setGravity(Gravity.CENTER);
        tvCamera.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsCamera = new FlexboxLayout.LayoutParams(160, 160);
        layoutParamsCamera.setFlexGrow(1.0f);
        layoutParamsCamera.setFlexShrink(1.0f);
        layoutParamsMenu.setFlexBasisPercent(0.0f);
        mSpaceEvenLayout.addView(tvCamera, layoutParamsCamera);

        // TODO: 2018/11/13 注意 设置填充父布局 MATCH_PARENT setFlexBaispercent应该只有在父布局是MATCH_PARENT和固定值的时候才会起作用
        // LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  300);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(700, 300);
        mSpaceEvenLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(mSpaceEvenLayout, llParams);
    }

    //6. 某两个固定
    private void createTwoFixedLayout() {
        FlexboxLayout mSpaceEvenLayout = new FlexboxLayout(this);
        mSpaceEvenLayout.setFlexDirection(FlexDirection.ROW);

        TextView tvBack = new TextView(this);
        tvBack.setText("auto");
        tvBack.setGravity(Gravity.CENTER);
        tvBack.setTextSize(16);
        tvBack.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsBack = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsBack.setFlexGrow(1.0f);
        layoutParamsBack.setFlexShrink(1.0f);
        layoutParamsBack.setFlexBasisPercent(0.0f);
        mSpaceEvenLayout.addView(tvBack, layoutParamsBack);

        //flex  flex-grow 0 flex-shrink 0 flex-bais 0.5
        TextView tvTitle = new TextView(this);
        tvTitle.setText("1/2");
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setTextSize(16);
        tvTitle.setBackgroundColor(Color.BLUE);
        FlexboxLayout.LayoutParams layoutParamsTitle = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsTitle.setFlexGrow(0.0f);
        layoutParamsTitle.setFlexShrink(0.0f);
        layoutParamsTitle.setFlexBasisPercent(0.5f);
        mSpaceEvenLayout.addView(tvTitle, layoutParamsTitle);


        TextView tvMenu = new TextView(this);
        tvMenu.setText("auto");
        tvMenu.setTextSize(16);
        tvMenu.setGravity(Gravity.CENTER);
        tvMenu.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsMenu = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsMenu.setFlexGrow(1.0f);
        layoutParamsMenu.setFlexShrink(1.0f);
        layoutParamsMenu.setFlexBasisPercent(0.0f);
        mSpaceEvenLayout.addView(tvMenu, layoutParamsMenu);


        TextView tvCamera = new TextView(this);
        tvCamera.setText("1/5");
        tvCamera.setTextSize(16);
        tvCamera.setGravity(Gravity.CENTER);
        tvCamera.setBackgroundColor(Color.GRAY);
        FlexboxLayout.LayoutParams layoutParamsCamera = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsCamera.setFlexGrow(1.0f);
        layoutParamsCamera.setFlexShrink(1.0f);
        layoutParamsMenu.setFlexBasisPercent(0.2f);
        mSpaceEvenLayout.addView(tvCamera, layoutParamsCamera);

        // TODO: 2018/11/13 注意 设置填充父布局 MATCH_PARENT setFlexBaispercent应该只有在父布局是MATCH_PARENT和固定值的时候才会起作用
        // LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  300);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(800, 300);
        mSpaceEvenLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(mSpaceEvenLayout, llParams);
    }


    //7. 某三个固定
    private void createThreeFixedLayout() {
        FlexboxLayout mSpaceEvenLayout = new FlexboxLayout(this);
        mSpaceEvenLayout.setFlexDirection(FlexDirection.ROW);

        TextView tvBack = new TextView(this);
        tvBack.setText("1/10");
        tvBack.setGravity(Gravity.CENTER);
        tvBack.setTextSize(16);
        tvBack.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsBack = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsBack.setFlexGrow(1.0f);
        layoutParamsBack.setFlexShrink(1.0f);
        layoutParamsBack.setFlexBasisPercent(0.1f);
        mSpaceEvenLayout.addView(tvBack, layoutParamsBack);

        //flex  flex-grow 0 flex-shrink 0 flex-bais 0.5
        TextView tvTitle = new TextView(this);
        tvTitle.setText("1/2");
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setTextSize(16);
        tvTitle.setBackgroundColor(Color.BLUE);
        FlexboxLayout.LayoutParams layoutParamsTitle = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsTitle.setFlexGrow(0.0f);
        layoutParamsTitle.setFlexShrink(0.0f);
        layoutParamsTitle.setFlexBasisPercent(0.5f);
        mSpaceEvenLayout.addView(tvTitle, layoutParamsTitle);


        TextView tvMenu = new TextView(this);
        tvMenu.setText("auto");
        tvMenu.setTextSize(16);
        tvMenu.setGravity(Gravity.CENTER);
        tvMenu.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsMenu = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsMenu.setFlexGrow(1.0f);
        layoutParamsMenu.setFlexShrink(1.0f);
        layoutParamsMenu.setFlexBasisPercent(0.0f);
        mSpaceEvenLayout.addView(tvMenu, layoutParamsMenu);


        TextView tvCamera = new TextView(this);
        tvCamera.setText("1/5");
        tvCamera.setTextSize(16);
        tvCamera.setGravity(Gravity.CENTER);
        tvCamera.setBackgroundColor(Color.GRAY);
        FlexboxLayout.LayoutParams layoutParamsCamera = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsCamera.setFlexGrow(1.0f);
        layoutParamsCamera.setFlexShrink(1.0f);
        layoutParamsMenu.setFlexBasisPercent(0.2f);
        mSpaceEvenLayout.addView(tvCamera, layoutParamsCamera);

        // TODO: 2018/11/13 注意 设置填充父布局 MATCH_PARENT setFlexBaispercent应该只有在父布局是MATCH_PARENT和固定值的时候才会起作用
        // LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  300);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(800, 300);
        mSpaceEvenLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(mSpaceEvenLayout, llParams);
    }


    //8. 圣杯布局
    private void createHollyCupLayout() {
        FlexboxLayout mHollyCupLayout = new FlexboxLayout(this);
        mHollyCupLayout.setFlexDirection(FlexDirection.COLUMN);

        // 头部
        TextView tvHeader = new TextView(this);
        tvHeader.setText("头部");
        tvHeader.setGravity(Gravity.CENTER);
        tvHeader.setTextSize(16);
        tvHeader.setBackgroundColor(Color.GREEN);
        FlexboxLayout.LayoutParams layoutParamsBack = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsBack.setFlexGrow(0.0f);
        layoutParamsBack.setFlexShrink(0.0f);
        layoutParamsBack.setFlexBasisPercent(0.2f);
        mHollyCupLayout.addView(tvHeader, layoutParamsBack);

        // body
        // flex  flex-grow 0 flex-shrink 0 flex-bais 0.5
        FlexboxLayout hollyCupBodyLayout = new FlexboxLayout(this);
        hollyCupBodyLayout.setFlexDirection(FlexDirection.ROW);
        FlexboxLayout.LayoutParams bodyLayoutParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        bodyLayoutParams.setFlexGrow(1.0f);
        bodyLayoutParams.setFlexShrink(1.0f);
        bodyLayoutParams.setFlexBasisPercent(0.0f);

        //左
        TextView tvLeft = new TextView(this);
        tvLeft.setText("左");
        tvLeft.setGravity(Gravity.CENTER);
        tvLeft.setTextSize(16);
        tvLeft.setBackgroundColor(Color.BLUE);
        FlexboxLayout.LayoutParams layoutParamsLeft = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsLeft.setFlexGrow(0.0f);
        layoutParamsLeft.setFlexShrink(0.0f);
        layoutParamsLeft.setFlexBasisPercent(0.2f);
        hollyCupBodyLayout.addView(tvLeft, layoutParamsLeft);

        //中
        TextView tvCenter = new TextView(this);
        tvCenter.setText("中");
        tvCenter.setTextSize(16);
        tvCenter.setGravity(Gravity.CENTER);
        tvCenter.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams layoutParamsCenter = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsCenter.setFlexGrow(1.0f);
        layoutParamsCenter.setFlexShrink(1.0f);
        layoutParamsCenter.setFlexBasisPercent(0.0f);
        hollyCupBodyLayout.addView(tvCenter, layoutParamsCenter);

        //右
        TextView tvRight = new TextView(this);
        tvRight.setText("右");
        tvRight.setTextSize(16);
        tvRight.setGravity(Gravity.CENTER);
        tvRight.setBackgroundColor(Color.GRAY);
        FlexboxLayout.LayoutParams layoutParamsRight = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsRight.setFlexGrow(0.0f);
        layoutParamsRight.setFlexShrink(0.0f);
        layoutParamsRight.setFlexBasisPercent(0.2f);
        hollyCupBodyLayout.addView(tvRight, layoutParamsRight);

        mHollyCupLayout.addView(hollyCupBodyLayout, bodyLayoutParams);

        //底部
        TextView tvFooter = new TextView(this);
        tvFooter.setText("底部");
        tvFooter.setGravity(Gravity.CENTER);
        tvFooter.setTextSize(16);
        tvFooter.setBackgroundColor(Color.GREEN);
        FlexboxLayout.LayoutParams layoutParamsFooter = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 160);
        layoutParamsFooter.setFlexGrow(0.0f);
        layoutParamsFooter.setFlexShrink(0.0f);
        layoutParamsFooter.setFlexBasisPercent(0.2f);
        mHollyCupLayout.addView(tvFooter, layoutParamsFooter);


        // TODO: 2018/11/13 注意 设置填充父布局 MATCH_PARENT setFlexBaispercent应该只有在父布局是MATCH_PARENT和固定值的时候才会起作用
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
        //LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(800,  300);
        mHollyCupLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(mHollyCupLayout, llParams);
    }

    //9 输入框布局 左右都有图片
    private void createleftRightInputLayout() {
        FlexboxLayout mInputFlexboxLayout = new FlexboxLayout(this);
        // 从左向右排列
        mInputFlexboxLayout.setFlexDirection(FlexDirection.ROW);

        ImageView imgSearch = new ImageView(this);
        imgSearch.setBackground(getResources().getDrawable(R.drawable.icon_search));
        FlexboxLayout.LayoutParams layoutParamsBack = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParamsBack.setFlexGrow(1.0f);
//        layoutParamsBack.setFlexShrink(1.0f);
//        layoutParamsBack.setFlexBasisPercent(0.0f);
        mInputFlexboxLayout.addView(imgSearch, layoutParamsBack);

        EditText etInput = new EditText(this);
        FlexboxLayout.LayoutParams layoutParamsInput = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsInput.setFlexGrow(1.0f);
        layoutParamsInput.setFlexShrink(1.0f);
        layoutParamsInput.setFlexBasisPercent(0.0f);
        mInputFlexboxLayout.addView(etInput, layoutParamsInput);


        ImageView imgSearchHint = new ImageView(this);
        imgSearchHint.setBackground(getResources().getDrawable(R.drawable.icon_search_hint));
        FlexboxLayout.LayoutParams layoutParamsHint = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParamsBack.setFlexGrow(1.0f);
//        layoutParamsBack.setFlexShrink(1.0f);
//        layoutParamsBack.setFlexBasisPercent(0.0f);
        mInputFlexboxLayout.addView(imgSearchHint, layoutParamsHint);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(800, 300);
        mInputFlexboxLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(mInputFlexboxLayout, llParams);
    }

    //10 输入框布局 左有图片
    private void createleftInputLayout() {
        FlexboxLayout mInputFlexboxLayout = new FlexboxLayout(this);
        // 从左向右排列
        mInputFlexboxLayout.setFlexDirection(FlexDirection.ROW);

        ImageView imgSearch = new ImageView(this);
        imgSearch.setBackground(getResources().getDrawable(R.drawable.icon_search));
        FlexboxLayout.LayoutParams layoutParamsBack = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mInputFlexboxLayout.addView(imgSearch, layoutParamsBack);

        EditText etInput = new EditText(this);
        FlexboxLayout.LayoutParams layoutParamsInput = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsInput.setFlexGrow(1.0f);
        layoutParamsInput.setFlexShrink(1.0f);
        layoutParamsInput.setFlexBasisPercent(0.0f);
        mInputFlexboxLayout.addView(etInput, layoutParamsInput);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(800, 300);
        mInputFlexboxLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(mInputFlexboxLayout, llParams);
    }

    //11 输入框布局 右有图片
    private void createRightInputLayout() {
        FlexboxLayout mInputFlexBoxLayout = new FlexboxLayout(this);
        // 从左向右排列
        mInputFlexBoxLayout.setFlexDirection(FlexDirection.ROW);

        EditText etInput = new EditText(this);
        FlexboxLayout.LayoutParams layoutParamsInput = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsInput.setFlexGrow(1.0f);
        layoutParamsInput.setFlexShrink(1.0f);
        layoutParamsInput.setFlexBasisPercent(0.0f);
        mInputFlexBoxLayout.addView(etInput, layoutParamsInput);

        ImageView imgSearchHint = new ImageView(this);
        imgSearchHint.setBackground(getResources().getDrawable(R.drawable.icon_search_hint));
        FlexboxLayout.LayoutParams layoutParamsHint = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mInputFlexBoxLayout.addView(imgSearchHint, layoutParamsHint);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(800, 300);
        mInputFlexBoxLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(mInputFlexBoxLayout, llParams);
    }

    //12.  底部footer固定在底部，但是不是fixed定位
    private void createBottomFixedLayout() {
        FlexboxLayout mBottomFooterFixedFlexBoxLayout = new FlexboxLayout(this);
        // 从上到下
        mBottomFooterFixedFlexBoxLayout.setFlexDirection(FlexDirection.COLUMN);

        FlexboxLayout mainLayout = new FlexboxLayout(this);
        mainLayout.setFlexDirection(FlexDirection.ROW);
        mainLayout.setBackgroundColor(Color.RED);
        FlexboxLayout.LayoutParams mainLayoutParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mainLayoutParams.setFlexGrow(1.0f);
        mainLayoutParams.setFlexShrink(1.0f);
        mainLayoutParams.setFlexBasisPercent(0.0f);
        mBottomFooterFixedFlexBoxLayout.addView(mainLayout, mainLayoutParams);


        TextView tvFooter = new TextView(this);
        tvFooter.setTextSize(16);
        tvFooter.setText("这是底部");
        tvFooter.setGravity(Gravity.CENTER);
        FlexboxLayout.LayoutParams layoutParamsFooter = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120);
        mBottomFooterFixedFlexBoxLayout.addView(tvFooter, layoutParamsFooter);

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(800, 400);
        mBottomFooterFixedFlexBoxLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(mBottomFooterFixedFlexBoxLayout, llParams);
    }

    //13. 流式布局
    private void createFiveFlowLayout() {
        FlexboxLayout flowFlexBoxLayout = new FlexboxLayout(this);
        // 从上到下
        flowFlexBoxLayout.setFlexDirection(FlexDirection.ROW);
        flowFlexBoxLayout.setAlignContent(AlignContent.FLEX_START);
        flowFlexBoxLayout.setFlexWrap(FlexWrap.WRAP);

        for (int index = 0; index < 5; index++) {
            // item0
            TextView tvItem = new TextView(this);
            tvItem.setTextSize(16);
            tvItem.setText(String.valueOf(index));
            tvItem.setGravity(Gravity.CENTER);
            tvItem.setBackgroundColor(Color.RED);
            FlexboxLayout.LayoutParams layoutParamsItem = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80);
            layoutParamsItem.setFlexGrow(0.0f);
            layoutParamsItem.setFlexShrink(0.0f);
            layoutParamsItem.setFlexBasisPercent(0.333f);
            flowFlexBoxLayout.addView(tvItem, layoutParamsItem);
        }

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(800, ViewGroup.LayoutParams.WRAP_CONTENT);
        flowFlexBoxLayout.setBackgroundColor(Color.GREEN);
        llParams.topMargin = 20;
        mRootView.addView(flowFlexBoxLayout, llParams);

    }

    //14. 流式布局
    private void createSevenFlowLayout() {
        FlexboxLayout flowFlexBoxLayout = new FlexboxLayout(this);
        // 从上到下
        flowFlexBoxLayout.setFlexDirection(FlexDirection.ROW);
        flowFlexBoxLayout.setAlignContent(AlignContent.FLEX_START);
        flowFlexBoxLayout.setFlexWrap(FlexWrap.WRAP);

//        flowFlexBoxLayout.setDividerDrawableVertical(getResources().getDrawable(R.drawable.item_divider));
//        flowFlexBoxLayout.setShowDividerVertical(FlexboxLayout.SHOW_DIVIDER_BEGINNING|FlexboxLayout.SHOW_DIVIDER_END|FlexboxLayout.SHOW_DIVIDER_MIDDLE);

        flowFlexBoxLayout.setDividerDrawable(getResources().getDrawable(R.drawable.item_divider));
        flowFlexBoxLayout.setShowDivider(FlexboxLayout.SHOW_DIVIDER_BEGINNING | FlexboxLayout.SHOW_DIVIDER_MIDDLE | FlexboxLayout.SHOW_DIVIDER_END);

        for (int index = 0; index < 7; index++) {
            // item0
            TextView tvItem = new TextView(this);
            tvItem.setTextSize(16);
            tvItem.setText(String.valueOf(index));
            tvItem.setBackgroundColor(Color.RED);
            tvItem.setGravity(Gravity.CENTER);
            FlexboxLayout.LayoutParams layoutParamsItem = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80);
            layoutParamsItem.setFlexGrow(0.0f);
            layoutParamsItem.setFlexShrink(0.0f);
            layoutParamsItem.setFlexBasisPercent(0.333f);
            flowFlexBoxLayout.addView(tvItem, layoutParamsItem);
        }

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llParams.topMargin = 20;
        mRootView.addView(flowFlexBoxLayout, llParams);
    }

    private void createComplexFlexBoxLayout() {
        RelativeLayout relativeLayout = new RelativeLayout(this);

        //添加按钮
        ImageView imagAdd = new ImageView(this);
        imagAdd.setBackgroundColor(Color.GRAY);
        imagAdd.setImageResource(R.drawable.ic_launcher);
        RelativeLayout.LayoutParams imageLayoutParamsItem = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageLayoutParamsItem.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        imageLayoutParamsItem.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        ViewCompat.setZ(imagAdd, 99.0f);
        relativeLayout.addView(imagAdd, imageLayoutParamsItem);

        FlexboxLayout flexBoxLayout = new FlexboxLayout(this);
        // 从左向右排列
        flexBoxLayout.setFlexDirection(FlexDirection.COLUMN);
        flexBoxLayout.setBackgroundColor(Color.YELLOW);
        TextView tvContent = new TextView(this);
        tvContent.setText("Hello World!");
        FlexboxLayout.LayoutParams layoutParamsItem = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80);
        flexBoxLayout.addView(tvContent, layoutParamsItem);

        RelativeLayout innerRelativeLayout = new RelativeLayout(this);
        innerRelativeLayout.setBackgroundColor(Color.GRAY);
        FlexboxLayout.LayoutParams innerLayoutParamsItem = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80);
        flexBoxLayout.addView(innerRelativeLayout, innerLayoutParamsItem);



//        RelativeLayout.LayoutParams flexParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
//                RelativeLayout.LayoutParams.MATCH_PARENT);
//        flexParams.topMargin = 100;

        FlexboxLayout.LayoutParams flexParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        flexParams.topMargin = 100;

        relativeLayout.addView(flexBoxLayout, flexParams);



        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800);
        mRootView.addView(relativeLayout, llParams);
    }

}
