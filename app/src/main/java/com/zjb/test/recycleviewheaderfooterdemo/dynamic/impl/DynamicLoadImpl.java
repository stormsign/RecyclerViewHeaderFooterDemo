package com.zjb.test.recycleviewheaderfooterdemo.dynamic.impl;

import android.content.Context;
import android.widget.Toast;

import com.zjb.test.recycleviewheaderfooterdemo.dynamic.DynamicLoad;

/**
 * Created by khb on 2017/3/10.
 */
public class DynamicLoadImpl implements DynamicLoad {
    @Override
    public void dynamicLoad(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }
}
