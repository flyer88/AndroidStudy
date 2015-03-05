package com.holyboom.flyer.tel.Contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
        //contactList = providerUtil.readAllLocalContacts();
        //contactList = providerUtil.readAllSimContacts();
        contactList = providerUtil.readAllContacts();
        contactAdapter = new ContactAdapter(ContactActivity.this,contactList);

        contactRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactRecyclerView.setHasFixedSize(true);
        contactRecyclerView.setAdapter(contactAdapter);
        addContactFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactActivity.this,EditContactActivity.class);
                startActivity(intent);
            }
        });
    }
    public void initContactUI(){
        int MaterialGreen = getResources().getColor(R.color.green_light);
        contactRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_contact);
        addContactFab = (FloatingActionButton) findViewById(R.id.fab_add_contact);
        addContactFab.setDrawable(getResources().getDrawable(R.drawable.add_contact_fab));
        addContactFab.setColor(MaterialGreen);
    }
}
