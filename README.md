
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


##### 1-30--2-1 service
+ 子线程通过`Handler``Message`更新UI
  - 创建`handler`实例，获取`message`
		Handler handler = new Handler(){
        	public void HandleMessage(Message msg){
            	switch(msg.what){
            		case a:
           			
            	}
            }
        }
  - 新建子线程处理`Message`传递数据
  		new Thread(new Runnable{
        	public void run(){
            	Message message = new Message();
           		message.what = a;
          		message.obj = ..
         		message.arg1 = ..
        		message.arg2 = ..
          		handler.sendMessage(message);
            }
        }).start();
		
+ `AsyncTask`更新UI
  - 继承重写`AsyncTask<Params,Progress,Result>`第一个参数为传入值类型，第二个为进度显示值类型，返回值类型
  - `onPreExecute()`,常用初始化UI操作
  - `doInBackground(Params...)`后台运行程序，调用`publishProgress(Progress...)`方法来反馈当前任务进度
  - `onProgressUpdate(Progress...)`获取`publishProgress(Progress...)`的返还值，更新UI
  - `onPostExecute(Result)`后台任务执行完成后，获取其返还的值，进行UI操作
+ `Service`启动停止，继承`Service`，重写四个方法`
  - `onCreate()`服务创建是调用
  - `onStartCommand()`服务开启时调用，
  - `onDestory()`服务销毁时调用
  - 通过`Intent`开启或停止服务
		Intent intent = new Intent(this,Myservice.class);
        startService(intent);
        stopService(intent);
+ `Activity`和`Service`的绑定(服务开启后，就于活动没有什么关系)
  - 在`MyService`中创建新的`Binder`内部类
		class DownloadBinder extends Binder{
        	...
        }
        DownloadBinder dBinder = DownloadBinder();
  - 在`MyService`重写`onBind()`
		public IBinder onBinder(Intent intent){
            return dBinder;
        }
  - 在活动中创建`ServiceConnection`的实例
  		DownloadBinder downloadBinder = new DownloadBinder();
		ServiceConnection connection = new ServiceConnection(){
        	public void onServiceDisconnected(ComponentName name){
            
            }
            public void onServiceConnected(ComponentName name,IBinder service){
            	downloadBinder = (DownloadBinder)service;
            }
        }
  - 绑定与解绑服务
  		Intent bindIntent = new Intent(this,MyService.class);
		bindService(bindIntent,connection,BIND_AUTO_CREATE);
        unbindService(connection);
+ 前台服务
  - 重写`MyService`的`onCreate`的方法即可
		public void onCreate() {
        	super.onCreate();
        /*将后台服务变成一个前台服务*/
        	Notification notification = new Notification(R.drawable.ic_launcher,"notification",System.currentTimeMillis());
       	 Intent notificationIntent = new Intent(this,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        	notification.setLatestEventInfo(this,"title","content",pendingIntent);
        	startForeground(1,notification);
        	Log.e("onCreate","服务创建");
    	}
+ `new Thread`和`IntentService`防止ANR
  - new Thread
		public void onStartCommand(Intent intent,int flags,int startId){
        	new Thread(new Runnable(){
            	public void run(){
                	:
               		:
                    stopSelf();
                }
            }).start();
          	return ...;
        }
  - `IntentService`
  		public class MyIntentService extends IntentService{
 			public MyIntentService(){
            
            }
        	protected void onHandleIntent(){
            
            }
          	public void onDestroy(){
            
            }
        }
        主线程开启服务：
        startService(new Intent(this,MyIntentService.class));
+ 定时任务，通过receiver来长期启动
  - 继承重写`service`
    	public int onStartCommand(Intent intent, int flags, int startId) {
        	new Thread(new Runnable() {
            	public void run() {
                	Log.e("LongRunningService","executed at "+new Date().toString());
            	}
        	}).start();
        	int anHour = 60*60*1000;
        	long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        	AlarmManager  alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        	Intent mIntent = new Intent(this,AlarmReceiver.class);
        	PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,mIntent,0);//alarmManager.setExact(AlarmManager.ELAPSED_REALTIME,triggerAtTime,pendingIntent);
        //4.4后用此方法会更为准确
            alarmManager.set(AlarmManager.ELAPSED_REALTIME,triggerAtTime,pendingIntent);
            return super.onStartCommand(intent, flags, startId);
    	}
  - `receiver`,每隔1小时接收到一次
		public void onReceive(Context context, Intent intent) {
        	Intent mIntent = new Intent(context,LongRunningService.class);
        	context.startService(mIntent);
    	}

  - 活动开启服务
		Intent intent = new Intent(this,LongRunningService.class);
        startService(intent);
    
##### 2-2 -2-3 http,xml,json
+ `http`
 - `HttpURLConnection`
		URL url = new URL("http://www.baidu.com")
        HttpURLConnection connection = (HttpURLConnection)utl.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(8000)
        connection.setReadTimeout(8000)
        InputStream in = connection.getInputStream();
        connection.disconnect();
 - `HttpClient`
    `get`方式发送http请求
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://www.baidu.com");
        HttpClient.execute(httpGet);

	`post`方式发送http请求
		HttpPost httpPost = new HttpPost("http://www.baidu.com");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
      	params.add(new BasicNameValuePair("username","flyer");
		params.add(new BasicNameValuePair("password","123456");
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,"utf-8");
        httpPost.setEntity(params);
        httpPost.execute(httpPost)
     获取`Entity`
        if(httpResponse.getStatusLine().getStatusCode() ==200){
        	HttpEntity entity = httpResponse.getEntity();
            String response = EntityUtils.toString(entity);
        }
		
+ `xml`
 - Pull解析xml
 		//获取实例
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser xmlPullParser = factory.newPullParser();
        xmlPullParser.setInput(new StringReader(response));
        int eventType = xmlPullParser.getEventType();
            App app = new App();
            while(eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    //解析某个节点
                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodeName)) {
                            app.setId(xmlPullParser.nextText());
                        } else if ("name".equals(nodeName)) {
                            app.setName(xmlPullParser.nextText());
                        } else if ("version".equals(nodeName)) {
                            app.setVersion(xmlPullParser.nextText());
                        }
                        break;
                    }
                    //完成某个节点解析
                    case XmlPullParser.END_TAG: {
                        if ("app".equals(nodeName)) {
                            Log.e("id: ",app.id+"");
                            Log.e("name: ",app.name+"");
                            Log.e("id: ",app.version+"");
                            Message message = new Message();
                            message.what = SHOW_RESPONSE;
                            message.obj = app;
                            handler.sendMessage(message);
                        }
                        break;
                    }
                    default:
                        break;
                }
                //调到下一个节点
                eventType = xmlPullParser.next();
            }
 - SAX解析xml
 	`MainActivity`
 	    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
        MyHandler handler = new MyHandler();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(new InputSource(new StringReader(xmlData)));
    `MyHandler`继承`DefaultHandler`
    	public void startDocument() throws SAXException {
        	id = new StringBuilder();
        	name = new StringBuilder();
        	version = new StringBuilder();
    	}
    	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName = localName;
    	}
    	public void characters(char[] ch, int start, int length) throws SAXException {
        //根据节点名字将内容添加到对应的StringBuilder中
        	switch (nodeName){
           	 case "id":
           	     id.append(ch,start,length);
           	     break;
           	 case "name":
            	    name.append(ch,start,length);
           	     break;
                case "version":
                	version.append(ch,start,length);
      	  }
   	 }
    	public void endElement(String uri, String localName, String qName) throws SAXException {
        	if ("app".equals(localName)){
            	Log.e("ContentHandler","id is "+id);
            	Log.e("ContentHandler","name is "+name);
	            Log.e("ContentHandler","version is "+version);
    	        //清空StringBuilder
        	    id.setLength(0);
            	name.setLength(0);
	            version.setLength(0);
    	    }
    	}
	    public void endDocument() throws SAXException {
    	}
+ `json`
 - `JSONObject`
 	    JSONArray jsonArray = new JSONArray(response);
        for (int i = 0;i<jsonArray.length();i++){
        	JSONObject jsonObject = jsonArray.getJSONObject(i);
            Game game = new Game();
            game.id = jsonObject.getString("id");
            game.name = jsonObject.getString("name");
            game.version = jsonObject.getString("version");
            Message message = new Message();
            message.obj = game;
            handler.sendMessage(message);
        }
 - `GSON`
 	   Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for (App app : appList){
            Log.e("parseJSONWithGSON","id is "+app.id);
            Message message = new Message();
            message.obj = app;
            handler.sendMessage(message);
        }





