package com.holyboom.userdefinedbroadcast;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    Button sendBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBroadcast = (Button)findViewById(R.id.send_broadcast);
        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.flyer.holyboom.broadcast.MY_BROADCAST");
                //sendBroadcast(intent);
                sendOrderedBroadcast(intent,null);
                //Toast.makeText(MainActivity.this,"send broadcast",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
