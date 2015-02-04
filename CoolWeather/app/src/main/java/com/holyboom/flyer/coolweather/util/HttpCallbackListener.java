package com.holyboom.flyer.coolweather.util;

/**
 * Created by flyer on 15/2/4.
 */
public interface HttpCallbackListener{
    public void onFinish(String response);
    public void onExecption(Exception e);
}
