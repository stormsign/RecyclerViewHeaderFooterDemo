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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyRecyclerView recyclerView;
    private List<String> list;
    private HeaderFooterWrapper wrapper;
//    private MyScrollListener myScrollListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        ViewGroup.LayoutParams params = header.getLayoutParams();
        params.height = 0;
        header.setLayoutParams(params);
        wrapper.addHeaderView(header);
        recyclerView.setAdapter(wrapper);
//        myScrollListener = new MyScrollListener();
//        recyclerView.addOnScrollListener(myScrollListener);

    }

    public void removeHeader(View view){
        recyclerView.removeHeader(view);
    }

    public void removeFooter(View view) {
        recyclerView.removeFooter(view);
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
