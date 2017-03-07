package com.zjb.test.recycleviewheaderfooterdemo.model;

import com.zjb.test.recycleviewheaderfooterdemo.TypeFactory;
import com.zjb.test.recycleviewheaderfooterdemo.Visitor;

/**
 * Created by khb on 2017/3/7.
 */
public class TypeTwo implements Visitor {

    String imgSrc;
    int imgRes;

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public int getImgRes() {
        return imgRes;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
