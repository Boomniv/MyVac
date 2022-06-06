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
    String id="", password="", nameToPass="" , idToPass="";
    SQLiteDatabase sqdb;
    DBHelper my_db;


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
        if(checkAdmin())
        {
            Intent i = new Intent(this,admin_menu.class);
            startActivity(i);
        }
        else if(checkDoctor())
        {
            Intent i = new Intent(this,menu_screen_doctor.class);
            i.putExtra("full_name", nameToPass);
            startActivity(i);

        }
        else if(checkUser())
        {
            sqdb=my_db.getWritableDatabase();
            Cursor c = sqdb.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);

            Intent i = new Intent(this,menu_screen.class);
            i.putExtra("full_name", nameToPass);
            i.putExtra("parent_id", idToPass);
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

                }
            });

            ad= adb.create();
            ad.show();

        }

    }

    private boolean checkDoctor() {
        sqdb=my_db.getWritableDatabase();
        Cursor c = sqdb.query(DBHelper.TABLE_NAME3,null,null,null,null,null,null);
        boolean flag = false;
        int col1 = c.getColumnIndex(DBHelper.DOCTOR_ID);
        int col2 = c.getColumnIndex(DBHelper.DOC_PASSWORD);
        int col3 = c.getColumnIndex(DBHelper.DOCTOR_NAME);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            String s1 = c.getString(col1);
            String s2 = c.getString(col2);
            String s3 = c.getString(col3);
            if (id.equals(s1) && password.equals(s2)) {
                flag = true;
                nameToPass = s3;
                idToPass = s1;
            }
            c.moveToNext();
        }
        sqdb.close();
        return flag;
    }

    private boolean checkAdmin()
    {
        if (id.equals("326047784")&& password.equals("hiitsme"))
        {
            return true;
        }
        return false;
    }


    private boolean checkUser() {

        sqdb=my_db.getWritableDatabase();
        Cursor c = sqdb.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        boolean flag = false;
        int col1 = c.getColumnIndex(DBHelper.ID);
        int col2 = c.getColumnIndex(DBHelper.PASS);
        int col3 = c.getColumnIndex(DBHelper.FIRST_NAME);
        int col4 = c.getColumnIndex(DBHelper.LAST_NAME);


        c.moveToFirst();
        while(!c.isAfterLast())
        {
            String s1 = c.getString(col1);
            String s2 = c.getString(col2);
            String s3 = c.getString(col3);
            String s4 = c.getString(col4);
            if (id.equals(s1)&&password.equals(s2))
            {
                flag =true;
                nameToPass = s3 + " " +s4;
                idToPass = s1;
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


    public void goRecoverPage(View view) {
        Intent i = new Intent(this,forgot_password.class);
        startActivity(i);
    }
}