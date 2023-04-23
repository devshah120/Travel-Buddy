package com.example.travelbuddy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main_Payment2 extends AppCompatActivity {

    private Button proceed;
    private EditText cardName_ET,cardNmber_ET,exp_ET,cvv_ET;
    private  String cardname,cardnumber,expdate,cvv;
    UtilService utilService;
    SharedPreferenceClass sharedPreferenceClass;
    private static final String CHANNEL_ID = "MY Channel";
    private static final int NOTIFICATION_ID = 100;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_payment2);


        proceed = findViewById(R.id.book6);

        cardName_ET= findViewById(R.id.editTextTextPersonName);
        cardNmber_ET = findViewById(R.id.editTextPhone3);
        exp_ET = findViewById(R.id.editTextDate);
        cvv_ET = findViewById(R.id.editTextPhone2);
        utilService = new UtilService();
        String mail=getIntent().getStringExtra("email");
        String fname=getIntent().getStringExtra("fname");
        String lname=getIntent().getStringExtra("lname");
        String contact=getIntent().getStringExtra("contact");
        String checkin=getIntent().getStringExtra("checkin");
        String checkout=getIntent().getStringExtra("checkout");
        String place=getIntent().getStringExtra("place");
        String name=getIntent().getStringExtra("name");
        String from=getIntent().getStringExtra("from");
        String to=getIntent().getStringExtra("to");
        String date=getIntent().getStringExtra("date");
        String time=getIntent().getStringExtra("time");
        String person=getIntent().getStringExtra("people");
        String classs=getIntent().getStringExtra("classs");
        sharedPreferenceClass = new SharedPreferenceClass(this);
        token = sharedPreferenceClass.getValue_String("token");

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String stringSenderEmail = "travelbuddyapp0@gmail.com";
                    String stringReceiverEmail = mail;
                    String stringPasswordSenderEmail = "nappzxcihckqpcud";

                    String stringHost = "smtp.gmail.com";

                    Properties properties = System.getProperties();

                    properties.put("mail.smtp.host", stringHost);
                    properties.put("mail.smtp.port", "465");
                    properties.put("mail.smtp.ssl.enable","true");
                    properties.put("mail.smtp.auth","true");

                    javax.mail.Session session = Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(stringSenderEmail,stringPasswordSenderEmail);

                        }
                    });
                    Thread thread;

                    MimeMessage mimeMessage= new MimeMessage(session);
                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));
                    mimeMessage.setSubject("Get Ready to Travel: Your Booking is Complete");
                    if (place != null) {

                        mimeMessage.setText("Dear "+ fname +",\n" +
                                "\n" +
                                "We are excited to confirm your upcoming trip with us. Below are the details of your booking:\n" +
                                "\n" +
                                "Name of Passenger(s):"+fname+" "+lname+"\n" +
                                "Contact No of Passenger(s):"+contact+"\n" +
                                "Hotel Details:  Hotel Name: "+place+", Check-in Date and Time:"+checkin+", Check-out Date and Time:"+checkout+",\n" +
                                "\n" +
                                "We recommend that you review the details of your trip carefully to ensure that all information is correct. If you notice any discrepancies, please contact us immediately to make any necessary changes.\n" +
                                "\n" +
                                "Please note that your itinerary may be subject to change due to circumstances beyond our control, such as flight delays or cancellations. If any changes occur, we will notify you promptly.\n" +
                                "\n" +
                                "If you have any questions or concerns about your trip, please do not hesitate to contact us. Our customer service team is available 24/7 to assist you.\n" +
                                "\n" +
                                "Thank you for choosing Travel Buddy for your travel needs. We look forward to providing you with a memorable trip experience.\n" +
                                "\n" +
                                "Best regards,\n" +
                                "Travel Buddy");
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Transport.send(mimeMessage);
                                } catch (MessagingException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });

                    } else {
                        mimeMessage.setText("Dear "+ name +",\n" +
                                "We are excited to confirm your upcoming trip with us. Below are the details of your booking:\n" +
                                "Mode of Travel: [Flight/Train/Bus]\n" +
                                "Departure Date: "+date+ "\n" +
                                "Departure Time: "+time+"\n" +
                                "From: "+from+"\n" +
                                "To:"+to+"\n" +
                                "Traveller(s) :"+person+"\n" +
                                "Class:"+classs+"\n" +
                                "\n" +
                                "Please note that your ticket is now confirmed.\n" +
                                "\n" +
                                "In the meantime, if you have any questions or concerns about your reservation, please don't hesitate to contact us.\n" +
                                "\n" +
                                "We recommend that you review the details of your trip carefully to ensure that all information is correct. If you notice any discrepancies, please contact us immediately to make any necessary changes.\n" +
                                "\n" +
                                "Please note that your itinerary may be subject to change due to circumstances beyond our control, such as flight delays or cancellations. If any changes occur, we will notify you promptly.\n" +
                                "\n" +
                                "Thank you for choosing Travel Buddy for your travel needs. We look forward to providing you with a memorable trip experience.\n" +
                                "\n" +
                                "Best regards,\n" +
                                "Travel Buddy");
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Transport.send(mimeMessage);
                                } catch (MessagingException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });

                    }
                    thread.start();

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }

                utilService.hideKeyboard(view,Main_Payment2.this);
                cardname=cardName_ET.getText().toString();
                cardnumber=cardNmber_ET.getText().toString();
                expdate = exp_ET.getText().toString();
                cvv=cvv_ET.getText().toString();
