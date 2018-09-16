package com.zamee.gicbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DatabaseAdapter extends SQLiteOpenHelper {

    public static final String DatabaseName = "User.db";
    public static final String TableName = "Userinfo";
    public static final String Col1 = "ID";
    public static final String Col2 = "NAME";
    public static final String Col3 = "Email";
    public static final String Col4 = "PhoneNumber";

    String id;

    public DatabaseAdapter(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TableName + "" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,Email TEXT, PhoneNumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String email, String phone_number){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2,name);
        contentValues.put(Col3,email);
        contentValues.put(Col4,phone_number);

        long result = sqLiteDatabase.insert(TableName, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public List<Datamodel> getData(){
        List<Datamodel> datamodels = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Userinfo",null);
        Datamodel datamodel;
        if (cursor.moveToFirst()){
            do {
                datamodel = new Datamodel();
                datamodel.setName(cursor.getString(cursor.getColumnIndex(Col2)));
                datamodel.setEmail(cursor.getString(cursor.getColumnIndex(Col3)));
                datamodel.setPhoneNumber(cursor.getString(cursor.getColumnIndex(Col4)));
                datamodels.add(datamodel);
            }while (cursor.moveToNext());
        }
        return datamodels;
    }
}
