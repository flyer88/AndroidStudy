package com.holyboom.flyer.httputil;

/**
 * Created by flyer on 15/2/3.
 */
public interface HttpCallbackListener {

    void onFinish(String response);
    void onError(Exception e);

}
