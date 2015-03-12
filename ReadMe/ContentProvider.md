# ContentProvider
##### 1-24--1-27:Data,ContentProvider
+ 文件存储读取 
	存:openFileOutput;
	取:openFileInput;
	
		FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput(“data”, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(contentString);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
      		try{
				if(writer!=null){
					writer.close();
				}
			}catch(...){..}
		} 
		
+ sharedPreferences
	- 获取`sharedPreferences`对象`getSharedPreferences()`或者`PreferenceManager.getDefaultSharedPreferences()`
	- 对数据存储`editor = pref.edit()`调用`editor.putString(“name”,name)`,再调用`editor.commit()`提交,或者`editor.clear()`清楚数据
	- 数据读取 `name = pref.getString(“name”,””)`
+ SQLite
	- 创建/更新数据库
		`dbhelper = new mDatabaseHelper(context,”BookStore.db”,null,1);`
		第一次调用`getWritableDatabase()`会调用`mDatabaseHelper`的`onCreate()`方法
		`db.execSQL(“sql语句”);`
			
			class mDatabaseHelper extends SQLiteOpenHelper{
				
				onCreate(){
					db.execSQL(“”)
				}
				
				onUpgrade(){
					db.execSQL(“drop table if exists Book”);
					db.execSQL(“..”)
					onCreate(db);
				}
			}
			
	- CRUD操作
			
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(“name”,”flyer”);
			:
			:
			db.insert(“表名”,null,values);
			values.clear();
			values.put();
			:
			:
			db.update(“table_name”,values,”naem = ?”,new String[]{“flyer”});
			db.delete(“table_name”,values,”name = ?”,new String[]{“flyer”});
			
	 query操作
			
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor cursor = db.query(“table_name”,null,null,null,null,null,null);
			if(cursor.moveToFirst()){
				do{
					String name = cursor.getString(cursor,getColumnIndex(“name”));
					String author = cursor.get....
				}while(cursor.moveToNext())
			}
			cursor.close();
	- 事务
			   
			   SQLiteDatabase db = databaseHelper.getWritableDatabase();
               db.beginTransaction();//开启事务
               try {
                   db.delete(“Book”,null,null);
                   //if (true){
                   //   throw new NullPointerException();
                   //}//手动抛出异常,事务不成功,关闭异常事务成功
                   ContentValues values = new ContentValues();
                   values.put(“name”,”Game of Thrones”);
                   values.put(“author”,”George Martin”);
                   values.put(“pages”,720);
                   values.put(“price”,20.85);
                   db.insert(“Book”,null,values);
                   db.setTransactionSuccessful();//事务执行成功
               }catch (Exception e){
                   e.printStackTrace();
               }finally {
                   db.endTransaction();
               }
+ ContentProvider
    - ConentResolver
    - myContentProvider

    - 跨程序数据共享　


