package com.lucky.customviewlearn.scroller.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucky.customviewlearn.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerViewActivity";
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecycleAdapter(loadData()));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 1. 判断RecyclerView是否到顶部或者底部
                /**
                 * 这个是View的函数
                 * Check if this view can be scrolled vertically in a certain direction.
                 *
                 * @param direction Negative to check scrolling up, positive to check scrolling down.
                 * @return true if this view can be scrolled in the specified direction, false otherwise.
                 */
                //public boolean canScrollVertically(int direction) {

                // -1 表示检测是否还能下拉 true 表示还可以 false 表示不可以
                //  1 表示检测是否还能上拉 true 表示还可以 false 表示不可以
                if (!recyclerView.canScrollVertically(-1)) {
                    Log.e(TAG, "onScrollStateChanged: 我已经到顶了 不能再往下拉了");
                } else if (!recyclerView.canScrollVertically(1)) {
                    Log.e(TAG, "onScrollStateChanged: 我已经到底了 不能再往上拉了");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


    private List<String> loadData() {
        ArrayList<String> listData = new ArrayList<>();
        for (int index = 0; index < 100; index++) {
            listData.add(String.format("数据%s", index));
        }
        return listData;
    }

    class RecycleAdapter extends RecyclerView.Adapter {

        private List<String> mDataList = new ArrayList<>();

        RecycleAdapter(List<String> dataList) {
            this.mDataList = dataList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            RecyclerViewHolder rvHolder = (RecyclerViewHolder) holder;
            rvHolder.setItem(mDataList.get(position));
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;

        RecyclerViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
        }

        public void setItem(String data) {
            tvContent.setText(data);
        }
    }


}
