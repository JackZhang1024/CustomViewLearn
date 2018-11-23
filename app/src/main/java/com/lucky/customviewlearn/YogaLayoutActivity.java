package com.lucky.customviewlearn;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.soloader.SoLoader;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaPositionType;
//https://cloud.tencent.com/developer/article/1006148
//https://github.com/facebook/yoga/tree/master/android
//https://github.com/nickma1986/YogaLayoutDemo
//https://www.jianshu.com/p/d4289b16a133

public class YogaLayoutActivity extends AppCompatActivity {

    private static final String TAG = "YogaLayoutActivity";
    RelativeLayout layout;
    //LinearLayout layout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initYoga2();
        createLogin();
        //setContentView(R.layout.activity_yoga_love);
        setContentView(layout);
        //layout = (RelativeLayout) findViewById(R.id.ll_root);
        //layout = (LinearLayout) findViewById(R.id.ll_root);
        //create();
        //test();
        //initYoga();
        //initYoga2();
    }

    private void create() {
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

        layout.getLayoutParams().width = width;
        layout.getLayoutParams().height = height;
        layout.setBackgroundColor(Color.RED);
        layout.requestLayout();

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
        root.calculateLayout();

        // 4. 添加子View到根布局
        TextView imageViewMusic = new TextView(this);
        imageViewMusic.setX(textMusic.getLayoutX());
        imageViewMusic.setY(textMusic.getLayoutY());
        imageViewMusic.setText("music");
        imageViewMusic.setBackgroundColor(Color.YELLOW);
        layout.addView(imageViewMusic);

        TextView imageViewMovie = new TextView(this);
        imageViewMovie.setX(imageMovie.getLayoutX());
        imageViewMovie.setY(imageMovie.getLayoutY());
        imageViewMovie.setText("movie");
        imageViewMovie.setBackgroundColor(Color.YELLOW);
        layout.addView(imageViewMovie);

        Log.e(TAG, "create: music x "+ textMusic.getLayoutX()+" y "+ textMusic.getLayoutY());
        Log.e(TAG, "create: movie x "+imageMovie.getLayoutX()+" y "+imageMovie.getLayoutY());

        //VirtualYogaLayout yogaLayout = new VirtualYogaLayout(this);
        //yogaLayout.addView();
    }

    private void test(){
//        YogaLayout innerLayout = new YogaLayout(this);
//        innerLayout.setLayoutParams(new YogaLayout.LayoutParams(-1,-1));
//        innerLayout.setBackgroundColor(Color.RED);

//        YogaNode scrollViewNode = new YogaNode();
//        ScrollView scrollView = new ScrollView(getActivity());
//        scrollView.setBackgroundColor(Color.GREEN);
//
//        scrollViewNode.setHeight(250);
//        scrollViewNode.setWidth(250);
//// when you set maxWidth instead of setWidth its working.
//
//        YogaLayout inner1Layout = new YogaLayout(getActivity());
//        YogaNode inner1LayoutNode = inner1Layout.getYogaNode();
//        inner1Layout.setBackgroundColor(Color.BLUE);
//        inner1LayoutNode.setWidth(100);
//        inner1LayoutNode.setHeight(1000);
//        scrollView.addView(inner1Layout);
//        innerLayout.addView(scrollView, scrollViewNode);
//        innerLayoutNode.calculateLayout(innerLayoutNode.getWidth().value, innerLayoutNode.getHeight().value);

    }

    private void initYoga() {
        SoLoader.init(this, false);
        //SoLoader.init(MainActivity.this, false);
        //LinearLayout layout = (LinearLayout) findViewById(R.id.ll_root);

        YogaNode root = new YogaNode();
        root.setWidth(getWindowManager().getDefaultDisplay().getWidth() / 2);
        root.setHeight(getWindowManager().getDefaultDisplay().getWidth() / 2);
        root.setJustifyContent(YogaJustify.SPACE_BETWEEN);

        layout.getLayoutParams().width = getWindowManager().getDefaultDisplay().getWidth() / 2;
        layout.getLayoutParams().height = getWindowManager().getDefaultDisplay().getWidth() / 2;
        layout.requestLayout();

        YogaNode row1 = new YogaNode();
        row1.setJustifyContent(YogaJustify.SPACE_BETWEEN);
        row1.setFlexDirection(YogaFlexDirection.ROW);
        root.addChildAt(row1, 0);

        YogaNode image1 = new YogaNode();
        image1.setHeight(60 * getResources().getDisplayMetrics().density);
        image1.setWidth(60 * getResources().getDisplayMetrics().density);
        image1.setAlignSelf(YogaAlign.CENTER);
        root.addChildAt(image1, 1);

        YogaNode row2 = new YogaNode();
        row2.setJustifyContent(YogaJustify.SPACE_BETWEEN);
        row2.setFlexDirection(YogaFlexDirection.ROW);
        root.addChildAt(row2, 2);

        YogaNode image2 = new YogaNode();
        image2.setHeight(60 * getResources().getDisplayMetrics().density);
        image2.setWidth(60 * getResources().getDisplayMetrics().density);
        image2.setMargin(YogaEdge.ALL, 5 * getResources().getDisplayMetrics().density);
        row1.addChildAt(image2, 0);

        YogaNode image3 = new YogaNode();
        image3.setHeight(60 * getResources().getDisplayMetrics().density);
        image3.setWidth(60 * getResources().getDisplayMetrics().density);
        image3.setMargin(YogaEdge.ALL, 5 * getResources().getDisplayMetrics().density);
        row1.addChildAt(image3, 1);

        YogaNode image4 = new YogaNode();
        image4.setHeight(60 * getResources().getDisplayMetrics().density);
        image4.setWidth(60 * getResources().getDisplayMetrics().density);
        image4.setMargin(YogaEdge.ALL, 5 * getResources().getDisplayMetrics().density);
        row2.addChildAt(image4, 0);

        YogaNode image5 = new YogaNode();
        image5.setHeight(60 * getResources().getDisplayMetrics().density);
        image5.setWidth(60 * getResources().getDisplayMetrics().density);
        image5.setMargin(YogaEdge.ALL, 5 * getResources().getDisplayMetrics().density);
        row2.addChildAt(image5, 1);

        root.calculateLayout();

        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.little_point);
        imageView1.setX(image1.getLayoutX());
        imageView1.setY(image1.getLayoutY());
        layout.addView(imageView1);

        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.little_point);
        imageView2.setX(image2.getLayoutX());
        imageView2.setY(image2.getLayoutY());
        layout.addView(imageView2);

        ImageView imageView3 = new ImageView(this);
        imageView3.setImageResource(R.drawable.little_point);
        imageView3.setX(image3.getLayoutX());
        imageView3.setY(image3.getLayoutY());
        layout.addView(imageView3);

        ImageView imageView4 = new ImageView(this);
        imageView4.setImageResource(R.drawable.little_point);
        imageView4.setX(image4.getLayoutX() + row2.getLayoutX());
        imageView4.setY(image4.getLayoutY() + row2.getLayoutY());
        layout.addView(imageView4);

        ImageView imageView5 = new ImageView(this);
        imageView5.setImageResource(R.drawable.little_point);
        imageView5.setX(image5.getLayoutX() + row2.getLayoutX());
        imageView5.setY(image5.getLayoutY() + row2.getLayoutY());
        layout.addView(imageView5);


        Log.d("image", image1.getLayoutX() + " " + image1.getLayoutY());
        Log.d("image", image2.getLayoutX() + " " + image2.getLayoutY());
        Log.d("image", image3.getLayoutX() + " " + image3.getLayoutY());
        Log.d("image", image4.getLayoutX() + " " + image4.getLayoutY());
        Log.d("image", image5.getLayoutX() + " " + image5.getLayoutY());
        Log.d("row1", row1.getLayoutWidth() + "");
    }

    // 注意最底层的根布局必须使用RelativeLayout 不然会有问题
    private void initYoga2() {
        SoLoader.init(this, false);
        //layout = (RelativeLayout) findViewById(R.id.ll_root);
        layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
        layout.setBackgroundColor(Color.YELLOW);
        layout.setLayoutParams(params);

        YogaNode root = new YogaNode();
        // root的width和layout的width宽度一样才能表现出预期的结果
//        root.setWidth(getWindowManager().getDefaultDisplay().getWidth() );
        root.setWidth(-2);
        root.setHeight(-2);
        root.setFlexDirection(YogaFlexDirection.ROW);

        // MATCH_PARENT支持 WRAP_CONTENT不支持
        //layout.getLayoutParams().width = getWindowManager().getDefaultDisplay().getWidth();
        //layout.getLayoutParams().width = -1;
        //layout.getLayoutParams().height = getWindowManager().getDefaultDisplay().getWidth() / 2;
        //layout.getLayoutParams().height = getWindowManager().getDefaultDisplay().getWidth() / 2;
        //layout.requestLayout();
        //root.setJustifyContent(YogaJustify.CENTER);

        YogaNode image1 = new YogaNode();
        image1.setHeight(60 * getResources().getDisplayMetrics().density);
        image1.setWidth(60 * getResources().getDisplayMetrics().density);
        image1.setMargin(YogaEdge.ALL, 5 * getResources().getDisplayMetrics().density);
        root.addChildAt(image1, 0);

        YogaNode image2 = new YogaNode();
        image2.setHeight(60 * getResources().getDisplayMetrics().density);
        image2.setWidth(60 * getResources().getDisplayMetrics().density);
        image2.setMargin(YogaEdge.ALL, 5 * getResources().getDisplayMetrics().density);
        //image2.setAlignSelf(YogaAlign.FLEX_END);
        image2.setPosition(YogaEdge.START, 20);
        image2.setPosition(YogaEdge.TOP, 20);
        image2.setPositionType(YogaPositionType.ABSOLUTE);
        root.addChildAt(image2, 1);

        root.calculateLayout();

        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.little_point);
        imageView2.setX(image2.getLayoutX());
        imageView2.setY(image2.getLayoutY());
        //imageView2.setZ(99.0f);
