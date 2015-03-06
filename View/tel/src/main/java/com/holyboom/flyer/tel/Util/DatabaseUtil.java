package com.holyboom.flyer.tel.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.holyboom.flyer.tel.Contact.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyer on 15/3/6.
 */
public class DatabaseUtil {

    static int READ_FROM_PHONE = 0;

    Context context;
    ProviderUtil providerUtil;
    List<Contact> contactList;
    ContactSQLiteOpenHelper dbHelper;

    public DatabaseUtil(Context context){
        this.context = context;
        Log.d("初始化","开始获取联系人");
        providerUtil = new ProviderUtil(this.context);
        contactList = providerUtil.readAllContacts();
        Log.d("初始化","完成获取联系人");
    }

    public void initDatabase(){
        Log.d("初始化","初始化数据库");
        dbHelper = new ContactSQLiteOpenHelper(context,"Contact.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d("初始化","开始写入数据");
        ContentValues values = new ContentValues();
        //Log.e("ContaListsize",contactList.size()+"");
        for (int i =0 ;i<contactList.size();i++){
            values.put("name",contactList.get(i).getContactName());
            //Log.d("姓名",contactList.get(i).getContactName());
            values.put("number",contactList.get(i).getContactNumber());
            //Log.d("电话",contactList.get(i).getContactNumber());
            db.insert("Contact",null,values);
            values.clear();
        }
        Log.d("初始化","数据写入完成");

    }
    public List<Contact> getContactList(){

        return contactList;
    }
    public void queryContact(){}
    public void insertContact(){}
    public void deleteContact(){}
    public void updateContact(){}

    public void queryMessage(){}
    public void insertMessage(){}
    public void deleteMessage(){}
}
