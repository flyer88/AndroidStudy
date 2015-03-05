package com.holyboom.flyer.tel.Util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;

import com.holyboom.flyer.tel.Contact.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyer on 15/2/27.
 */
public class ProviderUtil {
    Context context;
    List<Contact> contactList = new ArrayList<Contact>();
    public ProviderUtil(Context context){
        this.context = context;
    }

    /**
     * 读取所有联系人
     * @return List<Contact>
     */
    public List<Contact> readAllLocalContacts(){

        Cursor cursor = null;
        Contact contact = new Contact(context);
        try{
            cursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null
            );
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                );
                String number = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER)
                );
                //contactList.add(name+""+number);
                contact.setContactName(name);
                contact.setContactNumber(number);
                contactList.add(contact);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null) {
                cursor.close();
            }
        }
    return contactList;
    }
    public List<Contact> readAllSimContacts(){
        Uri uri = Uri.parse("content://icc/adn");
        Cursor cursor = null;
        Contact contact = new Contact(context);
        try{
            cursor = context.getContentResolver().query(
                uri,null,null,null,null
            );
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(Contacts.People.NAME));
                String number = cursor.getString(cursor.getColumnIndex(Contacts.People.NUMBER));
                contact.setContactName(name);
                contact.setContactNumber(number);
                contactList.add(contact);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null) {
                cursor.close();
            }
        }
        return contactList;
    }
    public List<Contact> readAllContacts(){
        readAllLocalContacts();
        readAllSimContacts();
        return contactList;
    }

}
