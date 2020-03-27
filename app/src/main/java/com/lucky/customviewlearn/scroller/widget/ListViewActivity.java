package com.lucky.customviewlearn.scroller.widget;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lucky.customviewlearn.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private static final String TAG = "ListViewActivity";

    private ListView mListView;
    private LayoutInflater mInflater;
    private ListAdapter mListAdapter;

    @TargetApi(23)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mInflater = LayoutInflater.from(this);
        mListView = findViewById(R.id.listview);
        mListAdapter = new ListAdapter(loadListData());
        mListView.setAdapter(mListAdapter);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView listView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // 1. 判断到最顶部
                // 第一个可见的Item 的位置是0 同时第一个可见的Item的getTop是0
                // firstVisibleItem 就是第一个可见Item的位置
                if (firstVisibleItem == 0) {
                    View child = listView.getChildAt(firstVisibleItem);
                    if (child != null && child.getTop() == 0) {
                        Log.e(TAG, "onScroll: ListView 我到最顶部了");
                    }
                }
                // 2. 判断到最底部
                // 第一个可见的Item的position加上所有可见的Item的条数等于总条数 还有就是最后一条Item的bottom等于ListView的高度
                // 就是到了最底部
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    Log.e(TAG, "onScroll: ListView 走不到着吗");
                    int position  = listView.getLastVisiblePosition();
                    View lastVisibleChild = listView.getChildAt(listView.getChildCount() - 1);
                    Log.e(TAG, "onScroll: lastVisiblePosition "+position+" totalCount-1 "+(listView.getChildCount()-1));
                    Log.e(TAG, "onScroll: viewHeight "+listView.getMeasuredHeight()+" ListViewHeight "+mListView.getHeight()+" ListViewMeasuredHeight "+mListView.getMeasuredHeight());
                    if (lastVisibleChild != null && lastVisibleChild.getBottom() == listView.getMeasuredHeight()) {
                        Log.e(TAG, "onScroll: ListView 我到最底部了");
                    }
                }
            }
        });
        mListView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e(TAG, "onScrollChange: scrollX " + scrollX + " scrollY " + scrollY);
            }
        });
    }


    private List<String> loadListData() {
        List<String> dataList = new ArrayList<>();
        for (int index = 0; index < 100; index++) {
            dataList.add(String.format("数据%s", index));
        }
        return dataList;
    }


    class ListAdapter extends BaseAdapter {

        private List<String> mListData = new ArrayList<>();

        public ListAdapter(List<String> dataList) {
            this.mListData = dataList;
        }

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public String getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = mInflater.inflate(R.layout.layout_list_item, parent, false);
            } else {
                view = convertView;
            }
            TextView tvContent = view.findViewById(R.id.tv_content);
            tvContent.setText(getItem(position));
            return view;
        }
    }


}
