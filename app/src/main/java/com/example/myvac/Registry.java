package com.example.myvac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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
    String errorMessage;
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
        errorMessage= "";
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
        if(info[2].length()==9 && info[2].matches("[0-9]+")) {
            if(info[3].length()>5) {
                if(info[6]!= null && android.util.Patterns.EMAIL_ADDRESS.matcher(info[6]).matches()) {
                    if(!isValidTel(info[5])) {
                        while (!c.isAfterLast() && flag) {
                            String s = c.getString(col);

                            if (info[2].equals(s)) {
                                flag = false;
                                errorMessage += "User already exists!";
                                errorMessage += "\n";

                            }
                            if (info[3].equals(info[4])) {
                                flag = false;
                                errorMessage += "Passwords does not match!";
                                errorMessage += "\n";
                            }

                            c.moveToNext();

                        }
                    }
                    else
                    {
                        flag = false;
                        errorMessage += "Invalid tel";
                        errorMessage += "\n";
                    }
                }
                else
                {
                    flag = false;
                    errorMessage += "Invalid mail";
                    errorMessage += "\n";
                }
            }
            else
            {
                flag = false;
                errorMessage += "Invalid password";
                errorMessage += "\n";
            }
        }
        else
        {
            flag = false;
            errorMessage += "Invalid id";
            errorMessage += "\n";
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

            etFirst.setText("");
            etLast.setText("");
            etId.setText("");
            etPass.setText("");
            etConPass.setText("");
            etPhone.setText("");
            etMail.setText("");

            finish();
        }
        else
        {
            AlertDialog.Builder adb;
            AlertDialog ad;
            adb= new AlertDialog.Builder(this);
            adb.setTitle("Error, parameters are wrong");
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
    public boolean isValidTel(String target) {
        String toCheck = target.substring(1);
        if(target.startsWith("+972"))
        {
            if (toCheck.matches("[0-9]+") && target.length() == 13) {
                return false;
            }
            return true;
        }
        else
        {
            if (target.startsWith("0"))
            {
                if (target.matches("[0-9]+") && (target.length() == 10 || target.length() == 9)) {
                    return false;
                }
                return true;
            }
            else
            {
                return true;
            }
        }

    }
}