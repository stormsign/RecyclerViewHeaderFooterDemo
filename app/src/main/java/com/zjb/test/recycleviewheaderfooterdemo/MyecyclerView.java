package com.zjb.test.recycleviewheaderfooterdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by khb on 2017/2/20.
 */
public class MyecyclerView extends RecyclerView {

    public boolean scrolldown = false;

    public MyecyclerView(Context context) {
        super(context);
    }

    public MyecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float startY = 0;
        float endY = 0;
        float diffY = 0;
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = e.getY();
                break;
            case MotionEvent.ACTION_SCROLL:
                endY = e.getY();
                break;
            case MotionEvent.ACTION_UP:
                diffY = endY - startY;
                break;
        }
        if (diffY>0){ //scrolldown
            scrolldown = true;
        }else { //scrollup
            scrolldown = false;
        }
        return super.onTouchEvent(e);
    }
}
