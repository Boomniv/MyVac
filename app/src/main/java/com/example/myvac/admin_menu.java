package com.example.myvac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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