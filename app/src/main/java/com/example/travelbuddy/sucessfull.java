package com.example.travelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class sucessfull extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucessfull);
    }

    public void Homme(View view)
    {
//        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

}