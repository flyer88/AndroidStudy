package com.holyboom.flyer.tel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.holyboom.flyer.tel.MaterialUi.FloatingActionBar.FloatingActionButton;
import com.holyboom.flyer.tel.MaterialUi.Ripple.RippleView;


public class MainActivity extends ActionBarActivity {

    FloatingActionButton FAB;
    RippleView tel_1,tel_2,tel_3,tel_4,tel_5,tel_6,tel_7,tel_8,tel_9,tel_star,tel_0,tel_jin;
    EditText editTextNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPhone();
        inPutPhoneNumber();
        call();
    }
    public void initPhone(){
        int MaterialGreen = getResources().getColor(R.color.green_dark);

        editTextNumber  = (EditText) findViewById(R.id.tel_number);
        tel_1 = (RippleView) findViewById(R.id.tel_1);
        tel_2 = (RippleView) findViewById(R.id.tel_2);
        tel_3 = (RippleView) findViewById(R.id.tel_3);
        tel_4 = (RippleView) findViewById(R.id.tel_4);
        tel_5 = (RippleView) findViewById(R.id.tel_5);
        tel_6 = (RippleView) findViewById(R.id.tel_6);
        tel_7 = (RippleView) findViewById(R.id.tel_7);
        tel_8 = (RippleView) findViewById(R.id.tel_8);
        tel_9 = (RippleView) findViewById(R.id.tel_9);
        tel_star = (RippleView) findViewById(R.id.tel_star);
        tel_0 = (RippleView) findViewById(R.id.tel_0);
        tel_jin = (RippleView) findViewById(R.id.tel_jin);
        FAB = (FloatingActionButton) findViewById(R.id.fab_button_call);
        setThemeColor(MaterialGreen);
        FAB.setDrawable(getResources().getDrawable(R.drawable.call_fab));
    }

    public void setThemeColor(int color){
        editTextNumber.setTextColor(color);
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

    public void inPutPhoneNumber(){
        tel_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append(1+"");
            }
        });
        tel_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append(2+"");
            }
        });
        tel_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append(3+"");
            }
        });
        tel_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append(4+"");
            }
        });
        tel_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append(5+"");
            }
        });
        tel_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append(6+"");
            }
        });
        tel_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append(7+"");
            }
        });
        tel_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append(8+"");
            }
        });
        tel_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append(9+"");
            }
        });
        tel_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append("*");
            }
        });
        tel_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append(0+"");
            }
        });
        tel_jin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumber.append("#");
            }
        });
    }

    public void call(){
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = editTextNumber.getText().toString();
                Intent phoneIntent = new Intent(
                        "android.intent.action.CALL", Uri.parse("tel:"
                        + inputStr));

                // 启动
                startActivity(phoneIntent);
            }
        });
    }

    public void backInPutPhoneNumber(){

    }
}
