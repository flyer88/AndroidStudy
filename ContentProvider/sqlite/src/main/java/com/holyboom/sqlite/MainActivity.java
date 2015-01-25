package com.holyboom.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * Created by flyer on 15-1-25.
 */
public class MainActivity extends ActionBarActivity {

    DatabaseHelper databaseHelper;

    Button createDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 创建/更新数据库
         */
        //databaseHelper = new DatabaseHelper(MainActivity.this,"BookStore.db",null,1);
        databaseHelper = new DatabaseHelper(MainActivity.this,"BookStore.db",null,2);
        createDatabase = (Button)findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.getWritableDatabase();
            }
        });
        /**
         * 数据库添加数据
         */
        Button addData = (Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始组装第一条数据
                values.put("name","第一行代码");
                values.put("author","郭霖");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);//插入第一条数据
                values.clear();
                //开始组装第二条数据
                values.put("name","Thinking in Java");
                values.put("author","Bruce Eckel");
                values.put("pages",999);
                values.put("price",100);
                db.insert("Book",null,values);
            }
        });
        /**
         * 数据库数据更新
         */
        Button updateDat = (Button)findViewById(R.id.update_data);
        updateDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price",10.99);
                db.update("Book",values,"name = ?",new String[]{"第一行代码"});
            }
        });
        /**
         * 数据库数据删除
         */
        Button deleteData = (Button)findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                db.delete("Book","pages > ?",new String[]{ "500" });
            }
        });
        /**
         * 数据库数据查询
         */
        Button queryData = (Button)findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                //查询Book表中所有的数据
                Cursor cursor = db.query("Book",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.e("Sqlite","book name is "+name);
                        Log.e("Sqlite","book author is "+author);
                        Log.e("Sqlite","book pages is "+pages);
                        Log.e("Sqlite","book price is "+price);
                    }while(cursor.moveToNext());
                }
                cursor.close();
            }
        });
        /**
         * 重置数据库
         */
       Button replaceData = (Button)findViewById(R.id.replace_data);
       replaceData.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SQLiteDatabase db = databaseHelper.getWritableDatabase();
               db.beginTransaction();//开启事务
               try {
                   db.delete("Book",null,null);
//                   if (true){
//                       throw new NullPointerException();
//                   }
                   ContentValues values = new ContentValues();
                   values.put("name","Game of Thrones");
                   values.put("author","George Martin");
                   values.put("pages",720);
                   values.put("price",20.85);
                   db.insert("Book",null,values);
                   db.setTransactionSuccessful();//事务执行成功
               }catch (Exception e){
                   e.printStackTrace();
               }finally {
                   db.endTransaction();
               }
           }
       });

    }
}
