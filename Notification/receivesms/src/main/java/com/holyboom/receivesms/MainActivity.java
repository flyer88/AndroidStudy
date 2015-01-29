package com.holyboom.receivesms;


import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    TextView sender,content;
    IntentFilter receiverFilter;
    MessageReceiver messageReceiver;


    EditText to,msgInput;
    Button sendMsg;
    SendResultStatusReceiver sendResultStatusReceiver;
    IntentFilter resultFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sender = (TextView)findViewById(R.id.sender);
        content = (TextView)findViewById(R.id.content);
        receiverFilter = new IntentFilter();
        receiverFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        //receiverFilter.setPriority(100);//提高权限，用于拦截短信
        messageReceiver = new MessageReceiver();
        registerReceiver(messageReceiver,receiverFilter);


        to = (EditText)findViewById(R.id.to);
        msgInput = (EditText)findViewById(R.id.msg_input);
        sendMsg = (Button)findViewById(R.id.send_msg);

        resultFilter = new IntentFilter();
        resultFilter.addAction("SEND_SMS_ACTION");
        sendResultStatusReceiver = new SendResultStatusReceiver();
        registerReceiver(sendResultStatusReceiver,resultFilter);
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("SEND_SMS_ACTION");
                PendingIntent pendingIntent =  PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(to.getText().toString(),null,msgInput.getText().toString(),pendingIntent,null);

            }
        });


    }

    class SendResultStatusReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (getResultCode() == RESULT_OK){
                Toast.makeText(context,"短信发送成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"短信发送失败",Toast.LENGTH_SHORT).show();
            }
        }
    }

    class MessageReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[])bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0;i<messages.length;i++){
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            String address = messages[0].getOriginatingAddress();
            String fullMessage = "";
            for (SmsMessage message:messages){
                fullMessage +=message.getMessageBody();
            }
            sender.setText(address);
            content.setText(fullMessage);
            //abortBroadcast();//拦截广播
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(messageReceiver);
        unregisterReceiver(sendResultStatusReceiver);
    }
}
