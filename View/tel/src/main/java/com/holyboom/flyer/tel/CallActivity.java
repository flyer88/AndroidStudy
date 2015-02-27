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
import com.holyboom.flyer.tel.Contact.ContactActivity;
import com.holyboom.flyer.tel.MaterialUi.FloatingActionBar.FloatingActionButton;
import com.holyboom.flyer.tel.Message.MessageActivity;


public class CallActivity extends ActionBarActivity {

    FloatingActionButton callFab,contactFab,messageFab;
    Button tel_1,tel_2,tel_3,tel_4,tel_5,tel_6,tel_7,tel_8,tel_9,tel_star,tel_0,tel_jin;
    ImageButton telBack;
    EditText editTextNumber;
    RippleView rippleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        initPhone();
        inPutPhoneNumber();
        backInPutPhoneNumber();
        call();
        contact();
        message();
    }
    public void initPhone(){
        int MaterialGreen = getResources().getColor(R.color.green_light);
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
        callFab = (FloatingActionButton) findViewById(R.id.fab_button_call);
        contactFab = (FloatingActionButton) findViewById(R.id.fab_button_contact);
        messageFab = (FloatingActionButton) findViewById(R.id.fab_button_message);
        callFab.setDrawable(getResources().getDrawable(R.drawable.call_fab));
        contactFab.setDrawable(getResources().getDrawable(R.drawable.contact_fab));
        messageFab.setDrawable(getResources().getDrawable(R.drawable.message_fab));
        //rippleView = (RippleView) findViewById(R.id.ripple_view);
        setThemeColor(MaterialGreen);
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
        callFab.setColor(color);
        contactFab.setColor(color);
        messageFab.setColor(color);
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
        callFab.setOnClickListener(new View.OnClickListener() {
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
    public void contact(){
        contactFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CallActivity.this,ContactActivity.class);
                startActivity(intent);
                //第一个参数为启动时动画效果，第二个参数为退出时动画效果
                overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_fade_out);
            }
        });
    }
    public void message(){
        messageFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallActivity.this, MessageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_fade_out);
            }
        });
    }
}
