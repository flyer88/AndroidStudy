package com.holyboom.flyer.xmlsax;

import android.os.Bundle;
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
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    TextView response;
    Button request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        response = (TextView)findViewById(R.id.response);
        request = (Button)findViewById(R.id.request);
        request.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.request){
            sendRequestWithHttpClien();
        }
    }

    private void sendRequestWithHttpClien(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://192.168.1.100/flyer/get_data.xml");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if ((httpResponse.getStatusLine().getStatusCode()) == 200){
                        HttpEntity httpEntity = httpResponse.getEntity();
                        String response = EntityUtils.toString(httpEntity,"utf-8");
                        parseXMLWithSAX(response);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("sendRequestWithHttpClient","Exception");
                }
            }
        }).start();
    }

    public void parseXMLWithSAX(String xmlData){
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
            MyHandler handler = new MyHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        }catch (Exception e){
            e.printStackTrace();
            Log.e("parseXMLWithSAX","Exception");
        }
    }
}
