package com.example.myvac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class menu_screen extends AppCompatActivity {

    Spinner spinner;
    TextView tvFullName;
    String parentID;
    SQLiteDatabase sqdb;
    List<String> children = new ArrayList<String>();
    DBHelper my_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);



        Intent intent = getIntent();
        String parentName = intent.getStringExtra("full_name");
        parentID= intent.getStringExtra("parent_id");
        String full_name = "Hello, " +parentName;
        tvFullName = findViewById(R.id.tvFullName);
        tvFullName.setText(full_name);
        spinner = (Spinner) findViewById(R.id.spinnerChild);


        my_db = new DBHelper(this);
        sqdb=my_db.getWritableDatabase();
        Cursor c = sqdb.query(DBHelper.TABLE_NAME2,null,null,null,null,null,null);
        int col1 = c.getColumnIndex(DBHelper.PARENT_ID);
        int col2 = c.getColumnIndex(DBHelper.CHILD_NAME);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            String s1 = c.getString(col1);
            String s2 = c.getString(col2);
            if (parentID.equals(s1)) {
                children.add(s2);
            }
            c.moveToNext();
        }
        sqdb.close();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, children);
        spinner.setAdapter(dataAdapter);

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


    public void goToCheckVac(View view) {
        boolean flag = false;
        Intent i = new Intent(this,check_vac.class);
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
                i.putExtra("childName",s1);

                flag = true;
            }
            c.moveToNext();
        }
        sqdb.close();

        startActivity(i);
    }
}