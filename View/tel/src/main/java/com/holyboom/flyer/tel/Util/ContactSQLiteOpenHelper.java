package com.holyboom.flyer.tel.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by flyer on 15/3/6.
 */
public class ContactSQLiteOpenHelper extends SQLiteOpenHelper{

    Context context;
    public static final String CREATE_CONTACT = "create table Contact (" +
            "id integer primary key autoincrement," +
            "name text," +
            "number text)";
    public static final String CREATE_MESSAGE = "create table Message (" +
            "id integer primary key autoincrement," +
            "type integer," +
            "read integer," +
            "body text," +
            "status integer," +
            "date long" +
            ")";

    public ContactSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACT);
        Log.d("ContactSQLiteOpenHelper","完成数据库创建");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
