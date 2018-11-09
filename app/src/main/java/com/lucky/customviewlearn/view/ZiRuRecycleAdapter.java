package com.lucky.customviewlearn.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lucky.customviewlearn.R;

import java.util.ArrayList;
import java.util.List;

public class ZiRuRecycleAdapter extends RecyclerView.Adapter<ZiRuRecycleAdapter.ZiRuViewHolder> {
    private static final String TAG = "ZiRuRecycleAdapter";
    public List<String> mDataList = new ArrayList<>();


    public ZiRuRecycleAdapter(List<String> dataList) {
        this.mDataList = dataList;
    }

    @Override
    public ZiRuRecycleAdapter.ZiRuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ZiRuViewItem mItemView = new ZiRuViewItem(parent.getContext());
        mItemView.addItemView(null, 10001);
        Log.e(TAG, "onCreateViewHolder: ");
        return new ZiRuViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(ZiRuRecycleAdapter.ZiRuViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: ");
        holder.setItemData(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: "+mDataList.size());
        return mDataList.size();
    }


    class ZiRuViewHolder extends RecyclerView.ViewHolder {
        private View mItemView;

        public ZiRuViewHolder(View itemView) {
            super(itemView);
            this.mItemView = itemView;
        }

        public void setItemData(String data) {
            if (mItemView instanceof ZiRuViewItem) {
                ZiRuViewItem ziRuViewItem = (ZiRuViewItem) mItemView;
                ziRuViewItem.setItemData(data);
            }
        }
    }


}
