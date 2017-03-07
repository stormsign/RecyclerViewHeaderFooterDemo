package com.zjb.test.recycleviewheaderfooterdemo.model;

import com.zjb.test.recycleviewheaderfooterdemo.TypeFactory;
import com.zjb.test.recycleviewheaderfooterdemo.Visitor;

/**
 * Created by khb on 2017/3/7.
 */
public class TypeOne implements Visitor {

    String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
