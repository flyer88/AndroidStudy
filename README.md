
##### 1-19:learn about webView:
+ `<WebView></WebView>`
+ `(WebView)findViewById()`
+ `webView.getSettings().setJavaScriptEnabled(true)`	
+ `webView.setWebViewClient(new WebViewClient(){})`
+ `shouldOverrideUrlLoading()` 
+ `webView.loadUrl(url)`

#####  1-20:learn Broadcast:
+ 动态注册
	- 创建IntentFilter实例,添加action;
	- 创建广播接收器-->重写onReceive()
	- 调用registerReceiver(BroadcastReceiver,IntentFilter)进行注册
+ manifest注册
	- `<receiver android:name=...><intent-filter><action android:name....></intent-filter></receiver>`
	- 创建广播接收器-->重写onReceive()