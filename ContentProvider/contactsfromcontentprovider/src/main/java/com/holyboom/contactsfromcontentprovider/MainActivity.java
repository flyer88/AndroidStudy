package com.holyboom.contactsfromcontentprovider;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {


    ListView contactView;
    ArrayAdapter<String> adapter;
    List<String> contactsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactView = (ListView)findViewById(R.id.contacts_view);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
        contactView.setAdapter(adapter);
        readContacts();
    }

    private void readContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    null,
                    null,
                    null
            );

            if (cursor != null){
                while(cursor.moveToNext()){
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add("姓名:"+name+"\n电话:"+number);
                }
            }
        }catch (Exception e){

        }finally {
            if (cursor!=null){
                cursor.close();
            }
        }
    }
}
