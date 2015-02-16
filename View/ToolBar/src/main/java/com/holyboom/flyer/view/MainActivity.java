package com.holyboom.flyer.view;

//import android.app.Activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

//import android.widget.Toolbar;

public class MainActivity extends ActionBarActivity {


    //toolbar
    Toolbar toolbar;

    //DrawerLayout
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ListView listView;
    String[] strings = {"A","B","C","D"};
    ArrayAdapter arrayAdapter;

    //ViewPager
    ViewPager viewPager;
    List<View> viewPagerList = new ArrayList<View>() ;

    //CardView
    CardView cardView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initDrawerLayout();
        initViewPager();
        initCardView();

    }
    public void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_navigation);
        listView = (ListView) findViewById(R.id.list_view);
        toolbar.setTitle("Toolbar");//设置Toolbar标题
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void initDrawerLayout(){
       actionBarDrawerToggle =  new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(arrayAdapter);

    }
    public void initViewPager(){
        Log.e("initViewPager","初始化viewpager");

        LayoutInflater layoutInflater = getLayoutInflater().from(this);
        viewPagerList.add(layoutInflater.inflate(R.layout.item1,null));
        viewPagerList.add(layoutInflater.inflate(R.layout.item2,null));
        viewPagerList.add(layoutInflater.inflate(R.layout.item3,null));
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewPagerList.get(position));
                Log.e("instantiateItem viewpager","position is "+position);
                return viewPagerList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewPagerList.get(position));
                Log.e("destroyItem viewpager","position is "+position);
            }

            @Override
            public int getCount() {
                return viewPagerList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });


    }
    public void initCardView(){
        cardView = (CardView) findViewById(R.id.card_view);
    }
}
