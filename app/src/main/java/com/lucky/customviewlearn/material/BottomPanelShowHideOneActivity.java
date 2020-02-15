package com.lucky.customviewlearn.material;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucky.customviewlearn.R;

import java.util.ArrayList;
import java.util.List;

// 底部面板显示或者隐藏
// 列表向下滚动 底部面板消失
// 列表向上滚定 底部面板显示
public class BottomPanelShowHideOneActivity extends AppCompatActivity {

    RecyclerView mRvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_panel_show_hide_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("多啦A梦");
        mRvList = findViewById(R.id.recyclerView);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setItemAnimator(new DefaultItemAnimator());
        ListAdapter adapter = new ListAdapter(getListData());
        mRvList.setAdapter(adapter);
    }

    private List<String> getListData() {
        List<String> dataList = new ArrayList<>();
        for (int i=0; i< 100; i++){
            dataList.add(String.format("第%s条数据", i));
        }
        return dataList;
    }

    private class ListAdapter extends RecyclerView.Adapter<ListViewHolder> {

        private List<String> dataList;

        private ListAdapter(List<String> list) {
            dataList = list;
        }

        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ListViewHolder holder, int position) {
            holder.setItemData(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }


    private class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        ListViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void setItemData(String data){
            tvTitle.setText(data);
        }

    }



}
