package com.holyboom.flyer.recyclerview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    List<Actor> actorList = new ArrayList<Actor>();
    private String[] names = { "朱茵", "张柏芝", "张敏", "巩俐", "黄圣依", "赵薇", "莫文蔚", "如花" };
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actorList.add(new Actor("朱茵"));
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 设置ItemAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        recyclerView.setHasFixedSize(true);
        //myAdapter = new MyAdapter(actorList,MainActivity.this);
        recyclerView.setAdapter(new MyAdapter(actorList,MainActivity.this));
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()) {
//            // 当点击actionbar上的添加按钮时，向adapter中添加一个新数据并通知刷新
//            case R.id.action_add:
//                if (myAdapter.getItemCount() != names.length) {
//                    actorList.add(new Actor(names[myAdapter.getItemCount()]));
//                    recyclerView.scrollToPosition(myAdapter.getItemCount() - 1);
//                    myAdapter.notifyDataSetChanged();
//                }
//                return true;
//            // 当点击actionbar上的删除按钮时，向adapter中移除最后一个数据并通知刷新
//            case R.id.action_remove:
//                if (myAdapter.getItemCount() != 0) {
//                    actorList.remove(myAdapter.getItemCount()-1);
//                    recyclerView.scrollToPosition(myAdapter.getItemCount() - 1);
//                    myAdapter.notifyDataSetChanged();
//                }
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
