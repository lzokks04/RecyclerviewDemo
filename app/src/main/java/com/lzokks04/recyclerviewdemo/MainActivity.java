package com.lzokks04.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerviewAdapter adapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //设置LayoutManager为LinearLayoutManager
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置LayoutManager为GridLayoutManager,传入一个Context和每行显示的item数
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        //设置分割线需要自定义
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        //设置adapter
        recyclerView.setAdapter(adapter = new RecyclerviewAdapter(this, mDatas));
        //设置监听器，由于recyclerview没有内置监听器只能自己从adapter处定制
        adapter.setOnItemClickLitener(new RecyclerviewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"(短按)这是第"+position+"个Item",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this,"(长按)这是第"+position+"个Item",Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 1; i <= 100; i++) {
            mDatas.add(String.valueOf(i));
        }
    }

}


