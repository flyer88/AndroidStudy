package com.holyboom.flyer.tel.Contact;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * Created by flyer on 15/2/26.
 */
public class Contact {
    String contactNumber;
    String contactName;
    Context context;
    public Contact(Context context){
        this.context = context;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactName(String name) {
        this.contactName = name;
    }

    public String setContactname() {
        return contactName;
    }


}
