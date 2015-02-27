package com.holyboom.flyer.tel.Util;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.holyboom.flyer.tel.Contact.Contact;

import java.util.List;

/**
 * Created by flyer on 15/2/27.
 */
public class ProviderUtil {
    Context context;
    public ProviderUtil(Context context){
        this.context = context;
    }

    /**
     * 读取所有联系人
     * @return List<Contact>
     */
    public List<Contact> readAllContacts(){
        List<Contact> contactList = null;
        Cursor cursor = null;
        Contact contact = new Contact(context);
        try{
            cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
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
}
