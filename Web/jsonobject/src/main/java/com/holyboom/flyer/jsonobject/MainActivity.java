package com.holyboom.flyer.jsonobject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    TextView id,name,version;
    Button getJson;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.obj!=null) {
                Game game = (Game) msg.obj;
                id.append(game.id);
                name.append(game.name);
                version.append(game.version);
            }
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
        }
    }

    public void sendRequestWithHttpClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://192.168.1.100/flyer/get_data.json");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if ((httpResponse.getStatusLine().getStatusCode()) ==200){
                        HttpEntity httpEntity = httpResponse.getEntity();
                        String response = EntityUtils.toString(httpEntity,"utf-8");
                        parseJSONWithJSONObject(response);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("sendRequestWithHttpClient","Exception");
                }
            }
        }).start();
    }

    public void parseJSONWithJSONObject(String jsonData){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Game game = new Game();
                game.id = jsonObject.getString("id");
                game.name = jsonObject.getString("name");
                game.version = jsonObject.getString("version");
                Message message = new Message();
                message.obj = game;
                handler.sendMessage(message);
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("parseJSONWithJSONObject","Exception");
        }
    }

}
