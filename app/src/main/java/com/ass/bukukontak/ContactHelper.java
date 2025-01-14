package com.ass.bukukontak;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactHelper extends SQLiteOpenHelper {

    private static final String DATABASE = "contact_book";
    private static final int VERSION = 2;

    public ContactHelper(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table contacts("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT," +
                " number TEXT," +
                " email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("drop table if exists contacts");

    }

    public void insertData(String name, String number, String email) {
        SQLiteDatabase database =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("number", number);
        contentValues.put("email", email);
        database.insert("contacts",null, contentValues);
    }
}
