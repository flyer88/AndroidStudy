package com.holyboom.flyer.asynctaskupdateui;

/**
 * Created by flyer on 15/1/30.
 */
public class NetOperator {
    /**
     * 模拟网络
     */
    public void operate(){
        try{
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
