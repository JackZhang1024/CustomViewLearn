package com.lucky.customviewlearn.view.nestedviewpager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucky.customviewlearn.R;

import java.util.List;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NestedRecycleViewHolder> {

    private List<String> dataList = null;


    public NestedAdapter(List<String> dataList) {
         this.dataList = dataList;
    }

    @Override
    public void onBindViewHolder(NestedRecycleViewHolder holder, int position) {
        holder.setItemData(dataList.get(position));
    }

    @Override
    public NestedRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new NestedRecycleViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class NestedRecycleViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvTitle;

        NestedRecycleViewHolder(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void setItemData(String data){
            mTvTitle.setText(data);
        }

    }



}
