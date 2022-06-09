package com.example.myvac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class add_doctor extends AppCompatActivity {

    SQLiteDatabase sqdb;
    DBHelper my_db;
    EditText etDocId, etDocName,etDocPass;
    ContentValues cv = new ContentValues();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        etDocId = findViewById(R.id.dctrID);
        etDocName = findViewById(R.id.dctrName);
        etDocPass = findViewById(R.id.pass1);



    }

    public void addDoctorNow(View view) {
        cv.put(DBHelper.DOCTOR_NAME,etDocName.getText().toString());
        cv.put(DBHelper.DOCTOR_ID,etDocId.getText().toString());
        cv.put(DBHelper.DOC_PASSWORD,etDocPass.getText().toString());
        my_db = new DBHelper(this);
        sqdb=my_db.getWritableDatabase();
        sqdb.insert(my_db.TABLE_NAME3,null,cv);
        sqdb.close();
        finish();


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