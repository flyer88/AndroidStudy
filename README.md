
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



##### 1-24:ContentProvider
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

