package com.example.myvac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class menu_screen extends AppCompatActivity {

    TextView tvFullName;
    String parentID;

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

    public void goToAddChild(View view) {
        Intent i = new Intent(this,add_child.class);
        i.putExtra("parentID",parentID);
        startActivity(i);
    }
}