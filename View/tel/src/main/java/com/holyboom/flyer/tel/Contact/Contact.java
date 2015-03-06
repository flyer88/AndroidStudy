package com.holyboom.flyer.tel.Contact;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * Created by flyer on 15/2/26.
 */
public class Contact {
    String contactLocation;
    String contactNumber;
    String contactName;
    String contactEmail;
    int contactID;
    Context context;
    public Contact(Context context){
        this.context = context;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setContactName(String name) {
        this.contactName = name;
    }

    public String setContactname() {
        return contactName;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public void setLocation(String location) {
        this.contactLocation = location;
    }

    public String getLocation() {
        return contactLocation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public int getContactID() {
        return contactID;
    }

    public String getContactName() {
        return contactName;
    }
}
