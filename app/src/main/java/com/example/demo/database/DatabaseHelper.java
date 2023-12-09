package com.example.demo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "StudentDemo.db";

    // database version
    static final int DB_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


//    "CREATE TABE Student(ID INTEGER, Address TEXT, )"
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StudentEntry.TABLE_NAME + " (" +
                    StudentEntry._ID + " INTEGER PRIMARY KEY," +
                    StudentEntry.firstName + " TEXT," +
                    StudentEntry.lastName + " TEXT," +
                    StudentEntry.gender + " TEXT," +
                    StudentEntry.dob + " TEXT," +
                    StudentEntry.age + " TEXT," +
                    StudentEntry.address + " TEXT," +
                    StudentEntry.mobileNo + " TEXT," +
                    StudentEntry.emailId + " TEXT," +
                    StudentEntry.course + " TEXT," +
                    StudentEntry.subject + " TEXT," +
                    StudentEntry.idCard + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
