package com.zjb.test.recycleviewheaderfooterdemo.holder;

import android.view.View;
import android.widget.ImageView;

import com.zjb.test.recycleviewheaderfooterdemo.BaseViewHolder;
import com.zjb.test.recycleviewheaderfooterdemo.R;
import com.zjb.test.recycleviewheaderfooterdemo.model.TypeTwo;

/**
 * Created by khb on 2017/3/7.
 */
public class TypeTwoHolder extends BaseViewHolder<TypeTwo> {
    public TypeTwoHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(TypeTwo model, int position) {
        ((ImageView)getView(R.id.img)).setImageResource(model.getImgRes());
    }
}
