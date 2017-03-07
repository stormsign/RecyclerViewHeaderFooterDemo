package com.zjb.test.recycleviewheaderfooterdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by khb on 2017/3/7.
 */
public class MultiTypeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private TypeFactory typeFactory;
    private List<Visitor> models;

    public MultiTypeAdapter(List<Visitor> models){
        this.models = models;
        this.typeFactory = new TypeFactoryForRecyclerView();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), viewType, null);
        BaseViewHolder viewHolder = typeFactory.createViewHolder(viewType, view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setUpView(models.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (null == models){
            return  0;
        }
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        return models.get(position).type(typeFactory);
    }
}
