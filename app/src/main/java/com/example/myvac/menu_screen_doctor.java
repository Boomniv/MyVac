package com.example.myvac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class menu_screen_doctor extends AppCompatActivity {

    Spinner spinner;
    String docName ="";
    TextView tvFullName;
    SQLiteDatabase sqdb;
    List<String> children = new ArrayList<String>();
    DBHelper my_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen_doctor);

        spinner = (Spinner) findViewById(R.id.spinner2);

        Intent intent = getIntent();
        String docName = intent.getStringExtra("full_name");
        String full_name = "Hello, " +docName;
        tvFullName = findViewById(R.id.tvFullName);
        tvFullName.setText(full_name);

        my_db = new DBHelper(this);

        sqdb=my_db.getWritableDatabase();
        Cursor c = sqdb.query(DBHelper.TABLE_NAME2,null,null,null,null,null,null);
        int col1 = c.getColumnIndex(DBHelper.DOC_NAME);
        int col2 = c.getColumnIndex(DBHelper.CHILD_NAME);

        c.moveToFirst();
        while(!c.isAfterLast()) {
            String s1 = c.getString(col1);
            String s2 = c.getString(col2);
            if (docName.equals(s1)) {
                children.add(s2);
            }
            c.moveToNext();
        }
        sqdb.close();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, children);
        spinner.setAdapter(dataAdapter);
    }

    public void goToAddChild(View view) {
        Intent i = new Intent(this,add_child.class);
        i.putExtra("DoctorName",docName);
        startActivity(i);
    }

    public void goEditVac(View view) {
        boolean flag = false;
        Intent i = new Intent(this,edit_vaccines.class);
        String str = spinner.getSelectedItem().toString();
        sqdb=my_db.getWritableDatabase();
        Cursor c = sqdb.query(DBHelper.TABLE_NAME2,null,null,null,null,null,null);
        int col1 = c.getColumnIndex(DBHelper.CHILD_NAME);
        int col2 = c.getColumnIndex(DBHelper.CHILD_ID);
        c.moveToFirst();
        while(!c.isAfterLast()&&!flag) {
            String s1 = c.getString(col1);
            String s2 = c.getString(col2);
            if (str.equals(s1)) {
                i.putExtra("childId",s2);
                flag = true;
            }
            c.moveToNext();
        }
        sqdb.close();

        startActivity(i);
    }
}