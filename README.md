
##### 1-19:learn about webView:
+ `<WebView></WebView>`
+ `(WebView)findViewById()`
+ `webView.getSettings().setJavaScriptEnabled(true)`	
+ `webView.setWebViewClient(new WebViewClient(){})`
+ `shouldOverrideUrlLoading()` 
+ `webView.loadUrl(url)`

#####  1-20:learn Broadcast:
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
