
创建IntentFilter实例,添加action;
创建广播接收器-->重写onReceive()
调用registerReceiver(BroadcastReceiver,IntentFilter)进行注册


发送
接收
系统广播
本地广播


- 静态注册-manifests
- 动态注册-代码内部注册

