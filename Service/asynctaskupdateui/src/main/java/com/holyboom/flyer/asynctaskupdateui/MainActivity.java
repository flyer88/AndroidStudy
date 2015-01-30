package com.holyboom.flyer.asynctaskupdateui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;




public class MainActivity extends ActionBarActivity {

    TextView textView;
    Button button;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.text_view);
        button = (Button)findViewById(R.id.button);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建实例，调用execute()触发异步更新程序
                ProgressBarAsyncTask progressBarAsyncTask = new ProgressBarAsyncTask(textView,progressBar);
                progressBarAsyncTask.execute();
            }
        });


    }
}
