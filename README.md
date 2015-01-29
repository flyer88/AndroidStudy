
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

##### 1-27-29:Notification,PendingIntent,收发短信
+ 通知栏（`notification`）：
	-  调用 `getSystemService()`，获取`NotificationManager`实例
        	NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE)
	- 创建notification对象
        	Notification notification = Notification(icon,"ticker",when)
    - 设置notification的布局
       	notification.setLatestEventInfo(context,"title","content",PendintIntent)`
	- 显示notification
			 manager.notify(id,notification)
    - PendingIntent 创建及作用具体参见：[http://blog.csdn.net/sir_zeng/article/details/8186835](http://)
    		创建：PendingIntent pi = PendingIntent.getActivity(Context,0,intent,flag)/getBroadcast()/getService()
   		  作用：PendingIntent可以看作是对Intent的包装。供当前App之外的其他App调用。有点“被动”或是“Callback”的意思，但不是严格意义上的“被动”或是“Callback”。总之，当前App不能用它马上启动它所包裹的Intent。而是在外部App执行这个PendingIntent时，间接地、实际地调用里面的Intent。PendingIntent主要持有的信息是它所包装的Intent和当前App的Context。正由于PendingIntent中保存有当前App的Context，使它赋予外部App一种能力，使得外部App可以如同当前App一样的执行PendingIntent里的Intent，就算在执行时当前App已经不存在了，也能通过存在PendingIntent里的Context照样执行Intent。
    	
+ 收短信
  - 重写广播,用来接受系统接收短信后发出的广播
 			class MessageBroadcast extends Broadcast{
  				//abortBroadcast()//拦截短信
 		 	}
  - 获取`Bundle`,转换成`SmsMessage[]`
			Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[])bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0;i<messages.length;i++){
                messages[i] = SmsMessage.createFromPdu(
              (byte[]) pdus[i]);
            }
  - 调用`getOriginatingAddress()`和`getMessageBody()`获取地址和内容
  		  String address = messages[0].getOriginatingAddress();
			for(SmsMessage message : messages){
            	fullMessage += message[i].getMessageBody();
            }

  - 注册广播`android.provider.Telephony.SMS_RECEIVED`
    	receiverFilter = new IntentFilter();
        receiverFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //receiverFilter.setPriority(100);//提高权限，用于拦截短信
        messageReceiver = new MessageReceiver();
        registerReceiver(messageReceiver,receiverFilter);

  - manifest权限
  		android.permission.RECEIVE_SMS

+ 发短信
  - 调用`getDefatul`获取`SmsManager`实例，调用`sendTextMessage`发送信息
  		sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 				SmsManager manager = SmsManager.getDefault();
		  		manager.sendTextMessage(destinationAddress,scAddress,text,sentIntent,deliveryIntent)
		 }
		/*
        **destinationAddress:目标地址，电话号码
		**scAddress:
        **text:发送内容
        **sentIntent:看下面
        **deliveryIntent:
        */
  - 调用`sendTextMessage`第四个参数监听短信发送时状态
		//第四个参数为PendingtIntent
		//重写广播
        class SendResultStatusReceiver extends BroadcastReceiver{
        	if (getResultCode() == RESULT_OK){
                Toast.makeText(context,"短信发送成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"短信发送失败",Toast.LENGTH_SHORT).show();
        }
		//注册广播,"SEND_SMS_ACTION"
        resultFilter = new IntentFilter();
        resultFilter.addAction("SEND_SMS_ACTION");
        sendResultStatusReceiver = new SendResultStatusReceiver();
        registerReceiver(sendResultStatusReceiver,resultFilter);
		//重写发送短信时按钮动作
		sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //添加intent("SEND_SMS_ACTION")
                Intent intent = new Intent("SEND_SMS_ACTION");
                PendingIntent pendingIntent =  PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(to.getText().toString(),null,msgInput.getText().toString(),pendingIntent,null);

            }
        });
  - 第五个参数为发送后返回对方是否接受的信息，类似于第四个参数


##### 1-30 service

















