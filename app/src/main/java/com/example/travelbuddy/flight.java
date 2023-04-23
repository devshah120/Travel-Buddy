package com.example.travelbuddy;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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
import java.util.Map;

public class flight extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {


    private Button book;
    private EditText name,email,person;
    private EditText date;
    private EditText time;
    String[] country = { "India", "USA", "China", "Japan", "Switzerland", "Germany", "Canada", "Australia", "Other"};
    String[] cclassof = { "Economy", "Premium Economy", "Business", "First Class"};

    private  String tname,temail,tfrom,tto,tdate,ttime,tclass,tperson;

    UtilService utilService;

    SharedPreferenceClass sharedPreferenceClass;

    String token;

    private static final String CHANNEL_ID = "MY Channel";
    private static final int NOTIFICATION_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

//        Toast.makeText(this, "FLIGHt", Toast.LENGTH_SHORT).show();

        date = findViewById(R.id.ck_in2);
        date.setOnClickListener(new View.OnClickListener() {
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
                        flight.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                date.setText("  " + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

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

        time = findViewById(R.id.editTextTime);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting the
                // instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting our hour, minute.
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // on below line we are initializing our Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(flight.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String AM_PM ;
                                if(hourOfDay < 12) {
                                    AM_PM = "AM";
                                } else {
                                    AM_PM = "PM";
                                }
                                // on below line we are setting selected time
                                // in our text view.
                                time.setText("  " + hourOfDay + ":" + minute + " " + AM_PM );
                            }
                        }, hour, minute, false);
                // at last we are calling show to
                // display our time picker dialog.
                timePickerDialog.show();
            }
        });


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner from = (Spinner) findViewById(R.id.spinner); //1
        from.setOnItemSelectedListener(this);

        Spinner to = (Spinner) findViewById(R.id.spinner2); //2
        to.setOnItemSelectedListener(this);

        Spinner classss = (Spinner) findViewById(R.id.spinner3); //3
        classss.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,cclassof);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        from.setAdapter(aa);    //1
        to.setAdapter(aa);   //2
        classss.setAdapter(bb);   //3

        book=findViewById(R.id.book5);
        name=findViewById(R.id.editTextTextPersonName4);
        email=findViewById(R.id.editTextTextEmailAddress2);
        person=findViewById(R.id.editTextTextPersonName5);
        utilService = new UtilService();
        sharedPreferenceClass = new SharedPreferenceClass(this);
        token = sharedPreferenceClass.getValue_String("token");
        book.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotificationPermission")
            @Override
            public void onClick(View view) {
                utilService.hideKeyboard(view,flight.this);
                tname=name.getText().toString();
                temail=email.getText().toString();
                tfrom=from.getSelectedItem().toString();
                tto = to.getSelectedItem().toString();
                tdate=date.getText().toString();
                ttime=time.getText().toString();
                tperson=person.getText().toString();
                tclass=classss.getSelectedItem().toString();

                reservationUser(view);
//                    navifdsffd(view);

                Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.logo,null);

                BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
                Bitmap largeIcon = bitmapDrawable.getBitmap();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notification = new Notification.Builder(flight.this)
                            .setSmallIcon(R.drawable.logo)
                            .setContentText("Flight Booked Please Do Payment!!")
                            .setSubText("New Message from Travel Buddy")
                            .setChannelId(CHANNEL_ID)
                            .build();

                    notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"Flight Channel",NotificationManager.IMPORTANCE_HIGH));
                }
                else {
                    notification = new Notification.Builder(flight.this)
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
        params.put("name",tname);
        params.put("email",temail);
        params.put("from",tfrom);
        params.put("to",tto);
        params.put("date",tdate);
        params.put("time",ttime);
        params.put("people",tperson);
        params.put("classs",tclass);

        String apiKey = "https://travel-buddy-api-production-8b1b.up.railway.app/api/flight";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                apiKey, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(flight.this, "Flight Reservation Done", Toast.LENGTH_SHORT).show();
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
                    Intent intent = new Intent(flight.this, Main_Payment2.class);
                    intent.putExtra("name",tname);
                    intent.putExtra("email",temail);
                    intent.putExtra("from",tfrom);
                    intent.putExtra("to",tto);
                    intent.putExtra("date",tdate);
                    intent.putExtra("time",ttime);
                    intent.putExtra("people",tperson);
                    intent.putExtra("classs",tclass);
                    startActivity(intent);
//                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                Toast.makeText(flight.this, "Please Log In Before Booking", Toast.LENGTH_SHORT).show();

                if (error instanceof ServerError && response != null){
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers,"utf-8"));

                        JSONObject obj = new JSONObject(res);
                        Toast.makeText(flight.this,obj.getString("msg"),Toast.LENGTH_SHORT).show();
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
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();     //Show everytime
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}


//    public void booking1(View view)
//    {
////        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, Main_Payment2.class);
//        startActivity(intent);
//    }
