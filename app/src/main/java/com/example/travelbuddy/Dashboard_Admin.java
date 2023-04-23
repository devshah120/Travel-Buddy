package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Dashboard_Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
    }

    public void admin_home(View view) {
        Intent myIntent = new Intent(Dashboard_Admin.this, admin_user_add_place.class);   //NEW WAY
        startActivity(myIntent);
    }

    public void user_data(View view) {
        Intent myIntent = new Intent(Dashboard_Admin.this, admin_user_data.class);   //NEW WAY
        startActivity(myIntent);
    }

    public void view_booking(View view) {
        Intent myIntent = new Intent(Dashboard_Admin.this, ViewBooking.class);   //NEW WAY
        startActivity(myIntent);
    }

    public void view_payment(View view) {
        Intent myIntent = new Intent(Dashboard_Admin.this, ViewPayment.class);   //NEW WAY
        startActivity(myIntent);
    }

    public void view_Flight(View view) {
        Intent myIntent = new Intent(Dashboard_Admin.this, ViewFlight.class);   //NEW WAY
        startActivity(myIntent);
    }

    public void view_Train(View view) {
        Intent myIntent = new Intent(Dashboard_Admin.this, ViewTrain.class);   //NEW WAY
        startActivity(myIntent);
    }

    public void view_Bus(View view) {
        Intent myIntent = new Intent(Dashboard_Admin.this, ViewBus.class);   //NEW WAY
        startActivity(myIntent);
    }

    public void logout(View view)
    {
        Toast.makeText(Dashboard_Admin.this, "Logged Out", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(Dashboard_Admin.this, MainActivity.class);   //NEW WAY
        startActivity(myIntent);
    }
}