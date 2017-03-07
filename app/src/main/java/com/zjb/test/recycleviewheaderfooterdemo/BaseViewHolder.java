package com.zjb.test.recycleviewheaderfooterdemo;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by khb on 2017/3/7.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private SparseArrayCompat<View> views;
    private View mItemView;
    public BaseViewHolder(View itemView) {
        super(itemView);
        views = new SparseArrayCompat<>();
        mItemView = itemView;
    }

    public View getView(int resId){
        View view = views.get(resId);
        if (view == null) {
            view = mItemView.findViewById(resId);
            views.put(resId, view);
        }
        return view;
    }

    public abstract void setUpView(T model, int position);
}
