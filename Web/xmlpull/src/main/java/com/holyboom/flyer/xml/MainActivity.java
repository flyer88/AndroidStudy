package com.holyboom.flyer.xml;

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
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    public static final int SHOW_RESPONSE = 1;
    TextView id,name,version;
    Button requestButton;
    App appTextView;

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case SHOW_RESPONSE:
                    appTextView = (App)msg.obj;
                    id.setText(appTextView.id);
                    name.setText(appTextView.name);
                    version.setText(appTextView.version);
                    break;
                default:break;
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
        requestButton = (Button)findViewById(R.id.send_request);
        requestButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.send_request){
            sendRequestWithHttpClient();
        }
    }

    public void sendRequestWithHttpClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://192.168.1.100/flyer/get_data.xml");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200){
                        /*请求成功*/
                        HttpEntity httpEntity = httpResponse.getEntity();
                        String response = EntityUtils.toString(httpEntity, "utf-8");
                        parseXMLWithPull(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Thread", "Execption");
                }
            }
        }).start();
    }

    public void parseXMLWithPull(String xmlData){
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            App app = new App();
            while(eventType != XmlPullParser.END_DOCUMENT){
                String nodeName = xmlPullParser.getName();
                switch (eventType){
                    //解析某个节点
                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodeName)) {
                            app.setId(xmlPullParser.nextText());
                        } else if ("name".equals(nodeName)) {
                            app.setName(xmlPullParser.nextText());
                        } else if ("version".equals(nodeName)) {
                            app.setVersion(xmlPullParser.nextText());
                        }
                        break;
                    }
                    //完成某个节点解析
                    case XmlPullParser.END_TAG: {
                        if ("app".equals(nodeName)) {
                            Log.e("id: ",app.id+"");
                            Log.e("name: ",app.name+"");
                            Log.e("id: ",app.version+"");
                            Message message = new Message();
                            message.what = SHOW_RESPONSE;
                            message.obj = app;
                            handler.sendMessage(message);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("parseXMLWithPull","Exception");
        }
    }
}
