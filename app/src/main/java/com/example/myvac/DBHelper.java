package com.example.myvac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users.db";
    public static final int DATABASE_VERSION = 1;
    public  static final String TABLE_NAME = "all_users";
    public  static final String FIRST_NAME = "firstName";
    public  static final String LAST_NAME = "lastName";
    public  static final String ID = "id";
    public  static final String PASS = "password";
    public  static final String PHONE = "phone";
    public  static final String EMAIL = "email";


    public  static final String TABLE_NAME1 = "children";
    public  static final String PARENT_ID = "id";
    public  static final String DATE_OF_BIRTH = "dateOfBirth";
    public  static final String FULL_NAME = "fullName";
    public  static final String WEIGHT = "weight";
    public  static final String HEIGHT = "height";
    public  static final String HEAD_SIZE = "headSize";
    public  static final String DOCTOR_NAME= "doctorName";

    public String SQL_Create="",SQL_Delete="";
    public String SQL_Create1="",SQL_Delete1="";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        SQL_Create="CREATE TABLE " +TABLE_NAME+" (";
        SQL_Create+=FIRST_NAME+" TEXT, ";
        SQL_Create+=LAST_NAME+" TEXT, ";
        SQL_Create+=ID+" TEXT, ";
        SQL_Create+=PASS+" TEXT, ";
        SQL_Create+=PHONE+" TEXT, ";
        SQL_Create+=EMAIL+" TEXT);";

        sqLiteDatabase.execSQL(SQL_Create);

        SQL_Create1="CREATE TABLE " +TABLE_NAME1+" (";
        SQL_Create1+=PARENT_ID+" TEXT, ";
        SQL_Create1+=DATE_OF_BIRTH+" TEXT, ";
        SQL_Create1+=FULL_NAME+" TEXT, ";
        SQL_Create1+=WEIGHT+" TEXT, ";
        SQL_Create1+=HEIGHT+" TEXT, ";
        SQL_Create1+=HEAD_SIZE+" TEXT, ";
        SQL_Create1+=DOCTOR_NAME+" TEXT);";

        sqLiteDatabase.execSQL(SQL_Create1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        SQL_Delete="DROP TABLE IF EXISTS "+TABLE_NAME;
        sqLiteDatabase.execSQL(SQL_Delete);

        SQL_Delete1="DROP TABLE IF EXISTS "+TABLE_NAME1;
        sqLiteDatabase.execSQL(SQL_Delete1);

        onCreate(sqLiteDatabase);
    }
}
