package com.example.ceshi3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class first_yindaoye extends AppCompatActivity {
    private int i = 0;
    private int TIME = 1000;
    private TextView textView;

//    Timer timer = new Timer();
//    TimerTask task = new TimerTask() {
//        @Override
//        public void run() {
//            // 需要做的事:发送消息
//            Message message = new Message();
//            message.what = 1;
//            handler.sendMessage(message);
//        }
//    };

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
//                Toast.makeText(first_yindaoye.this,"成功",Toast.LENGTH_SHORT);
                /*
                https://www.jianshu.com/p/3b2ac6201b33
                深入理解Android SharedPreferences的commit与apply
                 */
                SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                String str1 = preferences.getString("decide","");
                if (str1=="a"){
                    Log.v("preferences1",str1);
                    Intent intent=new Intent(first_yindaoye.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Log.v("preferences2",str1);
                    Intent intent=new Intent(first_yindaoye.this,second_yindaoye.class);
                    startActivity(intent);
                }
                finish();
            }
            super.handleMessage(msg);
        };
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_yindaoye);
        textView=(TextView)findViewById(R.id.first_yindaoye_text);
//        timer.schedule(task, 3000);
        startrun();
        nowrun();
    }

    //倒计时CountDownTimer
    //每过1000毫秒执行一次onTick
    private void startrun(){
        CountDownTimer timer2 = new CountDownTimer(1000 * 3+1080, 1000) {
            //第一个参数表示总时间，第二个参数表示间隔时间。意思就是每隔一秒会回调一次方法onTick
            //https://blog.csdn.net/u010898329/article/details/75005814解决 时间不准，没有0的思路
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(((int)millisUntilFinished/1000-1)+"秒后进入");
//                Log.v("millisUntilFinished",millisUntilFinished/1000+"");
                Log.d("CJT", "onTick----l=="+millisUntilFinished);
            }
            @Override
                public void onFinish() {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    //倒计时完成执行onFinish
            }
        };
        timer2.start();
    }

    private void nowrun(){
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(first_yindaoye.this,MainActivity.class);
                startActivity(intent);
                onDestroy();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


