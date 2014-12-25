package com.haohuotong.other;

import android.database.sqlite.SQLiteDatabase;

public abstract class DataBase {
    public abstract DataBase open();
    public abstract void close();
    public abstract SQLiteDatabase getWritableDatabase();
    public abstract SQLiteDatabase getReadableDatabase();
}