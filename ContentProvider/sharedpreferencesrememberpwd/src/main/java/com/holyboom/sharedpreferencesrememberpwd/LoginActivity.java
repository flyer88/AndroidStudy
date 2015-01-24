package com.holyboom.sharedpreferencesrememberpwd;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;

public class LoginActivity extends BaseActivity {

    EditText accountEdit;
    EditText passwordEdit;
    Button login;
    CheckBox rememberPwd;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // pref = getSharedPreferences("data",MODE_PRIVATE);
        pref = PreferenceManager.getDefaultSharedPreferences(this);


        accountEdit = (EditText)findViewById(R.id.account);
        passwordEdit = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        rememberPwd = (CheckBox)findViewById(R.id.remember_pwd);

        boolean isRemember = pref.getBoolean("rememberPassword",false);
        if (isRemember){
            String accoutn = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(accoutn);
            passwordEdit.setText(password);
            rememberPwd.setChecked(true);
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (account.equals("flyer")&&password.equals("flyer123")){

                    editor = pref.edit();
                    if (rememberPwd.isChecked()){
                        editor.putString("account",account);
                        editor.putString("password",password);
                        editor.putBoolean("rememberPassword",true);
                    }else{
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "password or account is invalid!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
