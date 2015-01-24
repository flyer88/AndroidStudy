package com.holyboom.contentprovider;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class MainActivity extends ActionBarActivity {

    EditText contentEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentEdit = (EditText)findViewById(R.id.content);

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
}
