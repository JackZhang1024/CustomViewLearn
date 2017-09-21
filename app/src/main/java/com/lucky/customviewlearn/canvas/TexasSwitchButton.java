package com.lucky.customviewlearn.canvas;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2017/9/20.
 */

public class TexasSwitchButton extends AppCompatImageView implements View.OnClickListener{
    private boolean mChecked;

    public TexasSwitchButton(Context context) {
        this(context, null);
    }

    public TexasSwitchButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        if (!mChecked){
            setImageResource(R.drawable.ic_switch_close);
        }else{
            setImageResource(R.drawable.ic_switch_open);
        }
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mChecked){
            setImageResource(R.drawable.ic_switch_close);
            mChecked = false;
        }else{
            setImageResource(R.drawable.ic_switch_open);
            mChecked = true;
        }
    }

    public boolean isSwitchButtonChecked(){
        return mChecked;
    }
}
