package com.lucky.customviewlearn.material.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.TextView;

import com.lucky.customviewlearn.R;

public class DrawableFragment extends BaseFragment {

    @Override
    protected View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_drawable, null);

        View imageview = view.findViewById(R.id.im);

        final TextView v = (TextView) view.findViewById(R.id.v);
        final TextView vd = (TextView) view.findViewById(R.id.vd);
        final TextView vl = (TextView) view.findViewById(R.id.vl);
        final TextView m = (TextView) view.findViewById(R.id.m);
        final TextView md = (TextView) view.findViewById(R.id.md);
        final TextView ml = (TextView) view.findViewById(R.id.ml);

        Drawable drawable = getResources().getDrawable(R.drawable.palette);
        imageview.setBackground(drawable);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Palette.generateAsync(bd.getBitmap(), new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {

//				v.setBackgroundColor(palette.getVibrantColor(Color.BLACK));
//				vd.setBackgroundColor(palette.getDarkVibrantColor(Color.BLACK));
//				vl.setBackgroundColor(palette.getLightVibrantColor(Color.BLACK));
//				m.setBackgroundColor(palette.getMutedColor(Color.BLACK));
//				md.setBackgroundColor(palette.getDarkMutedColor(Color.BLACK));
//				ml.setBackgroundColor(palette.getLightMutedColor(Color.BLACK));

                // 通过palette 来获取对应的色调
                Palette.Swatch vibrant = palette.getDarkVibrantSwatch();
                if (getActivity() != null) {
                    getActivity().getActionBar().setBackgroundDrawable(new ColorDrawable(vibrant.getRgb()));
                    getActivity().getWindow().setStatusBarColor(vibrant.getRgb());
                }
            }
        });
        return view;
    }


    @Override
    public String getUrl() {
        return "file:///android_asset/drawable.html";
    }
}
