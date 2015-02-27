package com.holyboom.flyer.tel.Contact;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.holyboom.flyer.tel.R;
import com.holyboom.flyer.tel.Util.ProviderUtil;

import java.util.List;

/**
 * Created by flyer on 15/2/27.
 */
public class ContactActivity extends ActionBarActivity{

    RecyclerView contactRecyclerView;
    List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ProviderUtil providerUtil = new ProviderUtil(ContactActivity.this);
        contactList = providerUtil.readAllContacts();
        contactRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactRecyclerView.setHasFixedSize(true);
        contactRecyclerView.setAdapter(new ContactAdapter(ContactActivity.this,contactList));
    }
}
