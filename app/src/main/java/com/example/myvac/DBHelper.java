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


    public  static final String TABLE_NAME2 = "children";
    public  static final String PARENT_ID = "parentID";
    public  static final String CHILD_NAME = "fullName";
    public  static final String DATE_OF_BIRTH = "date";
    public  static final String HEAD_DIAMETER = "headDiameter";
    public  static final String WEIGHT = "weight";
    public  static final String HEIGHT = "height";
    public  static final String DOC_NAME = "docName";
    public  static final String CHILD_ID = "childID";

    public  static final String TABLE_NAME3 = "doctors";
    public  static final String DOCTOR_NAME = "doctorName";
    public  static final String DOCTOR_ID = "docID";
    public  static final String DOC_PASSWORD = "docPass";

    public  static final String TABLE_NAME4 = "vaccine";
    public  static final String VAC_CHILD_ID = "ChildID";
    public  static final String DTW_COUGH= "cough";
    public  static final String HAEMOPHILUS_INFLUENZAE_TYPE_B= "HITypeB";
    public  static final String POLIO= "polio";
    public  static final String GERMAN_MEASLES= "German_measles";
    public  static final String CHICKEN_POX= "chicken_pox";
    public  static final String PCV= "pcv";
    public  static final String HEPATITIS_B= "Hepatitis_B";
    public  static final String HEPATITIS_A= "Hepatitis_A";
    public  static final String ROTAVIRUS= "rotavirus";

    public String SQL_Create="",SQL_Delete="";
    public String SQL2_Create="",SQL2_Delete="";
    public String SQL3_Create="",SQL3_Delete="";
    public String SQL4_Create="",SQL4_Delete="";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //table, contains all the users
        SQL_Create="CREATE TABLE " +TABLE_NAME+" (";
        SQL_Create+=FIRST_NAME+" TEXT, ";
        SQL_Create+=LAST_NAME+" TEXT, ";
        SQL_Create+=ID+" TEXT, ";
        SQL_Create+=PASS+" TEXT, ";
        SQL_Create+=PHONE+" TEXT, ";
        SQL_Create+=EMAIL+" TEXT);";

        sqLiteDatabase.execSQL(SQL_Create);

        //table 2, contains children(i know it sounds bad but i cant think of how to make it sound normal)
        SQL2_Create = "CREATE TABLE " +TABLE_NAME2+" (";
        SQL2_Create+=PARENT_ID+" TEXT, ";
        SQL2_Create+=CHILD_NAME+" TEXT, ";
        SQL2_Create+=DATE_OF_BIRTH+" TEXT, ";
        SQL2_Create+=HEAD_DIAMETER+" TEXT, ";
        SQL2_Create+=WEIGHT+" TEXT, ";
        SQL2_Create+=HEIGHT+" TEXT, ";
        SQL2_Create+=DOC_NAME+" TEXT, ";
        SQL2_Create+=CHILD_ID+" TEXT);";

        sqLiteDatabase.execSQL(SQL2_Create);

        SQL3_Create = "CREATE TABLE " +TABLE_NAME3+" (";
        SQL3_Create+=DOCTOR_NAME+" TEXT, ";
        SQL3_Create+=DOCTOR_ID+" TEXT, ";
        SQL3_Create+=DOC_PASSWORD+" TEXT);";

        sqLiteDatabase.execSQL(SQL3_Create);

        SQL4_Create = "CREATE TABLE " +TABLE_NAME4+" (";
        SQL4_Create+=VAC_CHILD_ID+" TEXT, ";
        SQL4_Create+=DTW_COUGH+" BIT, ";
        SQL4_Create+=HAEMOPHILUS_INFLUENZAE_TYPE_B+" BIT, ";
        SQL4_Create+=POLIO+" BIT, ";
        SQL4_Create+=GERMAN_MEASLES+" BIT, ";
        SQL4_Create+=CHICKEN_POX+" BIT, ";
        SQL4_Create+=PCV+" BIT, ";
        SQL4_Create+=HEPATITIS_A+" BIT, ";
        SQL4_Create+=HEPATITIS_B+" BIT, ";
        SQL4_Create+=ROTAVIRUS+" BIT);";

        sqLiteDatabase.execSQL(SQL4_Create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        SQL_Delete="DROP TABLE IF EXISTS "+TABLE_NAME;
        sqLiteDatabase.execSQL(SQL_Delete);

        SQL2_Delete="DROP TABLE IF EXISTS "+TABLE_NAME2;
        sqLiteDatabase.execSQL(SQL2_Delete);

        SQL3_Delete="DROP TABLE IF EXISTS "+TABLE_NAME3;
        sqLiteDatabase.execSQL(SQL3_Delete);

        SQL4_Delete="DROP TABLE IF EXISTS "+TABLE_NAME4;
        sqLiteDatabase.execSQL(SQL4_Delete);

        onCreate(sqLiteDatabase);
    }
}
