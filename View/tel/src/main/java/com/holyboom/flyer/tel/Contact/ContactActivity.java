package com.holyboom.flyer.tel.Contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.holyboom.flyer.tel.MaterialUi.FloatingActionBar.FloatingActionButton;
import com.holyboom.flyer.tel.R;
import com.holyboom.flyer.tel.Util.DatabaseUtil;
import com.holyboom.flyer.tel.Util.ProviderUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by flyer on 15/2/27.
 */
public class ContactActivity extends ActionBarActivity {

    /**
     * viewpager所在页面位置
     */
    public static int VIEW_PAGER_POSITION = 0;
    ViewPager viewPager;

    List<Contact> allContactList,favoriteContactList;
    ContactAdapter allContactAdapter,favoriteContactAdapter;
    RecyclerView allContactRecyclerView,favoriteContactRecyclerView;

    Toolbar toolbar;
    FloatingActionButton addContactFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initBasicContactUI();
        initAllContactUI();
        initFavoriteContactUI();
        //根据VIEW_PAGER_POSITION的值来判断当前所在页面，由此跳转不同的修改联系人页面
        addContactFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VIEW_PAGER_POSITION == 0) {
                    DatabaseUtil databaseUtil = new DatabaseUtil(ContactActivity.this);
                    databaseUtil.initDatabase();
//                    Intent intent = new Intent(ContactActivity.this, EditContactActivity.class);
//                    startActivity(intent);
                }else if (VIEW_PAGER_POSITION == 1){

                }else if (VIEW_PAGER_POSITION ==2){

                }
            }
        });
    }

    /**
     * 初始化右下角按钮以及toolbar
     */
    public void initBasicContactUI(){
        int MaterialGreen = getResources().getColor(R.color.green_light);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_contact);
        setSupportActionBar(toolbar);
        addContactFab = (FloatingActionButton) findViewById(R.id.fab_add_contact);
        addContactFab.setDrawable(getResources().getDrawable(R.drawable.add_contact_fab));
        addContactFab.setColor(MaterialGreen);
    }

    /**
     * 初始化所有联系人界面
     */
    public void initAllContactUI(){
        DatabaseUtil databaseUtil = new DatabaseUtil(ContactActivity.this);
        //ProviderUtil providerUtil = new ProviderUtil(ContactActivity.this);
        allContactRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_contact);
        //contactList = providerUtil.readAllLocalContacts();
        //contactList = providerUtil.readAllSimContacts();
        allContactList = databaseUtil.getContactList();
        //allContactList = providerUtil.readAllContacts();
        //Log.e("allContactList",allContactList.size()+"");
        //contactList = providerUtil.readContacts();
        allContactAdapter = new ContactAdapter(ContactActivity.this,allContactList);
        allContactRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allContactRecyclerView.setHasFixedSize(true);
        allContactRecyclerView.setAdapter(allContactAdapter);
    }

    /**
     * 初始化收藏联系人界面
     */
    public void initFavoriteContactUI(){
        favoriteContactRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_favorite_contact);
    }
}
