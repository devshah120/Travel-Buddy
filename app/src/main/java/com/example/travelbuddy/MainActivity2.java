package com.example.travelbuddy;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Header;
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
import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.validation.Validator;

public class MainActivity2 extends AppCompatActivity {
    private Button registerBtn;
    private EditText fname_ET,lname_ET,email_ET,contactno_ET,password_ET,confpass_ET;
    private  String fname,lname,email,contactno,password,confpass;
    UtilService utilService;
    SharedPreferenceClass sharedPreferenceClass;
    private static final String CHANNEL_ID = "MY Channel";
    private static final int NOTIFICATION_ID = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        registerBtn = findViewById(R.id.register);
        fname_ET = findViewById(R.id.fname_ET);
        lname_ET = findViewById(R.id.lname_ET);
        email_ET = findViewById(R.id.email_ET);
        contactno_ET = findViewById(R.id.contactno_ET);
        password_ET = findViewById(R.id.password_ET);
        confpass_ET = findViewById(R.id.confpass_ET);
        utilService = new UtilService();
        sharedPreferenceClass = new SharedPreferenceClass(this);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utilService.hideKeyboard(view,MainActivity2.this);
                fname = fname_ET.getText().toString();
                lname = lname_ET.getText().toString();
                email = email_ET.getText().toString();
                contactno = contactno_ET.getText().toString();
                password = password_ET.getText().toString();
                confpass = confpass_ET.getText().toString();
                if(validate(view)){
                    registerUser(view);
                }
                try {
                    String stringSenderEmail = "travelbuddyapp0@gmail.com";
                    String stringReceiverEmail = email;
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
                    mimeMessage.setSubject("Welcome to Travel Buddy");
                        mimeMessage.setText("Dear "+fname+" "+lname+",\n" +
                                "\n" +
                                "Thank you for creating an account with our travel application! We are excited to have you as a member of our community, and we look forward to providing you with a seamless and convenient travel experience.\n" +
                                "\n" +
                                "Your account has been successfully created, and you can now log in to the application using the following credentials:\n" +
                                "\n" +
                                "Email: "+email+"\n" +
                                "Password:"+password+"\n" +
                                "\n" +
                                "We recommend that you change your password as soon as possible to ensure the security of your account.\n" +
                                "\n" +
                                "With your account, you can book Flights, Hotels, and other travel-related services quickly and easily. You can also access our Travel AR models, AI Based Travel ChatBot, all from the convenience of your mobile device.\n" +
                                "\n" +
                                "If you have any questions or concerns about your account, please don't hesitate to contact us. We are always here to help.\n" +
                                "\n" +
                                "Thank you for choosing travel Buddy. We look forward to providing you with a hassle-free and enjoyable travel experience.\n" +
                                "\n" +
                                "Best regards,\n" +
                                "Travel Buddy \n");
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

                    thread.start();

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private void registerUser(View view) {
        HashMap<String,String> params= new HashMap<>();
        params.put("fname",fname);
        params.put("lname",lname);
        params.put("email",email);
        params.put("contactno",contactno);
        params.put("password",password);
        params.put("confpass",confpass);

        String apiKey = "https://travel-buddy-api-production-8b1b.up.railway.app/api/travel/auth/register";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                apiKey, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")){
                        String token = response.getString("token");
                        sharedPreferenceClass.setValue_String("token",token);
                        Toast.makeText(MainActivity2.this, "Successfully Account Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity2.this,MainActivity.class));
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null){
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers,"utf-8"));

                        JSONObject obj = new JSONObject(res);
                        Toast.makeText(MainActivity2.this,obj.getString("msg"),Toast.LENGTH_SHORT).show();
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

                return params;
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
        boolean isValid;

        if (!TextUtils.isEmpty(fname)){
            if (!TextUtils.isEmpty(lname)){
                if (!TextUtils.isEmpty(email)){
                    if (!TextUtils.isEmpty(contactno)){
                        if (!TextUtils.isEmpty(password)){
                            if (!TextUtils.isEmpty(confpass)){
                                isValid=true;
                            }else {
                                utilService.showSnackBar(view,"Enter Confirm Password");
                                isValid = false;
                            }
                        }else {
                            utilService.showSnackBar(view,"Enter Password");
                            isValid = false;
                        }
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

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
        if (pref.contains("token")){
            startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            finish();
        }
    }
}