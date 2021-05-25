package com.example.ceshi3;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class closeallactvity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

//    @Override
//    protected void onDestory() {
//        super.onDestory();
//    }


    public void exit(){//将所有的Activity全部销毁
        finish();
    }
}
