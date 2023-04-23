package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.travelbuddy.UtilsService.SharedPreferenceClass;
import com.example.travelbuddy.UtilsService.UtilService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main_Payment1 extends AppCompatActivity {

    private Button proceed;

    private EditText place_ET,cheak_in,cheak_out;
    private EditText fname_ET,lname_ET,email_ET,contact_ET;
    private  String fname,lname,email,contact,checkin,checkout,place;
    UtilService utilService;
    SharedPreferenceClass sharedPreferenceClass;

    String token;

    private static final String CHANNEL_ID = "MY Channel";
    private static final int NOTIFICATION_ID = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_payment1);

        place_ET=findViewById(R.id.place_hotel);
        cheak_in = findViewById(R.id.ck_in);
        cheak_out = findViewById(R.id.ck_out);
        // on below line we are adding click listener for our pick date button
        cheak_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        Main_Payment1.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                cheak_in.setText("  " + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        cheak_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        Main_Payment1.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                cheak_out.setText("  " + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        proceed = findViewById(R.id.book3);

        fname_ET= findViewById(R.id.editTextTextPersonName2);
        lname_ET = findViewById(R.id.editTextTextPersonName3);
        email_ET = findViewById(R.id.editTextTextEmailAddress);
        contact_ET = findViewById(R.id.editTextPhone);
        cheak_in = findViewById(R.id.ck_in);
        cheak_out=findViewById(R.id.ck_out);
        utilService = new UtilService();
        sharedPreferenceClass = new SharedPreferenceClass(this);
        token = sharedPreferenceClass.getValue_String("token");

        proceed.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotificationPermission")
            @Override
            public void onClick(View view) {
                utilService.hideKeyboard(view,Main_Payment1.this);
                place=place_ET.getText().toString();
                fname=fname_ET.getText().toString();
                lname=lname_ET.getText().toString();
                email = email_ET.getText().toString();
                contact=contact_ET.getText().toString();
                checkin=cheak_in.getText().toString();
                checkout=cheak_out.getText().toString();

                    reservationUser(view);
//                    navifdsffd(view);

                Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.logo,null);

                BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
                Bitmap largeIcon = bitmapDrawable.getBitmap();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                     notification = new Notification.Builder(Main_Payment1.this)
                            .setSmallIcon(R.drawable.logo)
                            .setContentText("successful Booked Please Do Payment!!")
                            .setSubText("New Message from Travel Buddy")
                            .setChannelId(CHANNEL_ID)
                            .build();

                     notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"New Channel",NotificationManager.IMPORTANCE_HIGH));
                }
                else {
                     notification = new Notification.Builder(Main_Payment1.this)
                            .setSmallIcon(R.drawable.logo)
                            .setContentText("New Message")
                            .setSubText("New Message from Travel Buddy")
                            .build();
                }
                notificationManager.notify(NOTIFICATION_ID,notification);
            }
        });
    }

    private void reservationUser(View view) {

//        Toast.makeText(Main_Payment1.this, "Reservation SFGHDFGHDSFHG", Toast.LENGTH_SHORT).show();

        HashMap<String,String> params= new HashMap<>();
        params.put("place",place);
        params.put("fname",fname);
        params.put("lname",lname);
        params.put("email",email);
        params.put("contactno",contact);
        params.put("checkIn",checkin);
        params.put("checkOut",checkout);

        String apiKey = "https://travel-buddy-api-production-8b1b.up.railway.app/api/reservation";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                apiKey, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                        Toast.makeText(Main_Payment1.this, "Reservation Done", Toast.LENGTH_SHORT).show();
                try
                {
                    if (response.getBoolean("success"))
                    {
                        String token = response.getString("token");
                        sharedPreferenceClass.setValue_String("token",token);
//                        Toast.makeText(Main_Payment1.this, "Reservation Done", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(Main_Payment1.this,sucessfull.class));
//                        navifdsffd(view);
                    }
                }
                catch (JSONException e)
                {
//                    Toast.makeText(Main_Payment1.this, "RUNTIME Reservation SFGHDFGHDSFHG", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main_Payment1.this, Main_Payment2.class);
                    intent.putExtra("place",place);
                    intent.putExtra("fname",fname);
                    intent.putExtra("lname",lname);
                    intent.putExtra("email",email);
                    intent.putExtra("contact",contact);
                    intent.putExtra("checkin",checkin);
                    intent.putExtra("checkout",checkout);
                    startActivity(intent);
//                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                Toast.makeText(Main_Payment1.this, "Please Log In Before Booking", Toast.LENGTH_SHORT).show();

                if (error instanceof ServerError && response != null){
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers,"utf-8"));

                        JSONObject obj = new JSONObject(res);
                        Toast.makeText(Main_Payment1.this,obj.getString("msg"),Toast.LENGTH_SHORT).show();
                    }catch (JSONException | UnsupportedEncodingException je){
                        je.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<>();
                headers.put("Content-Type","application/json");
                headers.put("Authorization", token);
                return headers;
            }
        };

        //set retry policy
        int socketTime = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTime,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);

        //request add
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }




    //Homepage
    public void navifdsffd(View view)
    {
//        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
//        reservationUser(view);
        Intent intent = new Intent(Main_Payment1.this, Main_Payment2.class);
        startActivity(intent);
    }

}