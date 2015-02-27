package com.holyboom.flyer.tel.Util;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * Created by flyer on 15/2/27.
 */
public class ProviderUtil {
    Context context;
    ProviderUtil(Context context){
        this.context = context;
    }
    public void readAllContacts(){
        Cursor cursor = null;
        cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
    }
}
