package com.zjb.test.recycleviewheaderfooterdemo.model;

import com.zjb.test.recycleviewheaderfooterdemo.TypeFactory;
import com.zjb.test.recycleviewheaderfooterdemo.Visitor;

/**
 * Created by khb on 2017/3/7.
 */
public class TypeThree implements Visitor {

    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
