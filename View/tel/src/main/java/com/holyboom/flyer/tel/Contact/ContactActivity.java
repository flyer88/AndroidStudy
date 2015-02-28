package com.holyboom.flyer.tel.Contact;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.holyboom.flyer.tel.MaterialUi.FloatingActionBar.FloatingActionButton;
import com.holyboom.flyer.tel.R;
import com.holyboom.flyer.tel.Util.ProviderUtil;

import java.util.List;

/**
 * Created by flyer on 15/2/27.
 */
public class ContactActivity extends Activity {


    List<Contact> contactList;
    ContactAdapter contactAdapter;

    RecyclerView contactRecyclerView;
    FloatingActionButton addContactFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initContactUI();

        ProviderUtil providerUtil = new ProviderUtil(ContactActivity.this);
        contactList = providerUtil.readAllLocalContacts();
        contactAdapter = new ContactAdapter(ContactActivity.this,contactList);

        contactRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactRecyclerView.setHasFixedSize(true);
        contactRecyclerView.setAdapter(contactAdapter);
    }
    public void initContactUI(){
        contactRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_contact);
        addContactFab = (FloatingActionButton) findViewById(R.id.fab_add_contact);
    }
}
