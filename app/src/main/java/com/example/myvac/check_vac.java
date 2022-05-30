package com.example.myvac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class check_vac extends AppCompatActivity {

    String name;
    TextView tvName;
    OptionsAdapter adapter;
    SQLiteDatabase sqdb;
    DBHelper my_db;
    ListView lvDec;
    ArrayList<vacOptions> list2;
    String childId = "";
    String[] vacList = {"DTW COUGH", "HAEMOPHILUS INFLUENZAE TYPE B", "POLIO", "GERMAN MEASLES", "CHICKEN POX","PCV", "HEPATITIS B","HEPATITIS A","ROTAVIRUS"};

    int [] imageVacList = new int[vacList.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_vac);

        Intent intent = getIntent();
        name = intent.getStringExtra("childName");
        tvName = findViewById(R.id.tvFullName);
        tvName.setText(name);
        lvDec = findViewById(R.id.lvcheck);
        childId = intent.getStringExtra("childId");
        for(int i = 0; i< vacList.length;i++)
        {
            imageVacList[i] = R.drawable.empty_vac;
        }

        sqdb=my_db.getWritableDatabase();
        Cursor c = sqdb.query(DBHelper.TABLE_NAME4,null,null,null,null,null,null);
        int col1 = c.getColumnIndex(DBHelper.VAC_CHILD_ID);
        int col2 = c.getColumnIndex(DBHelper.DTW_COUGH);
        int col3 = c.getColumnIndex(DBHelper.HAEMOPHILUS_INFLUENZAE_TYPE_B);
        int col4 = c.getColumnIndex(DBHelper.POLIO);
        int col5 = c.getColumnIndex(DBHelper.GERMAN_MEASLES);
        int col6 = c.getColumnIndex(DBHelper.CHICKEN_POX);
        int col7 = c.getColumnIndex(DBHelper.PCV);
        int col8 = c.getColumnIndex(DBHelper.HEPATITIS_B);
        int col9 = c.getColumnIndex(DBHelper.HEPATITIS_A);
        int col10 = c.getColumnIndex(DBHelper.ROTAVIRUS);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            String s1 = c.getString(col1);
            String s2 = c.getString(col2);
            String s3 = c.getString(col3);
            String s4 = c.getString(col4);
            String s5 = c.getString(col5);
            String s6 = c.getString(col6);
            String s7 = c.getString(col7);
            String s8 = c.getString(col8);
            String s9 = c.getString(col9);
            String s10 = c.getString(col10);
            if (childId.equals(s1)) {
                if (s2.equals("true"))
                {
                    imageVacList[0] = R.drawable.green_vac;

                    lvDec.getChildAt(0).setBackgroundColor(Color.parseColor("#FF76FF00"));
                }

                if (s3.equals("true"))
                {
                    imageVacList[1] = R.drawable.green_vac;

                    lvDec.getChildAt(1).setBackgroundColor(Color.parseColor("#FF76FF00"));
                }
                if (s4.equals("true"))
                {
                    imageVacList[2] = R.drawable.green_vac;

                    lvDec.getChildAt(2).setBackgroundColor(Color.parseColor("#FF76FF00"));
                }
                if (s5.equals("true"))
                {
                    imageVacList[3] = R.drawable.green_vac;

                    lvDec.getChildAt(3).setBackgroundColor(Color.parseColor("#FF76FF00"));
                }
                if (s6.equals("true"))
                {
                    imageVacList[4] = R.drawable.green_vac;

                    lvDec.getChildAt(4).setBackgroundColor(Color.parseColor("#FF76FF00"));
                }
                if (s7.equals("true"))
                {
                    imageVacList[5] = R.drawable.green_vac;

                    lvDec.getChildAt(5).setBackgroundColor(Color.parseColor("#FF76FF00"));
                }
                if (s8.equals("true"))
                {
                    imageVacList[6] = R.drawable.green_vac;

                    lvDec.getChildAt(6).setBackgroundColor(Color.parseColor("#FF76FF00"));
                }
                if (s9.equals("true"))
                {
                    imageVacList[7] = R.drawable.green_vac;

                    lvDec.getChildAt(7).setBackgroundColor(Color.parseColor("#FF76FF00"));
                }
                if (s10.equals("true"))
                {
                    imageVacList[8] = R.drawable.green_vac;

                    lvDec.getChildAt(8).setBackgroundColor(Color.parseColor("#FF76FF00"));
                }

            }
            c.moveToNext();
        }
        sqdb.close();
        list2 = new ArrayList<>();
        for(int i =0; i < vacList.length; i++)
            list2.add(new vacOptions(vacList[i],imageVacList[i]));
        adapter = new OptionsAdapter(this,R.layout.my_list,this.list2);
        this.lvDec.setAdapter(adapter);
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