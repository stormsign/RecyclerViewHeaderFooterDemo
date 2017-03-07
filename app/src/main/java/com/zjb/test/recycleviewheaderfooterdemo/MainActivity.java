package com.zjb.test.recycleviewheaderfooterdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyRecyclerView recyclerView;
    private List<String> list;
    private HeaderFooterWrapper wrapper;
    private FrameLayout parentView;
    //    private MyScrollListener myScrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        parentView = (FrameLayout) getWindow().getDecorView();
        recyclerView = (MyRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        for (int i=0; i<10; i++){
            list.add(i+"");
        }
        MyAdapter adapter = new MyAdapter(this, list);
        wrapper = new HeaderFooterWrapper(adapter);
        View header = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_listheader, recyclerView, false);
        wrapper.addHeaderView(header);
        recyclerView.setAdapter(wrapper);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        TextView leftText = (TextView) findViewById(R.id.left);
//        leftText.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        Log.i("LOG", " width=" + leftText.getMeasuredWidth() + "  height=" + leftText.getMeasuredHeight());
        MaskView maskView = new MaskView(MainActivity.this, leftText);
//        maskView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
//        parentView.addView(maskView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                FrameLayout.LayoutParams.MATCH_PARENT));
        maskView.invalidate();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void removeHeader(View view){
        recyclerView.removeHeader(view);
    }

    public void removeFooter(View view) {
        recyclerView.removeFooter(view);
    }

    private void getstatusBarHeight() {
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            System.out.println("hxy:"+"statusBarHeight"+statusBarHeight);
        }
    }



    class MyAdapter extends RecyclerView.Adapter{
        private List<String> list;
        private Context context;
        public MyAdapter(Context context, List<String> list){
            this.list = list;
            this.context = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(MainActivity.this).
                    inflate(R.layout.item_main, viewGroup, false));
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView text;
            public ViewHolder(View itemView) {
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.text);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder holder = (ViewHolder)viewHolder;
            holder.text.setText(list.get(i));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

}
