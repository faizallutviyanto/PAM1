package com.ngampus.cafetaria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.ngampus.cafetaria.Constants.PILIHAN;
import static com.ngampus.cafetaria.Constants.SPESIAL;
import static com.ngampus.cafetaria.Constants.TABLE_NAME;
import static com.ngampus.cafetaria.Constants.UKURAN;

/**
 * Created by Faizal Lutviyanto on 7/30/2015.
 */
public class DataMinuman extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cafetaria.db";
    private static final int DATABASE_VERSION = 4;

    DataMinuman(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + UKURAN + "TEXT NOT NULL," + PILIHAN + "TEXT NOT NULL,"+  SPESIAL + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }
}
