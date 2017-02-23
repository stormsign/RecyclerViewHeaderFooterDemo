package com.zjb.test.recycleviewheaderfooterdemo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by khb on 2017/2/20.
 */
public class MyRecyclerView extends RecyclerView {

    private HeaderFooterWrapper wrapper;
    private View header;
    private View footer;
    private final int MAX_HEADERHEIGHT = 200;

    private final int PAGESIZE = 10;

    public boolean hasHeader;
    public boolean hasFooter;

    public boolean scrolldown = false;
    private GestureDetector gd;
    private MyScrollListener myScrollListener;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter instanceof HeaderFooterWrapper){
            wrapper = (HeaderFooterWrapper) adapter;
        }
        header = wrapper.getHeaderView();
        footer = wrapper.getFooterView();
        myScrollListener = new MyScrollListener();
        addOnScrollListener(myScrollListener);
        super.setAdapter(adapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float startY = 0;
        float endY = 0;
        float dY = 0 ;
        Log.i("LOG", "e.getAction = " + e.getAction());
//        gd.onTouchEvent(e);
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endY = e.getY();
                dY = endY - startY;
                Log.i("LOG", e.getX() + " : " + e.getY() + " scrollY=" + dY);
                if (header!=null) {
                    ViewGroup.LayoutParams layoutParams = header.getLayoutParams();
                    layoutParams.height = (int) dY;
                    header.setLayoutParams(layoutParams);
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("LOG", "animation");
                break;
        }
        return super.onTouchEvent(e);
    }

    public void removeHeader(View view){

    }

    public void removeFooter(View view){
        int size = wrapper.getRealItemCount();
//        for (int i = size; i<size+PAGESIZE; i++){
//            list.add(i+"");
//        }
        wrapper.removeFooter();
        hasFooter = false;
    }

    class MyScrollListener extends RecyclerView.OnScrollListener{

        private int lastVisibleItem;
        private int firstVisibleItem;
        int scrollDownThreshold = 0;
        boolean footerEnable;
        boolean headerEnable;

//        private static final boolean SCROLL_UP = 1;
//        private static final int SCROLL_DOWN = -1;
//        private static final int SCROLL_DEFAULT = 0;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            Log.i("TAG", "FOOTER ENABLE: " + footerEnable);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && firstVisibleItem == wrapper.getHeadersCount()
                    && !headerEnable
                    && !hasHeader) {
                Toast.makeText(getContext(), "到顶了", Toast.LENGTH_SHORT).show();
                headerEnable = true;
            }
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING
                    && firstVisibleItem == wrapper.getHeadersCount()
                    && lastVisibleItem != wrapper.getHeadersCount() + wrapper.getRealItemCount()
                    && !headerEnable
                    && !hasHeader){
//                wrapper.addHeaderView(LayoutInflater.from(getContext()).inflate(R.layout.item_listheader, recyclerView, false));
//                wrapper.notifyItemInserted(0);
                hasHeader = true;
                headerEnable = false;
            }
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem == wrapper.getItemCount() - 1
                    && firstVisibleItem != 0
                    && !footerEnable
                    && !hasFooter) {
                Toast.makeText(getContext(), "到底了", Toast.LENGTH_SHORT).show();
                footerEnable = true;
            }
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING
                    && lastVisibleItem == wrapper.getItemCount() - 1
                    && firstVisibleItem != 0
                    && footerEnable
                    && !hasFooter){
                Log.i("TAG", "show footer");
                wrapper.addFooterView(LayoutInflater.from(getContext()).inflate(R.layout.item_listfooter, recyclerView, false));
                wrapper.notifyItemChanged(wrapper.getHeadersCount() + wrapper.getRealItemCount() + wrapper.getFootersCount());
                hasFooter = true;
                footerEnable = false;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            Log.i("TAG", "dy = " + dy);
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem =
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            firstVisibleItem =
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

            boolean isSignificantDelta = Math.abs(dy) > scrollDownThreshold;
            if (isSignificantDelta){
                //pagedown
                if (dy > 0) {
                }else {
                }
            }else {
            }
        }

    }



}
