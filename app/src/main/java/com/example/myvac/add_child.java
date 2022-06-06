package com.example.myvac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class add_child extends AppCompatActivity {

    String  docName;
    final Calendar myCalendar= Calendar.getInstance();
    SQLiteDatabase sqdb;
    DBHelper my_db;
    String errorMessage;

    EditText etDate, etFullName, etHeight, etWeight, etHeadDiameter, etParentId, etChildId;
    Boolean flag = true;
    String[] info = new String[8];
    ContentValues cv = new ContentValues();
    ContentValues cv2 = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);


        Intent intent = getIntent();
        docName = intent.getStringExtra("docName");
        my_db = new DBHelper(this);
        sqdb=my_db.getWritableDatabase();
        sqdb.close();
        //linking
        etChildId = findViewById(R.id.childId);
        etDate=(EditText) findViewById(R.id.edDate);
        etFullName = findViewById(R.id.etFullName);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        etHeadDiameter = findViewById(R.id.etHeadDiameter);
        etParentId = findViewById(R.id.prID);



        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(add_child.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat);
        etDate.setText(dateFormat.format(myCalendar.getTime()));
    }

    public void addChild(View view) {
        info[0] = etParentId.getText().toString();
        info[1] = etFullName.getText().toString();
        info[2] = etDate.getText().toString();
        info[3] = etHeadDiameter.getText().toString();
        info[4] = etWeight.getText().toString();
        info[5] = etHeight.getText().toString();
        info[6] = docName;
        info[7] = etChildId.getText().toString();




        sqdb=my_db.getWritableDatabase();
        Cursor c = sqdb.query(DBHelper.TABLE_NAME2,null,null,null,null,null,null);
        int col1 = c.getColumnIndex(DBHelper.PARENT_ID);
        int col2 = c.getColumnIndex(DBHelper.CHILD_NAME);

        c.moveToFirst();
        while(!c.isAfterLast()&&flag)
        {
            String parentID = c.getString(col1);
            String childName = c.getString(col2);

            if (info[0].equals(parentID))
            {
                if(info[1].equals(childName))
                {
                    flag=false;
                    errorMessage +="Kid already entered";
                }

            }

            c.moveToNext();

        }
        sqdb.close();

        if(flag)
        {
            cv.put(my_db.PARENT_ID,info[0]);
            cv.put(my_db.CHILD_NAME,info[1]);
            cv.put(my_db.DATE_OF_BIRTH,info[2]);
            cv.put(my_db.HEAD_DIAMETER,info[3]);
            cv.put(my_db.WEIGHT,info[4]);
            cv.put(my_db.HEIGHT,info[5]);
            cv.put(my_db.DOC_NAME,info[6]);
            cv.put(my_db.CHILD_ID,info[7]);

            cv2.put(DBHelper.DTW_COUGH,"false");
            cv2.put(DBHelper.HAEMOPHILUS_INFLUENZAE_TYPE_B,"false");
            cv2.put(DBHelper.POLIO,"false");
            cv2.put(DBHelper.GERMAN_MEASLES,"false");
            cv2.put(DBHelper.CHICKEN_POX,"false");
            cv2.put(DBHelper.PCV,"false");
            cv2.put(DBHelper.HEPATITIS_B,"false");
            cv2.put(DBHelper.HEPATITIS_B,"false");
            cv2.put(DBHelper.ROTAVIRUS,"false");
            sqdb=my_db.getWritableDatabase();
            sqdb.insert(my_db.TABLE_NAME2,null,cv);
            sqdb.insert(my_db.TABLE_NAME4,null,cv2);
            sqdb.close();




            etFullName.setText("");
            etDate.setText("");
            etHeight.setText("");
            etWeight.setText("");
            etHeadDiameter.setText("");
        }
        else
        {
            AlertDialog.Builder adb;
            AlertDialog ad;
            adb= new AlertDialog.Builder(this);
            adb.setTitle("Error");
            adb.setMessage(errorMessage);
            adb.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            ad= adb.create();
            ad.show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID=item.getItemId();

        if (itemID==R.id.back)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}