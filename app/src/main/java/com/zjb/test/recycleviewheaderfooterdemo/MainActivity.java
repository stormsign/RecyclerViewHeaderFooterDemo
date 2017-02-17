package com.zjb.test.recycleviewheaderfooterdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> list;
    private HeaderFooterWrapper wrapper;
    private MyScrollListener myScrollListener;
    private final int PAGESIZE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        for (int i=0; i<PAGESIZE; i++){
            list.add(i+"");
        }
        MyAdapter adapter = new MyAdapter(this, list);
        wrapper = new HeaderFooterWrapper(adapter);
        recyclerView.setAdapter(wrapper);
        myScrollListener = new MyScrollListener();
        recyclerView.addOnScrollListener(myScrollListener);

    }

    public void removeHeader(View view){

    }

    public void removeFooter(View view){
        int size = list.size();
        for (int i = size; i<size+PAGESIZE; i++){
            list.add(i+"");
        }
        wrapper.removeFooter();
        myScrollListener.hasFooter = false;
    }




    class MyScrollListener extends RecyclerView.OnScrollListener{

        private int lastVisibleItem;
        private int firstVisibleItem;
        int scrollDownThreshold = 0;
        int scroll_direction;
        public boolean hasHeader;
        public boolean hasFooter;

        private static final int SCROLL_UP = 1;
        private static final int SCROLL_DOWN = -1;
        private static final int SCROLL_DEFAULT = 0;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            Log.i("TAG", "DISPLAY STATUS: "+ scroll_direction);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && firstVisibleItem == 0
                    && scroll_direction == SCROLL_DEFAULT) {
                Toast.makeText(MainActivity.this, "到顶了", Toast.LENGTH_SHORT).show();
//                scroll_direction = SCROLL_DEFAULT;
            }
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING
                    && firstVisibleItem == 0
                    && scroll_direction == SCROLL_UP){
                wrapper.addFooterView(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_listheader, recyclerView, false));
                wrapper.notifyItemChanged(wrapper.getHeadersCount()+wrapper.getRealItemCount()+wrapper.getFootersCount());
                hasHeader = true;
            }
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem == wrapper.getItemCount() - 1
                    && firstVisibleItem != 0
                    && scroll_direction == SCROLL_DOWN
                    && !hasFooter) {
                Toast.makeText(MainActivity.this, "到底了", Toast.LENGTH_SHORT).show();
                scroll_direction = SCROLL_DEFAULT;
            }
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING
                    && lastVisibleItem == wrapper.getItemCount() - 1
                    && firstVisibleItem != 0
                    && scroll_direction == SCROLL_DOWN
                    && !hasFooter){
                Log.i("TAG", "show footer");
                wrapper.addFooterView(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_listheader, recyclerView, false));
                wrapper.notifyItemChanged(wrapper.getHeadersCount()+wrapper.getRealItemCount()+wrapper.getFootersCount());
                hasFooter = true;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            Log.i("TAG", "dy = "+dy);
            lastVisibleItem =
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            firstVisibleItem =
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

            boolean isSignificantDelta = Math.abs(dy) > scrollDownThreshold;
                if (isSignificantDelta){
                    //pagedown
                    if (dy > 0) {
                        scroll_direction = SCROLL_DOWN;
                    }else {
                        scroll_direction = SCROLL_UP;
                    }
                }else {
                    scroll_direction = SCROLL_DEFAULT;
                }
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
