package com.holyboom.flyer.asynctaskupdateui;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by flyer on 15/1/30.
 */
public class ProgressBarAsyncTask extends AsyncTask<Integer,Integer,String> {
    TextView textView;
    ProgressBar progressBar;

    ProgressBarAsyncTask(TextView textView, ProgressBar progressBar) {
        this.textView = textView;
        this.progressBar = progressBar;
    }

    /**
     * 在后台任务开始执行的时候执行，常用于初始化ui
     * 此处初始化textView
     */
    @Override
    protected void onPreExecute() {
        textView.setText("开始异步程序！！");
    }

    /**
     * 此方法中主要处理异步操作，在此处处理所有耗时任务
     * Integer为AsyncTask的第一个参数,此处并无用到（该值由execute传入）
     * 该方法不运行咋UI线程中，无法对UI进行操作
     * 如需要更新UI需要调用publishProgress()方法触发onProgressUpdate()对UI进行操作
     * @param params
     * @return 返回值String为AsyncTask的第三个参数
     */
    @Override
    protected String doInBackground(Integer... params) {
        NetOperator netOperator = new NetOperator();
        int j = 0;
        for (int i = 0; i <= 100; i+=10) {
            netOperator.operate();
                if((i % 10) ==0) {
                    publishProgress(i);
                }
            j = i;
        }
        return j + "";
    }

    /**
     * Integer 为AsyncTask 的第二个参数
     * 当doInBackground 中调用publishProgress()时会触发此方法
     * 刚方法是在UI线程中的，因此可以更新UI
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        progressBar.setProgress(value);
        Log.e("onProgressUpdate",value+"");
    }

    /**
     * 此处String为AsyncTask的地三个参数
     * 同时也是doInBackground的返回值
     * 后台任务完成后(doInBackground)，获取返回值，对UI更新
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        textView.setText("异步操作结束"+s);
    }
}
