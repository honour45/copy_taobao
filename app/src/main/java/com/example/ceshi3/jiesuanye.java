package com.example.ceshi3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ceshi3.fragment.Fragment_zhuye3;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.security.AccessController.getContext;

public class jiesuanye extends AppCompatActivity {
private Button bt1;
private TextView tt;
    shangpinsql a4sql;
    String aaa;
    String bbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiesuanye);
        tt=findViewById(R.id.jiesuanye_tt1);
        bt1=findViewById(R.id.jiesuanye_bt);
        a4sql = new shangpinsql(this);
        SQLiteDatabase db = a4sql.getReadableDatabase();
        Cursor cursor = db.query("sp", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String a1 = cursor.getString(cursor.getColumnIndex("mingzi"));
            String a2 = cursor.getString(cursor.getColumnIndex("jiage"));
            aaa+=a1+",";
            bbb+=a2+",";
        }
        cursor.close();
        db.close();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String a=simpleDateFormat.format(date);
        tt.setText("您于"+a+"购买了"+aaa);
        /*
        通知栏消息
         */
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(jiesuanye.this);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
            //获取当前时间
            Date date = new Date(System.currentTimeMillis());
            String a=simpleDateFormat.format(date);
            mBuilder .setContentTitle("您的购买物品")  //设置标题
                    .setContentText("您于"+a+"购买"+aaa) //内容
                    .setWhen(System.currentTimeMillis())  //设置时间
                    .setSmallIcon(R.drawable.taobao1)  //设置小图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.taobao1));   //设置大图标
            manager.notify(10, mBuilder.build());
                Intent intent = new Intent(jiesuanye.this, MainActivity.class);
                intent.putExtra("id",3);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}