package com.example.myvac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.loadDataWithBaseURL("file:///android_res/drawable/","<img align='middle' src='welcome_to_my_vac_animation.gif' width='100%'/>","text/html","uft-8",null);

        webView.reload();


        Thread t = new Thread()
        {
            @Override
            public void run() {
                try{

                    sleep(3000);
                    Intent i = new Intent(MainActivity.this,Login.class);
                    startActivity(i);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        t.start();




    }

}