package com.example.ceshi3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class shangpinsql extends SQLiteOpenHelper {
    public shangpinsql(@Nullable Context context) {
        super(context, "sp1", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table sp(_id integer primary key autoincrement,mingzi varchar(20),jiage varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
