package com.example.myvac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registry extends AppCompatActivity {

    EditText etFirst, etLast, etId, etPass, etConPass, etPhone,etMail;
    SQLiteDatabase sqdb;
    DBHelper my_db;
    String[] info = new String[7];
    Boolean flag = true;
    ContentValues cv = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        my_db = new DBHelper(this);
        sqdb=my_db.getWritableDatabase();
        sqdb.close();

        etFirst = findViewById(R.id.etFirstName);
        etLast = findViewById(R.id.etLastName);
        etId = findViewById(R.id.etIdNumber);
        etPass = findViewById(R.id.etPassword);
        etConPass = findViewById(R.id.etConfirmPassword);
        etPhone = findViewById(R.id.etPhoneNumber);
        etMail = findViewById(R.id.etEmail);

    }

    public void tryToRegister(View view) {
        info[0] = etFirst.getText().toString();
        info[1] = etLast.getText().toString();
        info[2] = etId.getText().toString();
        info[3] = etPass.getText().toString();
        info[4] = etConPass.getText().toString();
        info[5] = etPhone.getText().toString();
        info[6] = etMail.getText().toString();



        sqdb=my_db.getWritableDatabase();
        Cursor c = sqdb.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        int col = c.getColumnIndex(DBHelper.ID);

        c.moveToFirst();
        while(!c.isAfterLast()&&flag)
        {
            String s = c.getString(col);

            if (info[2].equals(s))
            {
                flag=false;
                Toast.makeText(this, "User already exists!", Toast.LENGTH_SHORT).show();
            }
            if (info[3].equals(info[4]))
            {
                flag=false;
                Toast.makeText(this, "Passwords does not match!", Toast.LENGTH_SHORT).show();
            }

            c.moveToNext();

        }
        sqdb.close();

        if(flag)
        {
            cv.put(my_db.FIRST_NAME,info[0]);
            cv.put(my_db.LAST_NAME,info[1]);
            cv.put(my_db.ID,info[2]);
            cv.put(my_db.PASS,info[3]);
            cv.put(my_db.PHONE,info[5]);
            cv.put(my_db.EMAIL,info[6]);
            sqdb=my_db.getWritableDatabase();
            sqdb.insert(my_db.TABLE_NAME,null,cv);
            sqdb.close();
        }

        etFirst.setText("");
        etLast.setText("");
        etId.setText("");
        etPass.setText("");
        etConPass.setText("");
        etPhone.setText("");
        etMail.setText("");


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