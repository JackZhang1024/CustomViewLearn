package com.lucky.customviewlearn.materialdesign;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lucky.customviewlearn.R;

/**
 * Created by zfz on 2017/7/14.
 */

public class TabContentLayout extends LinearLayout {

    private TabContentLayout(Context context, String contentText) {
        super(context);
        LinearLayout rootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.tab_content, null);
        TextView textView = (TextView) rootView.findViewById(R.id.tab_content);
        textView.setText(contentText);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(rootView, layoutParams);
    }

    static class Builder {

        private Context context;
        private String contentText;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTabContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        public TabContentLayout build() {
            return new TabContentLayout(context, contentText);
        }

    }

}
