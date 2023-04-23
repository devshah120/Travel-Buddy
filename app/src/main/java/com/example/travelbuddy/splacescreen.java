package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class splacescreen extends AppCompatActivity {

    LottieAnimationView lotite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splacescreen);

        lotite = findViewById(R.id.animationView);

        lotite.animate().setDuration(2000);
//        lotite.animate().translationX(2000).setDuration(2000).setStartDelay(2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        },1500 );
    }
}