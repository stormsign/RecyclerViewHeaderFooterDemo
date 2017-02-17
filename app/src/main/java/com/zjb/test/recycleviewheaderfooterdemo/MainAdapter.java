package com.zjb.test.recycleviewheaderfooterdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by khb on 2017/2/13.
 */
public class MainAdapter extends RecyclerView.Adapter{
    private List<String> list;
    private Context context;

    public MainAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
