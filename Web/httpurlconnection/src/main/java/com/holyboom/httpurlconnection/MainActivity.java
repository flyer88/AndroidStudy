package com.holyboom.httpurlconnection;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    public static  final int SHOW_RESPONSE = 0;
    Button sendRequest;
    TextView responseText;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response = (String)msg.obj;
                    responseText.setText(response);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequest = (Button)findViewById(R.id.send_request);
        responseText = (TextView)findViewById(R.id.response);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.send_request){
            Log.e("onClick","sendRequest"+v.getId());
            sendRequestWithHttpURLConnection();
        }
    }

    private void sendRequestWithHttpURLConnection(){
        Log.e("sendRequestWithHttpURLConnection","start before thread");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://www.baidu.com");
                    connection = (HttpURLConnection)url.openConnection();
                    /*设置参数*/
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    /*数据流读入*/
                    InputStream in = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = bufferedReader.readLine())!=null){
                        response.append(line);
                    }
                    /*message发出消息*/
                    Message message = new Message();
                    message.what =  SHOW_RESPONSE;
                    message.obj = response.toString();
                    Log.e("message.obj","content is "+message.obj);
                    handler.sendMessage(message);
                }catch (Exception e){
                    e.getStackTrace();
                    Log.e("exception","throw exception");
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                        Log.e("finally","thread is end");
                    }
                }
            }
        }).start();
    }
}
