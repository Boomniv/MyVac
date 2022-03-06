package com.example.myvac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class forgot_password extends AppCompatActivity {
    private static final int PERMISSION_REQUEST = 100;

    String phone ="";
    String password = "";
    EditText ID;
    SQLiteDatabase sqdb;
    DBHelper my_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        my_db = new DBHelper(this);
        sqdb=my_db.getWritableDatabase();
        sqdb.close();

        ID = findViewById(R.id.etID);

    }

    public void goRecover(View view) {
        String id = ID.getText().toString();



        sqdb=my_db.getWritableDatabase();
        Cursor c = sqdb.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        boolean flag = false;
        int col1 = c.getColumnIndex(DBHelper.ID);
        int col2 = c.getColumnIndex(DBHelper.PHONE);
        int col3 =  c.getColumnIndex(DBHelper.PASS);

        c.moveToFirst();
        while(!c.isAfterLast())
        {
            String s1 = c.getString(col1);

            if (id.equals(s1))
            {
                flag =true;
                phone = c.getString(col2);
                password = c.getString(col3);
            }

            c.moveToNext();

        }
        sqdb.close();

        if(flag)
        {
            send();
        }
        else
        {
            Toast.makeText(this, "User doesn't exist", Toast.LENGTH_SHORT).show();
        }


    }
    public void send() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                    Snackbar.make(findViewById(R.id.rl), "You need to grant SEND SMS permission to send sms",
                            Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST);
                        }
                    }).show();
                } else {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST);
                }
            } else {
                sendSMS();
            }
        } else {
            sendSMS();
        }
    }

    private void sendSMS() {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone, null, "your password: " + password, null, null);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Snackbar.make(findViewById(R.id.rl), "Permission Granted",
                    Snackbar.LENGTH_LONG).show();
            sendSMS();

        } else {

            Snackbar.make(findViewById(R.id.rl), "Permission denied",
                    Snackbar.LENGTH_LONG).show();

        }
    }
}