package com.example.myvac;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class edit_vaccines extends AppCompatActivity {

    OptionsAdapter adapter;
    SQLiteDatabase sqdb;
    DBHelper my_db;
    ListView lvDec;
    ArrayList<vacOptions> list2;
    ContentValues cv = new ContentValues();
    String childId = "";
    String[] vacList = {"DTW COUGH", "HAEMOPHILUS INFLUENZAE TYPE B", "POLIO", "GERMAN MEASLES", "CHICKEN POX","PCV", "HEPATITIS B","HEPATITIS A","ROTAVIRUS"};
    int arr[] = new int[vacList.length];
    int [] imageVacList = new int[vacList.length];
    String [] updated = new String[vacList.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vaccines);

        my_db = new DBHelper(this);
        Intent intent = getIntent();
        lvDec = findViewById(R.id.lvDec);
        childId = intent.getStringExtra("childId");
        for(int i = 0; i< arr.length;i++)
        {
            arr[i] = 0;
        }
        for(int i = 0; i< vacList.length;i++)
        {
            imageVacList[i] = R.drawable.empty_vac;
            updated[i] = "false";
        }



        sqdb=my_db.getWritableDatabase();
        Cursor c = sqdb.query(DBHelper.TABLE_NAME4,null,null,null,null,null,null);
        int col1 = c.getColumnIndex(DBHelper.VAC_CHILD_ID);
        int col2 = c.getColumnIndex(DBHelper.DTW_COUGH);
        int col3 = c.getColumnIndex(DBHelper.HAEMOPHILUS_INFLUENZAE_TYPE_B);
        int col4 = c.getColumnIndex(DBHelper.POLIO);
        int col5 = c.getColumnIndex(DBHelper.GERMAN_MEASLES);
        int col6 = c.getColumnIndex(DBHelper.CHICKEN_POX);
        int col7 = c.getColumnIndex(DBHelper.PCV);
        int col8 = c.getColumnIndex(DBHelper.HEPATITIS_B);
        int col9 = c.getColumnIndex(DBHelper.HEPATITIS_A);
        int col10 = c.getColumnIndex(DBHelper.ROTAVIRUS);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            String s1 = c.getString(col1);
            String s2 = c.getString(col2);
            String s3 = c.getString(col3);
            String s4 = c.getString(col4);
            String s5 = c.getString(col5);
            String s6 = c.getString(col6);
            String s7 = c.getString(col7);
            String s8 = c.getString(col8);
            String s9 = c.getString(col9);
            String s10 = c.getString(col10);
            if (childId.equals(s1)) {
                if (s2.equals("true"))
                {
                    imageVacList[0] = R.drawable.green_vac;
                    arr[0]++;
                    updated[0] = "true";
                }

                if (s3.equals("true"))
                {
                    imageVacList[1] = R.drawable.green_vac;
                    arr[1]++;
                    updated[1] = "true";
                }
                if (s4.equals("true"))
                {
                    imageVacList[2] = R.drawable.green_vac;
                    arr[2]++;
                    updated[2] = "true";
                }
                if (s5.equals("true"))
                {
                    imageVacList[3] = R.drawable.green_vac;
                    arr[3]++;
                    updated[3] = "true";
                }
                if (s6.equals("true"))
                {
                    imageVacList[4] = R.drawable.green_vac;
                    arr[4]++;
                    updated[4] = "true";
                }
                if (s7.equals("true"))
                {
                    imageVacList[5] = R.drawable.green_vac;
                    arr[5]++;
                    updated[5] = "true";
                }
                if (s8.equals("true"))
                {
                    imageVacList[6] = R.drawable.green_vac;
                    arr[6]++;
                    updated[6] = "true";
                }
                if (s9.equals("true"))
                {
                    imageVacList[7] = R.drawable.green_vac;
                    arr[7]++;
                    updated[7] = "true";
                }
                if (s10.equals("true"))
                {
                    imageVacList[8] = R.drawable.green_vac;
                    arr[8]++;
                    updated[8] = "true";
                }

            }
            c.moveToNext();
        }
        sqdb.close();
        list2 = new ArrayList<>();
        for(int i =0; i < vacList.length; i++)
            list2.add(new vacOptions(vacList[i],imageVacList[i]));
        adapter = new OptionsAdapter(this, R.layout.my_list,list2){
            @Override
            public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (arr[position]%2!=0) {
                    view.setBackgroundColor(Color.parseColor("#FF76FF00"));
                } else if (arr[position]%2== 0) {
                    view.setBackgroundColor(Color.TRANSPARENT);
                }
                return view;
            }
        };
        this.lvDec.setAdapter(adapter);
        lvDec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                arr[i]++;

                if(arr[i]%2 != 0) {
                    view.setBackgroundColor(Color.parseColor("#FF76FF00"));
                    updated[i] = "true";
                }
                else {
                    view.setBackgroundColor(Color.TRANSPARENT);
                    updated[i] = "false";
                }
            }
        });


    }

    public void save(View view) {
        sqdb=my_db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.DTW_COUGH,updated[0]);
        cv.put(DBHelper.HAEMOPHILUS_INFLUENZAE_TYPE_B,updated[1]);
        cv.put(DBHelper.POLIO,updated[2]);
        cv.put(DBHelper.GERMAN_MEASLES,updated[3]);
        cv.put(DBHelper.CHICKEN_POX,updated[4]);
        cv.put(DBHelper.PCV,updated[5]);
        cv.put(DBHelper.HEPATITIS_B,updated[6]);
        cv.put(DBHelper.HEPATITIS_B,updated[7]);
        cv.put(DBHelper.ROTAVIRUS,updated[8]);
        sqdb.update(DBHelper.TABLE_NAME4, cv, "ChildID = ?", new String[]{childId});
        sqdb.close();
        NotificationCompat.Builder builder =new  NotificationCompat.Builder(this.getApplicationContext(),"channel_1");
        Intent ii = new Intent(this.getApplicationContext(),MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,ii,0);
        builder.setContentTitle("Vaccine Editor");
        builder.setContentText("Vaccine list was updated");
        builder.setSmallIcon(R.drawable.my_vac_logo);
        NotificationManager mNotificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel1 = new NotificationChannel(
                    "channel_1",
                    "Vaccine List Updated",
                    NotificationManager.IMPORTANCE_HIGH


            );
            channel1.setDescription("The vaccine list of the patient was updated succesfully!");
            mNotificationManager.createNotificationChannel(channel1);
            builder.setChannelId("channel_1");
        }

        mNotificationManager.notify(0, builder.build());
        Intent buzz= new Intent(this,SampleService.class);
        startService(buzz);
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