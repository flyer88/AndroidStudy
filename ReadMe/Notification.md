# Notification
##### 1-27-29:Notification,PendingIntent,收发短信
+ 通知栏（`notification`）：
	-  调用 `getSystemService()`，获取`NotificationManager`实例
        	
        	NotificationManager manager = (NotificationManager)
        	getSystemService(Context.NOTIFICATION_SERVICE)
	- 创建notification对象
        	`Notification notification = Notification(icon,”ticker”,when)`
    - 设置notification的布局
`notification.setLatestEventInfo(context,”title”,”content”,PendintIntent)`
	- 显示notification
	`manager.notify(id,notification)`
    - PendingIntent 创建及作用具体参见：[http://blog.csdn.net/sir_zeng/article/details/8186835](http://)
    		创建：`PendingIntent pi = PendingIntent.
    		getActivity(Context,0,intent,flag)/getBroadcast()/getService()`
   		  作用：PendingIntent可以看作是对Intent的包装。供当前App之外的其他App调用。有点“被动”或是“Callback”的意思，但不是严格意义上的“被动”或是“Callback”。总之，当前App不能用它马上启动它所包裹的Intent。而是在外部App执行这个PendingIntent时，间接地、实际地调用里面的Intent。PendingIntent主要持有的信息是它所包装的Intent和当前App的Context。正由于PendingIntent中保存有当前App的Context，使它赋予外部App一种能力，使得外部App可以如同当前App一样的执行PendingIntent里的Intent，就算在执行时当前App已经不存在了，也能通过存在PendingIntent里的Context照样执行Intent。
    	
+ 收短信
  - 重写广播,用来接受系统接收短信后发出的广播
 			
 			class MessageBroadcast extends Broadcast{
  				//abortBroadcast()//拦截短信
 		 	}
  - 获取`Bundle`,转换成`SmsMessage[]`
			
			Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[])bundle.get(“pdus”);
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
        	receiverFilter.addAction(“android.provider.Telephony.SMS_RECEIVED”);
        	//receiverFilter.setPriority(100);//提高权限，用于拦截短信
        	messageReceiver = new MessageReceiver();
        	registerReceiver(messageReceiver,receiverFilter);

  - manifest权限
  			`android.permission.RECEIVE_SMS`

+ 发短信
  - 调用`getDefatul`获取`SmsManager`实例，调用`sendTextMessage`发送信息
  		
  			sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 				SmsManager manager = SmsManager.getDefault();
		  		manager.sendTextMessage(destinationAddress,scAddress,text,sentIntent,deliveryIntent)
		 	}
		destinationAddress:目标地址，电话号码
		scAddress:
      text:发送内容
      sentIntent:看下面
      deliveryIntent:
   
  - 调用`sendTextMessage`第四个参数监听短信发送时状态
		//第四个参数为PendingtIntent
		//重写广播
        
        	class SendResultStatusReceiver extends BroadcastReceiver{
        	if (getResultCode() == RESULT_OK){
                Toast.makeText(context,”短信发送成功”,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,”短信发送失败”,Toast.LENGTH_SHORT).show();
        	}
			//注册广播,”SEND_SMS_ACTION”
        	resultFilter = new IntentFilter();
        	resultFilter.addAction(“SEND_SMS_ACTION”);
        	sendResultStatusReceiver = new SendResultStatusReceiver();
        	registerReceiver(sendResultStatusReceiver,resultFilter);
			//重写发送短信时按钮动作
			sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        	//添加intent(“SEND_SMS_ACTION”)
                Intent intent = new Intent(“SEND_SMS_ACTION”);
                PendingIntent pendingIntent =  PendingIntent.
                getBroadcast(MainActivity.this,0,intent,0);
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(
                to.getText().toString(),null,msgInput.getText().toString(),pendingIntent,null);
            }
        	});
        
  - 第五个参数为发送后返回对方是否接受的信息，类似于第四个参数



