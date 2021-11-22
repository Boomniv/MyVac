package com.example.myvac;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    EditText etId, etPassword;
    String id="", password="";
    SQLiteDatabase sqdb;
    DBHelper my_db;
    Boolean flag = false;

    private static final Pattern NAME_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{2,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        my_db = new DBHelper(this);
        sqdb=my_db.getWritableDatabase();
        sqdb.close();

        etId = findViewById(R.id.etId);
        etPassword = findViewById(R.id.etPassword);
    }

    public void tryToLogin(View view) {
        id = etId.getText().toString();
        password = etPassword.getText().toString();
        if(checkUser())
        {
            sqdb=my_db.getWritableDatabase();
            Cursor c = sqdb.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
            String s1 = c.getString(c.getColumnIndex(DBHelper.FIRST_NAME));
            String s2 = c.getString(c.getColumnIndex(DBHelper.LAST_NAME));
            Intent i = new Intent(this,menu_screen.class);
            i.putExtra("first_name", s1);
            i.putExtra("last_name", s2);
            startActivity(i);
            sqdb.close();
        }
        else
        {
            AlertDialog.Builder adb;
            AlertDialog ad;
            adb= new AlertDialog.Builder(this);
            adb.setTitle("Error");
            //adb.seticon
            adb.setMessage("User doesnt exists");
            adb.setNeutralButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            ad= adb.create();
            ad.show();

        }

    }

    private boolean checkUser() {

        sqdb=my_db.getWritableDatabase();
        Cursor c = sqdb.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        boolean flag = false;
        int col1 = c.getColumnIndex(DBHelper.ID);
        int col2 = c.getColumnIndex(DBHelper.PASS);


        c.moveToFirst();
        while(!c.isAfterLast())
        {
            String s1 = c.getString(col1);
            String s2 = c.getString(col2);
            if (id.equals(s1)&&password.equals(s2))
            {
                return true;
            }

            c.moveToNext();

        }
        sqdb.close();



        return flag;
    }

    public void goRegistry(View view) {
        Intent i = new Intent(this,Registry.class);
        startActivity(i);
    }


}