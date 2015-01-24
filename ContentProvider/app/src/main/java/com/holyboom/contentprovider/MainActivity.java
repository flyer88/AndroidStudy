package com.holyboom.contentprovider;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends ActionBarActivity {

    EditText contentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentEdit = (EditText)findViewById(R.id.content);
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)){
            contentEdit.setText(inputText);
            contentEdit.setSelection(inputText.length());
            Toast.makeText(MainActivity.this,"restoring succeed",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save(contentEdit.getText().toString());
    }

    public void save(String contentString){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(contentString);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (writer!=null){
                    writer.close();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!=null){
                content.append(line);
            }
        }catch (Exception e){

        }finally {
            if (reader!=null){
                try {
                    reader.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
