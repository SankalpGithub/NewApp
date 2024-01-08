package com.example.shopingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class splash_screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // on below line we are calling handler to run a task
        // for specific time interval
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                // on below line we are
//                // creating a new intent
                Intent i = new Intent(splash_screen.this, home_screen.class);
//
//                // on below line we are
//                // starting a new activity.
                startActivity(i);
//
//                // on the below line we are finishing
//                // our current activity.
                finish();
            }
        }, 2000);
    }
}