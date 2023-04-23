package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button registerBtn,loginBtn;
    private EditText email_ET,password_ET;
    private  String email,password;
    UtilService utilService;
    public loading_1 load;
    SharedPreferenceClass sharedPreferenceClass;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load = new loading_1(this);

        if (!isConnected(this)) {
            showInternetDialog();
        }

        registerBtn = findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Creating Account", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        loginBtn = findViewById(R.id.loginBtn);
        email_ET = findViewById(R.id.email_ET);
        password_ET = findViewById(R.id.password_ET);
        utilService = new UtilService();
        sharedPreferenceClass = new SharedPreferenceClass(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                // loading
//                load.show();
//                Handler handler = new Handler();
//                Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        load.cancel();
//                    }
//                };
//                handler.postDelayed(runnable, 2000);

                utilService.hideKeyboard(view,MainActivity.this);
                email = email_ET.getText().toString();
                password = password_ET.getText().toString();
                if(validate(view)){
                    Loadingdsfsdf();
                    loginUser(view);
                }
            }
        });
    }

    public void Loadingdsfsdf(){
//         loading
        load.show();
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                load.cancel();
            }
        };
        handler.postDelayed(runnable, 2000);
    }

    private void showInternetDialog() {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        View view = LayoutInflater.from(this).inflate(R.layout.no_internet_dialog, findViewById(R.id.no_internet_layout));
        view.findViewById(R.id.try_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConnected(MainActivity.this)) {
                    showInternetDialog();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        });

        dialog.setContentView(view);
        dialog.show();
    }

    private boolean isConnected(MainActivity mainActivity) {
        ConnectivityManager connectivityManager =(ConnectivityManager) mainActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected());

    }

    private void loginUser(View view) {
        HashMap<String,String> params= new HashMap<>();
        params.put("email",email);
        params.put("password",password);

        String apiKey = "https://travel-buddy-api-production-8b1b.up.railway.app/api/travel/auth/login";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                apiKey, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success")){
//                        Loadingdsfsdf();

                        String token = response.getString("token");
                        sharedPreferenceClass.setValue_String("token",token);
                        Toast.makeText(MainActivity.this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,MainActivity3.class));
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
                        Toast.makeText(MainActivity.this,obj.getString("msg"),Toast.LENGTH_SHORT).show();
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

    private boolean validate(View view) {
        boolean isValid;

        if (!TextUtils.isEmpty(email)){
            if (!TextUtils.isEmpty(password)){
                isValid=true;
            }else {
                utilService.showSnackBar(view,"Please Enter Password");
                isValid=false;
            }
        }else {
            utilService.showSnackBar(view,"Please Enter Email");
            isValid=false;
        }
        return isValid;
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
        if (pref.contains("token")){
            startActivity(new Intent(MainActivity.this, MainActivity3.class));
            finish();
        }
    }

    //Homepage
    public void Home(View view)
    {
        Loadingdsfsdf();
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    // Admin Login
    public void admin(View view)
    {
//        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, admin_login.class);
        startActivity(intent);
    }

}