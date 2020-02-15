package com.lucky.customviewlearn.view.nestedviewpager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lucky.customviewlearn.R;
import com.lucky.customviewlearn.view.SimpleItemDecoration;
import com.lucky.customviewlearn.view.nestedviewpager.NestedAdapter;

import java.util.ArrayList;
import java.util.List;

public class FashionFragment extends Fragment {

    private RecyclerView mRvList;
    private NestedAdapter mNestedAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fashion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRvList = view.findViewById(R.id.recyclerView);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvList.addItemDecoration(new SimpleItemDecoration(getContext()));
        mNestedAdapter = new NestedAdapter(getDataList());
        mRvList.setAdapter(mNestedAdapter);
    }

    private List<String> getDataList(){
        List<String> dataList = new ArrayList<>();
        for (int index=0; index< 100; index++){
            dataList.add(String.format("时尚芭莎新闻%s", index));
        }
        return dataList;
    }
}
