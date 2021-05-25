package com.example.ceshi3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ceshi3.bean.Zhucesqlhelp;
import com.example.ceshi3.fragment.Fragment_zhuye4;

public class login extends AppCompatActivity {
private TextView login_tt2;
private EditText login_et1,login_et2;
private Button login_bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_tt2=(TextView)findViewById(R.id.login_tt2);
        login_et1=(EditText)findViewById(R.id.login_et1);
        login_et2=(EditText)findViewById(R.id.login_et2);
        login_bt1=(Button)findViewById(R.id.login_bt1);



        login_tt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, zhuce.class));

            }
        });


        login_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("gerenxinxi",MODE_PRIVATE);
                String yonghu=sp.getString("yonghuming","");
                String mm=sp.getString("mima","");

                String a=login_et1.getText().toString();
                String b=login_et2.getText().toString();
                //登录
                if (a.equals(yonghu)&&b.equals(mm)){
                    startActivity(new Intent(login.this, MainActivity.class));
                }else {
                    Toast.makeText(login.this,"登陆失败",Toast.LENGTH_SHORT);
                }
            }
        });

    }


}
