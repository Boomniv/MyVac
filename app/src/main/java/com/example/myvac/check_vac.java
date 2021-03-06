package com.example.myvac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class check_vac extends AppCompatActivity {

    String name,childId = "";
    TextView tvName;
    OptionsAdapter adapter;
    SQLiteDatabase sqdb;
    DBHelper my_db;
    ListView lvDec;
    ArrayList<vacOptions> list2;
    String[] vacList = {"DTW COUGH", "HAEMOPHILUS INFLUENZAE TYPE B", "POLIO", "GERMAN MEASLES", "CHICKEN POX","PCV", "HEPATITIS B","HEPATITIS A","ROTAVIRUS"};
    String[] addColor = new String[vacList.length];

    int [] imageVacList = new int[vacList.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_vac);

        my_db = new DBHelper(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("childName");
        tvName = findViewById(R.id.child_name);
        tvName.setText(name);
        lvDec = findViewById(R.id.lvcheck);
        childId = intent.getStringExtra("childId");
        Log.println(Log.DEBUG,"debug", childId);
        for(int i = 0; i< vacList.length;i++)
        {
            imageVacList[i] = R.drawable.empty_vac;
            addColor[i] = "false";
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
                    addColor[0] = "true";

                }

                if (s3.equals("true"))
                {
                    imageVacList[1] = R.drawable.green_vac;
                    addColor[1] = "true";

                }
                if (s4.equals("true"))
                {
                    imageVacList[2] = R.drawable.green_vac;
                    addColor[2] = "true";

                }
                if (s5.equals("true"))
                {
                    imageVacList[3] = R.drawable.green_vac;
                    addColor[3] = "true";

                }
                if (s6.equals("true"))
                {
                    imageVacList[4] = R.drawable.green_vac;
                    addColor[4] = "true";

                }
                if (s7.equals("true"))
                {
                    imageVacList[5] = R.drawable.green_vac;
                    addColor[5] = "true";

                }
                if (s8.equals("true"))
                {
                    imageVacList[6] = R.drawable.green_vac;
                    addColor[6] = "true";

                }
                if (s9.equals("true"))
                {
                    imageVacList[7] = R.drawable.green_vac;
                    addColor[7] = "true";

                }
                if (s10.equals("true"))
                {
                    imageVacList[8] = R.drawable.green_vac;
                    addColor[8] = "true";

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
        /*
        for(int i = 0; i< addColor.length;i++)
        {
            if(addColor[i]=="true")
            {
                lvDec.getChildAt(i).setBackgroundColor(Color.parseColor("#FF76FF00"));
            }
        }
*/
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
        if (itemID==R.id.guide)
        {
            Intent i = new Intent(this,Guide.class);
            startActivity(i);
        }
        if (itemID==R.id.credits)
        {
            Intent i = new Intent(this,Credits.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}