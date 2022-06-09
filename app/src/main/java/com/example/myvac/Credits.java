package com.example.myvac;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        TextView txtRawResource= (TextView)findViewById(R.id.txtRawResource);

        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.credits);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            txtRawResource.setText(new String(b));
        } catch (Exception e) {
            // e.printStackTrace();
            txtRawResource.setText("Error: can't show help.");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.credits);
        item.setVisible(false);
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
        return super.onOptionsItemSelected(item);
    }
}