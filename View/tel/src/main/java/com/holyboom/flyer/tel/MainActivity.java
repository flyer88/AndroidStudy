package com.holyboom.flyer.tel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.andexert.library.RippleView;
import com.holyboom.flyer.tel.MaterialUi.FloatingActionBar.FloatingActionButton;


public class MainActivity extends ActionBarActivity {

    FloatingActionButton FAB;
    Button tel_1,tel_2,tel_3,tel_4,tel_5,tel_6,tel_7,tel_8,tel_9,tel_star,tel_0,tel_jin;
    ImageButton telBack;
    EditText editTextNumber;
    RippleView rippleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPhone();
        inPutPhoneNumber();
        backInPutPhoneNumber();
        call();
    }
    public void initPhone(){
        int MaterialGreen = getResources().getColor(R.color.green_dark);
        editTextNumber  = (EditText) findViewById(R.id.tel_number);
        telBack = (ImageButton) findViewById(R.id.tel_back);
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
        rippleView = (RippleView) findViewById(R.id.ripple_view);
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
        telBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = KeyEvent.ACTION_DOWN;
                //code:删除，其他code也可以，例如 code = 0
                int code = KeyEvent.KEYCODE_DEL;
                KeyEvent event = new KeyEvent(action, code);
                editTextNumber.onKeyDown(KeyEvent.KEYCODE_DEL, event); //抛给系统处理了
            }
        });
    }
}
