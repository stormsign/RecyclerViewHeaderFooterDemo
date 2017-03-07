package com.zjb.test.recycleviewheaderfooterdemo.holder;

import android.view.View;
import android.widget.TextView;

import com.zjb.test.recycleviewheaderfooterdemo.BaseViewHolder;
import com.zjb.test.recycleviewheaderfooterdemo.R;
import com.zjb.test.recycleviewheaderfooterdemo.model.TypeThree;

/**
 * Created by khb on 2017/3/7.
 */
public class TypeThreeHolder extends BaseViewHolder<TypeThree> {
    public TypeThreeHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(TypeThree model, int position) {
        ((TextView)getView(R.id.text)).setText(model.getText());
    }
}
