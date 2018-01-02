package com.lucky.customviewlearn.canvas.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lucky.customviewlearn.BaseActivity;
import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.canvas.PieData;
import com.lucky.customviewlearn.canvas.PieView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zfz on 2017/7/5.
 */

public class PieViewLearnActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pieview);
        drawPieView();
    }


    private void drawPieView() {
        PieView pieView = (PieView) findViewById(R.id.pie_view);
        List<PieData> pieDataList = new ArrayList<>();
        pieDataList.add(new PieData("Apple", 30));
        pieDataList.add(new PieData("Orange", 20));
        pieDataList.add(new PieData("Banana", 25));
        pieDataList.add(new PieData("Peach", 25));
        pieView.setPieDataList(pieDataList);
    }

}
