package com.example.multiscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {

    public static final String DB_NAME = "ORDERS.DB";
    public static final String TABLE_NAME = "ORDERS_TABLE";
    public static final String COL1 = "ID";
    public static final String COL2 = "ORDER1";
    public static final String COL3 = "ORDER2";
    public static final String COL4 = "ORDER3";

    public database(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY, ORDER1 TEXT, ORDER2 TEXT, ORDER3 TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String ORDER1, String ORDER2, String ORDER3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, ORDER1);
        contentValues.put(COL3, ORDER2);
        contentValues.put(COL4, ORDER3);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor showData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;

    }
}
