package com.example.ceshi3.bean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Zhucesqlhelp extends SQLiteOpenHelper {

        public Zhucesqlhelp(Context context) {
            super(context, "zhuce.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table information(_id integer primary key autoincrement,yonghuming VARCHAR(20),mima VARCHAR(20),nicheng VARCHAR(20),dizhi VARCHAR(20))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
}
