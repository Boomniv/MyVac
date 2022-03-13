package com.example.myvac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admin_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);


    }

    public void addDoctor(View view) {
        Intent i = new Intent(this,add_doctor.class);
        startActivity(i);
    }
}