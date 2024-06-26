package com.example.epics;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class FrontScreen extends AppCompatActivity {
    TextView appname;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_screen);
        appname = findViewById(R.id.appname);
        lottie = findViewById(R.id.lottie);

        //appname.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        appname.post(new Runnable() {
            @Override
            public void run() {
                int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
                int appnameHeight = appname.getHeight();
                int stopPosition = (screenHeight / 2) - (appnameHeight / 2);

                appname.animate().translationY(stopPosition).setDuration(2700).setStartDelay(0);
            }
        });
        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class); startActivity(i);

                finish(); // Optional: finish current activity after starting the next one
            }
        },5000);
    }
}