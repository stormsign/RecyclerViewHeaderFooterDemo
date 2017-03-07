package com.zjb.test.recycleviewheaderfooterdemo.holder;

import android.view.View;
import android.widget.TextView;

import com.zjb.test.recycleviewheaderfooterdemo.BaseViewHolder;
import com.zjb.test.recycleviewheaderfooterdemo.R;
import com.zjb.test.recycleviewheaderfooterdemo.model.TypeOne;

/**
 * Created by khb on 2017/3/7.
 */
public class TypeOneHolder extends BaseViewHolder<TypeOne> {
    public TypeOneHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(TypeOne model, int position) {
        ((TextView)getView(R.id.text)).setText(model.getText());
    }
}
