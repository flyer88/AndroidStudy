
##### 1-19:learn about webView:
+ `<WebView></WebView>`
+ `(WebView)findViewById()`
+ `webView.getSettings().setJavaScriptEnabled(true)`	
+ `webView.setWebViewClient(new WebViewClient(){})`
+ `shouldOverrideUrlLoading()` 
+ `webView.loadUrl(url)`

#####  1-20--1-23:learn Broadcast:
+ 动态注册(registerReceiver)
	- 创建IntentFilter实例,添加action;
	- 创建广播接收器-->重写onReceive()
	- 调用registerReceiver(BroadcastReceiver,IntentFilter)进行注册
+ 静态注册(manifest)
	- `<receiver android:name=...><intent-filter><action android:name....></intent-filter></receiver>`
	- 创建广播接收器-->重写onReceive()
+ 自定义broadcast
	- 新建test1,manifest注册,设置onclick,发送广播
	- `Intent intent = new Intent("com.flyer.MY_BROADCAST")`
	- `sendBroadcast(intent)
+ 有序广播
	- 新建test2,manifest静态注册一个与上述自定义broadcast接收一样的广播接收器`
	- 在test1中将`sendBroadcast(intent)`改为`sendOrderBroadcast(intent)`
	- 在AnotherBroadcast中`abortBroadcast()`截断广播
+ localBroadcast
	- `localBroadcastManager = LocalBroadcastManager.getInstance(this)`
	- `localBroadcastManager.sendBroadcast(intent)`
	- `localBroadcastManager.registerReceiver(localReceiver,intentFilter)`
+ 强制下线
	- `ActivityCollector`泛型操作所有的`activity`（`addActivity(..){}removeActivity(..){}finishALl(..){}`）		
			BaseActivity extends Activity{
              ..onCreate{ActivityCollector.addActivity}
          	..onDestroy{ActivityCollector.removeActivity}  
          }	
        
    - 
    	
   		LoginActivity extends BaseActivity{}
   		MainActivity extends BaseActivity{}
	- 	`MainActivity`中设置按钮，调用`sendBroadcast(intent)`;
	- 	`Manifest`中注册广播
	- 	`ForceToOfflineReceiver`接收处理广播,弹出`AlertDialog`	
	- 	确定后重新启动`LoginActivity`,因为广播启动,需要在intent中添加flag,即:`intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);`
	- 	`AlertDialog`需要依附于一个具体的`Activity`所以在调用`show()`之前需要`getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);`表明这是一个系统级别的`AlertDialog`,依附于系统界面



##### 1-24--1-27:Data,ContentProvider
+ 文件存储读取 
	存:openFileOutput;
	取:openFileInput;
		FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("data", Context.MODE_PRIVATE);
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
	- 对数据存储`editor = pref.edit()`调用`editor.putString("name",name)`,再调用`editor.commit()`提交,或者`editor.clear()`清楚数据
	- 数据读取 `name = pref.getString("name","")`
+ SQLite
	- 创建/更新数据库
		`dbhelper = new mDatabaseHelper(context,"BookStore.db",null,1);`
		第一次调用`getWritableDatabase()`会调用`mDatabaseHelper`的`onCreate()`方法
		`db.execSQL("sql语句");`
			class mDatabaseHelper extends SQLiteOpenHelper{
				
				onCreate(){
					db.execSQL("")
				}
				
				onUpgrade(){
					db.execSQL("drop table if exists Book");
					db.execSQL("..")
					onCreate(db);
				}
			}
			
	- CRUD操作
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("name","flyer");
			:
			:
			db.insert("表名",null,values);
			values.clear();
			values.put();
			:
			:
			db.update("table_name",values,"naem = ?",new String[]{"flyer"});
			db.delete("table_name",values,"name = ?",new String[]{"flyer"});
			
	 query操作
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor cursor = db.query("table_name",null,null,null,null,null,null);
			if(cursor.moveToFirst()){
				do{
					String name = cursor.getString(cursor,getColumnIndex("name"));
					String author = cursor.get....
				}while(cursor.moveToNext())
			}
			cursor.close();
	- 事务
			   SQLiteDatabase db = databaseHelper.getWritableDatabase();
               db.beginTransaction();//开启事务
               try {
                   db.delete("Book",null,null);
                   //if (true){
                   //   throw new NullPointerException();
                   //}//手动抛出异常,事务不成功,关闭异常事务成功
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
+ ContentProvider
    - ConentResolver
    - myContentProvider

    - 跨程序数据共享　

##### 1-27:Notification
+ 通知栏（`notification`）
	- `NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)`
	- `Notification notification = Notification(R.drawable.picture,"ticker",System.currentTimeMills())`
	- `notification.setLatestEventInfo(context,"title","content",pendintIntent)`
	-  `manager.notify(id,notification)`
+ 收发短信（）
	－ 获取`Bundle`,转换成`SmsMessage[]`
			Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[])bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
		
		

