package com.holyboom.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    Button saveData;
    Button restoreData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveData = (Button)findViewById(R.id.save_data);
        restoreData = (Button)findViewById(R.id.restore_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","flyer");
                editor.putInt("age",28);
                editor.putBoolean("married",false);
                editor.commit();
            }
        });

        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref= getSharedPreferences("data",MODE_PRIVATE);
                String name = pref.getString("name","");
                int age =pref.getInt("age",0);
                boolean married = pref.getBoolean("married",false);
                Log.e("sharedpreferences","name is "+name);
                Log.e("sharedpreferences","age is "+age);
                Log.e("sharedpreferences","married is "+married);
            }
        });
    }

}
