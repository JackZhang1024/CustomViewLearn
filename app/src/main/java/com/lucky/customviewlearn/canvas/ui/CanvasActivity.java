package com.lucky.customviewlearn.canvas.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lucky.customviewlearn.BaseActivity;
import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.canvas.base.SimpleCanvasActivity;
import com.lucky.customviewlearn.canvas.imageprocess.CircleBitmapShaderActivity;
import com.lucky.customviewlearn.canvas.imageprocess.GuaGuaCardActivity;
import com.lucky.customviewlearn.canvas.imageprocess.ImageColorMatrixActivity;
import com.lucky.customviewlearn.canvas.imageprocess.ImageMatrixActivity;
import com.lucky.customviewlearn.canvas.imageprocess.ImageMatrixSimpleActivity;
import com.lucky.customviewlearn.canvas.imageprocess.ImageProcessActivity;
import com.lucky.customviewlearn.canvas.imageprocess.PathEffectViewActivity;
import com.lucky.customviewlearn.canvas.imageprocess.ReflectViewActivity;
import com.lucky.customviewlearn.canvas.layer.CanvasLayerActivity;
import com.lucky.customviewlearn.canvas.saverestore.CanvasSaveRestoreActivity;
import com.lucky.customviewlearn.canvas.specialeffects.PorterDuffXferModeActivity;
import com.lucky.customviewlearn.canvas.surfaceview.SurfaceViewActivity;

/**
 * Created by zfz on 2017/8/3.
 */

public class CanvasActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        findViewById(R.id.btn_simple_canvas).setOnClickListener(this);
        findViewById(R.id.btn_checkmark).setOnClickListener(this);
        findViewById(R.id.btn_pieview).setOnClickListener(this);
        findViewById(R.id.btn_customcircle).setOnClickListener(this);
        findViewById(R.id.btn_taichi).setOnClickListener(this);
        findViewById(R.id.btn_canvas_save_restore).setOnClickListener(this);
        findViewById(R.id.btn_canvas_layer).setOnClickListener(this);
        findViewById(R.id.btn_rule_view).setOnClickListener(this);
        findViewById(R.id.btn_recursive_view).setOnClickListener(this);
        findViewById(R.id.btn_clock_view).setOnClickListener(this);
        findViewById(R.id.btn_canvas_skew).setOnClickListener(this);
        findViewById(R.id.btn_seek_bar).setOnClickListener(this);
        findViewById(R.id.btn_switch_button).setOnClickListener(this);
        findViewById(R.id.btn_xml_paint).setOnClickListener(this);
        findViewById(R.id.btn_custom_gridview).setOnClickListener(this);
        findViewById(R.id.btn_surfaceview).setOnClickListener(this);
        findViewById(R.id.btn_image_process).setOnClickListener(this);
        findViewById(R.id.btn_color_matrix).setOnClickListener(this);
        findViewById(R.id.btn_matrix_transform).setOnClickListener(this);
        findViewById(R.id.btn_matrix_simple_transform).setOnClickListener(this);
        findViewById(R.id.btn_paint_effects).setOnClickListener(this);
        findViewById(R.id.btn_guagua_card).setOnClickListener(this);
        findViewById(R.id.btn_bitmap_shader).setOnClickListener(this);
        findViewById(R.id.btn_reflectview).setOnClickListener(this);
        findViewById(R.id.btn_patheffect).setOnClickListener(this);
        findViewById(R.id.btn_arc_progress).setOnClickListener(this);
        findViewById(R.id.btn_nb_scrollruler).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_checkmark:
                intent.setClass(this, CheckMarkActivity.class);
                break;
            case R.id.btn_pieview:
                intent.setClass(this, PieViewLearnActivity.class);
                break;
            case R.id.btn_customcircle:
                intent.setClass(this, CustomCircleActivity.class);
                break;
            case R.id.btn_taichi:
                intent.setClass(this, TaiChiActivity.class);
                break;
            case R.id.btn_canvas_save_restore:
                intent.setClass(this, CanvasSaveRestoreActivity.class);
                break;
            case R.id.btn_canvas_layer:
                intent.setClass(this, CanvasLayerActivity.class);
                break;
            case R.id.btn_rule_view:
                intent.setClass(this, RuleViewActivity.class);
                break;
            case R.id.btn_recursive_view:
                intent.setClass(this, RecursiveActivity.class);
                break;
            case R.id.btn_clock_view:
                intent.setClass(this, RotateClockViewActivity.class);
                break;
            case R.id.btn_canvas_skew:
                intent.setClass(this, CanvasSkewActivity.class);
                break;
            case R.id.btn_seek_bar:
                intent.setClass(this, SeekBarActivity.class);
                break;
            case R.id.btn_switch_button:
                intent.setClass(this, TexasSwitchButtonActivity.class);
                break;
            case R.id.btn_simple_canvas:
                intent.setClass(this, SimpleCanvasActivity.class);
                break;
            case R.id.btn_xml_paint:
                intent.setClass(this, XmlPaintActivity.class);
                break;
            case R.id.btn_custom_gridview:
                intent.setClass(this, CustomGridViewActivity.class);
                break;
            case R.id.btn_surfaceview:
                intent.setClass(this, SurfaceViewActivity.class);
                break;
            case R.id.btn_image_process:
                intent.setClass(this, ImageProcessActivity.class);
                break;
            case R.id.btn_color_matrix:
                intent.setClass(this, ImageColorMatrixActivity.class);
                break;
            case R.id.btn_matrix_transform:
                intent.setClass(this, ImageMatrixActivity.class);
                break;
            case R.id.btn_matrix_simple_transform:
                intent.setClass(this, ImageMatrixSimpleActivity.class);
                break;
            case R.id.btn_paint_effects:
                intent.setClass(this, PorterDuffXferModeActivity.class);
                break;
            case R.id.btn_guagua_card:
                intent.setClass(this, GuaGuaCardActivity.class);
                break;
            case R.id.btn_bitmap_shader:
                intent.setClass(this, CircleBitmapShaderActivity.class);
                break;
            case R.id.btn_reflectview:
                intent.setClass(this, ReflectViewActivity.class);
                break;
            case R.id.btn_patheffect:
                intent.setClass(this, PathEffectViewActivity.class);
                break;
            case R.id.btn_arc_progress:
                intent.setClass(this, ArcProgressActivity.class);
                break;
            case R.id.btn_nb_scrollruler:
                intent.setClass(this, NBScrollRulerActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
