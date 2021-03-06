package com.holyboom.flyer.tel.Util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;

import com.holyboom.flyer.tel.Contact.Contact;
import com.holyboom.flyer.tel.Message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyer on 15/2/27.
 */
public class ProviderUtil {
    Context context;
    List<Contact> contactList = new ArrayList<Contact>();
    List<Message> messageList = new ArrayList<Message>();
    public ProviderUtil(Context context){
        this.context = context;
    }

    public  List<Contact> getContactList() {
        return contactList;
    }

    /**
     * 读取所有本地联系人
     * @return List<Contact>
     */
    public void readAllLocalContacts(){

        Cursor cursor = null;

        try{
            cursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null
            );
            while (cursor.moveToNext()){
                Contact contact = new Contact(context);
                String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                );
                String number = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER)
                );
                //contactList.add(name+""+number);
                contact.setContactName(name);
                contact.setContactNumber(number);
                contact.setLocation("P");
                contactList.add(contact);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null) {
                cursor.close();
            }
        }
    }

    /**
     * 读取所有SIM卡中联系人
     * @return List<Contact>
     */
    public void readAllSimContacts(){
        Uri uri = Uri.parse("content://icc/adn");
        Cursor cursor = null;

        try{
            cursor = context.getContentResolver().query(
                uri,null,null,null,null
            );
            while (cursor.moveToNext()){
                Contact contact = new Contact(context);
                String name = cursor.getString(cursor.getColumnIndex(Contacts.People.NAME));
                String number = cursor.getString(cursor.getColumnIndex(Contacts.People.NUMBER));
                contact.setContactName(name);
                contact.setContactNumber(number);
                contact.setLocation("S");
                contactList.add(contact);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null) {
                cursor.close();
            }
        }
    }

    /**
     * 读取所有联系人
     * @return List<Contact>
     */
    public List<Contact> readAllContacts(){
        readAllLocalContacts();
        Log.d("ReadAllLocalContacts","完成读取本地联系人");
        readAllSimContacts();
        Log.d("ReadAllSimContacts","完成读取Sim卡联系人");
        return contactList;
    }

    public List<Message> readAllMessage(){
        return  messageList;
    }
//    public List<Contact> readContacts(){
//        Uri uri  = Uri.parse("content://com.android.contacts/contacts");
//        Cursor cursor = context.getContentResolver().query(uri,new String[]{"_id"},null,null,null);
//        while(cursor.moveToNext()){
//            Contact contact = new Contact(context);
//            contact.setContactID(cursor.getInt(0));
////            int contractID = cursor.getInt(0);
////            StringBuilder sb = new StringBuilder("contractID=");
////            sb.append(contractID);
//            uri = Uri.parse("content://com.android.contacts/contacts/"+contact.getContactID()+"/data");
//            Cursor cursor1 = context.getContentResolver().query(uri,new String[]{"mimetype","data1","data2"},null,null,null);
//            while(cursor1.moveToNext()){
//                String data1 = cursor1.getString(cursor1.getColumnIndex("data1"));
//                String mimeType = cursor1.getString(cursor1.getColumnIndex("mimetype"));
//                if ("vnd.android.cursor.item/name".equals(mimeType)){
//                    contact.setContactName(data1);
//                    //sb.append(",name="+data1);
//                }else if ("vnd.android.cursor.item/email_v2".equals(mimeType)){
//                    contact.setContactEmail(data1);
//                    //sb.append(",email="+data1);
//                }else if ("vnd.android.cursor.item/phone_v2".equals(mimeType)){
//                    contact.setContactNumber(data1);
//                    //sb.append(",phone="+data1);
//                }
//            }
//            contactList.add(contact);
//            cursor1.close();
//        }
//        cursor.close();
//        return contactList;
//    }
}