//        imageView2.setTranslationZ(99.0f);
        //ViewCompat.setZ(imageView2, 99.0f);
        layout.addView(imageView2);

        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.little_point);
        imageView1.setX(image1.getLayoutX());
        imageView1.setY(image1.getLayoutY());
        imageView1.setBackgroundColor(Color.RED);
        layout.addView(imageView1);

        Log.e(TAG, "initYoga2: rootWidth "+root.getWidth()+"  "+root.getHeight()+"  "+root.getLayoutWidth()+" "+root.getLayoutHeight());
        Log.e(TAG, "initYoga2: rootView "+layout.getWidth()+" "+layout.getHeight());

        layout.getLayoutParams().width = (int)root.getLayoutWidth();
        layout.getLayoutParams().height = (int)root.getLayoutHeight();
        layout.requestLayout();
        Log.e(TAG, "initYoga2: rootView "+layout.getWidth()+" "+layout.getHeight());
//        image.setPositionType(YogaPositionType.ABSOLUTE);
//        image.setPosition(YogaEdge.END, 20);
//        image.setPosition(YogaEdge.TOP, 20);


    }

    private void createLogin(){
        SoLoader.init(this, false);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();

        layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -2);
        layout.setBackgroundColor(Color.YELLOW);
        layout.setLayoutParams(params);

        YogaNode root = new YogaNode();
        root.setWidth(width);
        root.setHeight(height);
        root.setFlexDirection(YogaFlexDirection.COLUMN);
        root.setAlignItems(YogaAlign.CENTER);
        root.setJustifyContent(YogaJustify.SPACE_BETWEEN);

        //登录节点
        YogaNode loginNode = new YogaNode();
        loginNode.setWidth(-2);
        loginNode.setHeight(-2);
        root.addChildAt(loginNode, 0);

        //用户名节点
        YogaNode userNameNode = new YogaNode();
        userNameNode.setWidth(-2);
        userNameNode.setHeight(-2);
        root.addChildAt(userNameNode, 1);

        //用户密码节点
        YogaNode userPassWdNode = new YogaNode();
        userPassWdNode.setWidth(-2);
        userPassWdNode.setHeight(-2);
        root.addChildAt(userPassWdNode, 2);

        //登录按钮
        YogaNode userLoginButton = new YogaNode();
        userLoginButton.setWidth(-2);
        userLoginButton.setHeight(-2);
        root.addChildAt(userLoginButton, 3);

        //注册按钮
        YogaNode userRegisterButton = new YogaNode();
        userRegisterButton.setWidth(-2);
        userRegisterButton.setHeight(-2);
        root.addChildAt(userRegisterButton, 4);

        root.calculateLayout();

        //添加childView
        TextView tvLogin = new TextView(this);
        tvLogin.setText("登录页面");
        tvLogin.setTextSize(20);
        tvLogin.setX(loginNode.getLayoutX());
        tvLogin.setY(loginNode.getLayoutY());
        layout.addView(tvLogin);

        TextView tvUserName = new TextView(this);
        tvUserName.setText("输入用户名");
        tvUserName.setTextSize(20);
        tvUserName.setX(userNameNode.getLayoutX());
        tvUserName.setY(userNameNode.getLayoutY());
        layout.addView(tvUserName);

        TextView tvUserPasswd = new TextView(this);
        tvUserPasswd.setText("输入用户密码");
        tvUserPasswd.setTextSize(20);
        tvUserPasswd.setX(userPassWdNode.getLayoutX());
        tvUserPasswd.setY(userPassWdNode.getLayoutY());
        layout.addView(tvUserPasswd);

        TextView tvLoginButton = new TextView(this);
        tvLoginButton.setText("登录");
        tvLoginButton.setTextSize(20);
        tvLoginButton.setX(userLoginButton.getLayoutX());
        tvLoginButton.setY(userLoginButton.getLayoutY());
        layout.addView(tvLoginButton);

        TextView tvRegisterButton = new TextView(this);
        tvRegisterButton.setText("没账号去注册");
        tvRegisterButton.setTextSize(20);
        tvRegisterButton.setX(userRegisterButton.getLayoutX());
        tvRegisterButton.setY(userRegisterButton.getLayoutY());
        layout.addView(tvRegisterButton);

        //layout.getLayoutParams().width = (int)root.getLayoutWidth();
        //layout.getLayoutParams().height = (int)root.getLayoutHeight();
        Log.e(TAG, "createLogin: width "+root.getLayoutWidth()+" height "+root.getLayoutHeight());
        layout.requestLayout();

    }

}
