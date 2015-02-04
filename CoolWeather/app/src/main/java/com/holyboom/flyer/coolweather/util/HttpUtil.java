package com.holyboom.flyer.coolweather.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by flyer on 15/2/4.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address,final  HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url = new URL(address);
                    httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    InputStream in = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line = null;
                    StringBuilder response = new StringBuilder();
                    while((line = reader.readLine())!=null){
                        response.append(line);
                    }
                    if (listener!=null){
                        listener.onFinish(response.toString());
                    }
                }catch (Exception e){
                    if (listener!=null){
                        listener.onExecption(e);
                    }
                }finally {
                    if (httpURLConnection !=null){
                        httpURLConnection.disconnect();
                    }
                }
            }
        }).start();
    }
}
