package com.zjb.test.recycleviewheaderfooterdemo;

import android.view.View;

import com.zjb.test.recycleviewheaderfooterdemo.model.TypeOne;
import com.zjb.test.recycleviewheaderfooterdemo.model.TypeThree;
import com.zjb.test.recycleviewheaderfooterdemo.model.TypeTwo;

/**
 * Created by khb on 2017/3/7.
 */
public interface TypeFactory {
    int type(TypeOne typeOne);
    int type(TypeTwo typeTwo);
    int type(TypeThree typeThree);

    BaseViewHolder createViewHolder(int type, View itemView);
}
