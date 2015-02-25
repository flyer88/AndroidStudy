package com.holyboom.flyer.tel;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.holyboom.flyer.tel.MaterialUi.FloatingActionBar.FloatingActionButton;


public class MainActivity extends ActionBarActivity {

    FloatingActionButton FAB;
    Button tel_1,tel_2,tel_3,tel_4,tel_5,tel_6,tel_7,tel_8,tel_9,tel_star,tel_0,tel_jin;
    EditText contactNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPhone();
        tel_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"tel_1",Toast.LENGTH_SHORT).show();
            }
        });
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Fuck you", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void initPhone(){
        int MaterialGreen = getResources().getColor(R.color.green_dark);

        contactNumber  = (EditText) findViewById(R.id.tel_number);
        tel_1 = (Button) findViewById(R.id.tel_1);
        tel_2 = (Button) findViewById(R.id.tel_2);
        tel_3 = (Button) findViewById(R.id.tel_3);
        tel_4 = (Button) findViewById(R.id.tel_4);
        tel_5 = (Button) findViewById(R.id.tel_5);
        tel_6 = (Button) findViewById(R.id.tel_6);
        tel_7 = (Button) findViewById(R.id.tel_7);
        tel_8 = (Button) findViewById(R.id.tel_8);
        tel_9 = (Button) findViewById(R.id.tel_9);
        tel_star = (Button) findViewById(R.id.tel_star);
        tel_0 = (Button) findViewById(R.id.tel_0);
        tel_jin = (Button) findViewById(R.id.tel_jin);
        FAB = (FloatingActionButton) findViewById(R.id.fab_button_call);
        setThemeColor(MaterialGreen);
        FAB.setDrawable(getResources().getDrawable(R.drawable.call_fab));
    }

    public void setThemeColor(int color){
        contactNumber.setTextColor(color);
        tel_1.setTextColor(color);
        tel_2.setTextColor(color);
        tel_3.setTextColor(color);
        tel_4.setTextColor(color);
        tel_5.setTextColor(color);
        tel_6.setTextColor(color);
        tel_7.setTextColor(color);
        tel_8.setTextColor(color);
        tel_9.setTextColor(color);
        tel_0.setTextColor(color);
        tel_star.setTextColor(color);
        tel_jin.setTextColor(color);
        FAB.setColor(color);
    }
}
