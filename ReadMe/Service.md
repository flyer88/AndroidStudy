# Service
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
        	Notification notification = new Notification(
        	R.drawable.ic_launcher,”notification”,System.currentTimeMillis());
       		Intent notificationIntent = new Intent(this,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        	notification.setLatestEventInfo(this,”title”,”content”,pendingIntent);
        	startForeground(1,notification);
        	Log.e(“onCreate”,”服务创建”);
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
        `startService(new Intent(this,MyIntentService.class));`
+ 定时任务，通过receiver来长期启动
  - 继承重写`service`
    	
    		public int onStartCommand(Intent intent, int flags, int startId) {
        	new Thread(new Runnable() {
            	public void run() {
                	Log.e(“LongRunningService”,”executed at “+new Date().toString());
            	}
        	}).start();
        	int anHour = 60*60*1000;
        	long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        	AlarmManager  alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        	Intent mIntent = new Intent(this,AlarmReceiver.class);
        	PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,mIntent,0);
        	//alarmManager.setExact(
        	//AlarmManager.ELAPSED_REALTIME,triggerAtTime,pendingIntent);
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


