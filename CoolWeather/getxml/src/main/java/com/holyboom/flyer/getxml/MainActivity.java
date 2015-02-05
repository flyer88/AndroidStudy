package com.holyboom.flyer.getxml;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{


    Button sendRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequest = (Button)findViewById(R.id.send_request);
        sendRequest.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.send_request){
            sendRequestWithHttpClient();
        }
    }


    public void sendRequestWithHttpClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.e("sendRequestWithHttpClient","start run!");
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://flash.weather.com.cn/wmaps/xml/china.xml");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if ((httpResponse.getStatusLine().getStatusCode()) == 200){
                        HttpEntity httpEntity = httpResponse.getEntity();
                        String response = EntityUtils.toString(httpEntity,"utf-8");
                       // Log.e("sendRequestWithHttpClient","response is "+response);
                        handleProvincesResponse(response);
                      //  Log.e("sendRequestWithHttpClient","finish handleProvinceResponse!");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void handleProvincesResponse(String response){
            try{
                Log.e("handleProvincesResponse","start handle");
                XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setInput(new StringReader(response));
                int eventType = xmlPullParser.getEventType();
                while(eventType != xmlPullParser.END_DOCUMENT){
                    String nodeName = xmlPullParser.getName();
                    Log.e("handleProvincesResponse","node name is "+nodeName);
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            if ("city".equals(nodeName)){
                                Log.e("city","City name is  "+xmlPullParser.getAttributeValue(0));
                                Log.e("city","City pyName is "+xmlPullParser.getAttributeValue(1));
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            Log.e("xmlPullParse","End of xml");
                            break;
                        default:
                            break;
                    }
                    eventType = xmlPullParser.next();
                }
            }catch (Exception e){
                Log.e("handleProvincesResponse","handle exception!!");
            }
    }
}
