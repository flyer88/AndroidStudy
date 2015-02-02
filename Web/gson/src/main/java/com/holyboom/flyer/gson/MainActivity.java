package com.holyboom.flyer.gson;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{


    TextView id,version,name;
    Button getJson;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            App app = (App)msg.obj;
            id.append(app.id);
            name.append(app.name);
            version.append(app.version);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (TextView)findViewById(R.id.id);
        name = (TextView)findViewById(R.id.name);
        version = (TextView)findViewById(R.id.version);
        getJson = (Button)findViewById(R.id.get_json);
        getJson.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.get_json){
            sendRequestWithHttpClient();
            Log.e("onClick","get json");
        }
    }

    public void sendRequestWithHttpClient(){
        Log.e("sendRequestWithHttpClient","before thread");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://192.168.1.100/flyer/get_data.json");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    Log.e("sendRequestWithHttpClient","httpClient.execute");
                    if ((httpResponse.getStatusLine().getStatusCode()) == 200){
                        Log.e("sendRequestWithHttpClient","status code 200");
                        HttpEntity httpEntity = httpResponse.getEntity();
                        String response = EntityUtils.toString(httpEntity,"utf-8");
                        parseJSONWithGSON(response);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("sendRequest","Exception");
                }

            }
        }).start();
    }
    public void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for (App app : appList){
            Log.e("parseJSONWithGSON","id is "+app.id);
            Message message = new Message();
            message.obj = app;
            handler.sendMessage(message);
        }
    }
}
