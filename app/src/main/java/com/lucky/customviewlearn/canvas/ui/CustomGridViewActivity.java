package com.lucky.customviewlearn.canvas.ui;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.canvas.CustomGridView;
import com.lucky.customviewlearn.core.DisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zfz on 2018/1/9.
 */

public class CustomGridViewActivity extends AppCompatActivity {
    @BindView(R.id.custom_gridview)
    CustomGridView customGridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_gridview);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_leaf);
        customGridView.addView(imageView);
        //addAnimations(customGridView);
        addLayoutAnimations(customGridView);
    }

    @OnClick(R.id.btn_add_childView)
    public void onAddChildViewClick(View view) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_leaf);
        customGridView.addView(imageView, 0);
    }


    @OnClick(R.id.btn_delete_childView)
    public void onDeleteChildViewClick(View view) {
        if (customGridView.getChildCount() > 0) {
            customGridView.removeViewAt(0);
        }
    }

    /**
     * LayoutTransition 提供了以下几种过渡类型：
     * APPEARING —— 元素在容器中显现时需要动画显示。
     * CHANGE_APPEARING —— 由于容器中要显现一个新的元素，其它元素的变化需要动画显示。
     * DISAPPEARING —— 元素在容器中消失时需要动画显示。
     * CHANGE_DISAPPEARING —— 由于容器中某个元素要消失，其它元素的变化需要动画显示。
     */
    public void addAnimations(ViewGroup viewGroup) {
        LayoutTransition mLayoutTransition = new LayoutTransition();
        //设置每个动画持续的时间
        mLayoutTransition.setStagger(LayoutTransition.CHANGE_APPEARING, 50);
        mLayoutTransition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 50);
        mLayoutTransition.setStagger(LayoutTransition.APPEARING, 50);
        mLayoutTransition.setStagger(LayoutTransition.DISAPPEARING, 50);

        PropertyValuesHolder appearingScaleX = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1.0f);
        PropertyValuesHolder appearingScaleY = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1.0f);
        PropertyValuesHolder appearingAlpha = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
        ObjectAnimator mAnimatorAppearing = ObjectAnimator.ofPropertyValuesHolder(this, appearingAlpha, appearingScaleX, appearingScaleY);
        //为LayoutTransition设置动画及动画类型
        mLayoutTransition.setAnimator(LayoutTransition.APPEARING, mAnimatorAppearing);

        PropertyValuesHolder disappearingAlpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        PropertyValuesHolder disappearingRotationY = PropertyValuesHolder.ofFloat("rotationY", 0.0f, 90.0f);
        ObjectAnimator mAnimatorDisappearing = ObjectAnimator.ofPropertyValuesHolder(this, disappearingAlpha, disappearingRotationY);
        //为LayoutTransition设置动画及动画类型
        mLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, mAnimatorDisappearing);

        ObjectAnimator mAnimatorChangeDisappearing = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
        //为LayoutTransition设置动画及动画类型
        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, mAnimatorChangeDisappearing);
        ObjectAnimator mAnimatorChangeAppearing = ObjectAnimator.ofFloat(null, "alpha", 1f, 0f);
        //为LayoutTransition设置动画及动画类型
        mLayoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, mAnimatorChangeAppearing);
        //为mImageViewGroup设置mLayoutTransition对象
        viewGroup.setLayoutTransition(mLayoutTransition);
    }

    private void addLayoutAnimations(ViewGroup viewGroup) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(2000);
        LayoutAnimationController animationController = new LayoutAnimationController(alphaAnimation, 0.5f);
        animationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
        viewGroup.setLayoutAnimation(animationController);
    }

}