//                navifdsffddasdsa(view);
//                if(validate(view)){
                reservationUser(view);
//                }
            }
        });
    }

    public void onErrorResponse(VolleyError error) {
        if (error instanceof AuthFailureError) {
            // Handle authentication errors
        } else if (error instanceof ServerError) {
            // Handle server errors
        } else {
            // Handle other errors
        }
    }
    private void reservationUser(View view) {

//        Toast.makeText(Main_Payment1.this, "Reservation SFGHDFGHDSFHG", Toast.LENGTH_SHORT).show();

        HashMap<String,String> params= new HashMap<>();
        params.put("cardname",cardname);
        params.put("cardnumber",cardnumber);
        params.put("expdate",expdate);
        params.put("cvv",cvv);

        String apiKey = "https://travel-buddy-api-production-8b1b.up.railway.app/api/payment";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                apiKey, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(Main_Payment2.this, "Reservation Done", Toast.LENGTH_SHORT).show();
                Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.logo,null);

                BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
                Bitmap largeIcon = bitmapDrawable.getBitmap();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notification = new Notification.Builder(Main_Payment2.this)
                            .setSmallIcon(R.drawable.logo)
                            .setContentText("Thank You For Booking Please Check Mail!!")
                            .setSubText("New Message from Travel Buddy")
                            .setChannelId(CHANNEL_ID)
                            .build();

                    notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"Payment Channel",NotificationManager.IMPORTANCE_HIGH));
                }
                else {
                    notification = new Notification.Builder(Main_Payment2.this)
                            .setSmallIcon(R.drawable.logo)
                            .setContentText("New Message")
                            .setSubText("New Message from Travel Buddy")
                            .build();
                }
                notificationManager.notify(NOTIFICATION_ID,notification);

                try
                {
                    if (response.getBoolean("success"))
                    {
                        String token = response.getString("token");
                        sharedPreferenceClass.setValue_String("token",token);
                        Toast.makeText(Main_Payment2.this, "Reservation TRY", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(Main_Payment2.this,sucessfull.class));
//                        navifdsffd(view);
//                        navifdsffddasdsa(view);

                    }
                }
                catch (JSONException e)
                {
//                    throw new RuntimeException(e);
//                    Toast.makeText(Main_Payment2.this, "Reservation CATCH", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main_Payment2.this, sucessfull_NEW.class);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                Toast.makeText(Main_Payment2.this, "ERROR Responece", Toast.LENGTH_SHORT).show();

                if (error instanceof ServerError && response != null){
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers,"utf-8"));

                        JSONObject obj = new JSONObject(res);
                        Toast.makeText(Main_Payment2.this,obj.getString("msg"),Toast.LENGTH_SHORT).show();
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

    public boolean validate(View view){
        boolean isValid = false;

        if (!TextUtils.isEmpty(cardname)){
            if (!TextUtils.isEmpty(cardnumber)){
                if (!TextUtils.isEmpty(expdate)){
                    if (!TextUtils.isEmpty(cvv)){
                    }else {
                        utilService.showSnackBar(view,"Enter Contact No");
                        isValid = false;
                    }
                }else {
                    utilService.showSnackBar(view,"Enter Email Address");
                    isValid = false;
                }
            }else {
                utilService.showSnackBar(view,"Enter Last Name");
                isValid = false;
            }
        }else {
            utilService.showSnackBar(view,"Enter First Name");
            isValid = false;
        }
        return isValid;
    }


    //Homepage
    public void navifdsffddasdsa(View view)
    {
//        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
//        reservationUser(view);
        Intent intent = new Intent(this, sucessfull.class);
        startActivity(intent);
    }


    // Admin Login
//    public void admin(View view)
//    {
////        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, admin_login.class);
//        startActivity(intent);
//    }

}