package com.example.travelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.travelbuddy.UtilsService.UtilService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class admin_user_add_place extends AppCompatActivity {

//    FloatingActionButton floatingActionButton;
//    ImageView floatingActionButton;
    UtilService utilService;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_place);

        button = findViewById(R.id.add_admin);

//        View view = getLayoutInflater().inflate(R.layout.activity_admin_add_place, null);

//        ImageView floatingActionButton = (ImageView) findViewById(R.id.add_task_admin);


//        showAlertDialog();

//        add = findViewById(R.id.add_task_admin);

//                Toast.makeText(admin_user_add_place.this, "show aletdialog", Toast.LENGTH_SHORT).show();
                final EditText title_field = findViewById(R.id.title_admin);
                final EditText desc_field = findViewById(R.id.deesc_admin);
                final EditText place_field = findViewById(R.id.place_admin);
                final EditText price_field = findViewById(R.id.price_admin);
                final Button addAdmin = findViewById(R.id.add_admin);

                addAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(admin_user_add_place.this, "Positive Butons", Toast.LENGTH_SHORT).show();

                        String title = title_field.getText().toString();
                        String dese = desc_field.getText().toString();
                        String place = place_field.getText().toString();
                        String price = price_field.getText().toString();
                        if (!TextUtils.isEmpty(title)) {
                            //addTask(title, dese,place,price);
//                    addAdmin.dismiss();
                            Toast.makeText(admin_user_add_place.this, "Add Task", Toast.LENGTH_SHORT).show();

                            String url = "https://travel-buddy-api-production-8b1b.up.railway.app/api/cards";
//        JSONObject postData = new JSONObject();
                            HashMap<String, String> body = new HashMap<>();
                            body.put("title", title);
                            body.put("description", dese);
                            body.put("place", place);
                            body.put("price",price);
//        postData.put(body);

//        JSONObject parameters = new JSONObject(body);

                            //json
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                                    url, new JSONObject(body), new Response.Listener<JSONObject>() {
                                public void onResponse(JSONObject response) {

                                    try {
//                                        Toast.makeText(admin_user_add_place.this, "Added succesfully", Toast.LENGTH_SHORT).show();

                                        if (response.getBoolean("success")){


                                            Toast.makeText(admin_user_add_place.this, "Added succesfully", Toast.LENGTH_SHORT).show();
//                      startActivity(new Intent(admin_user_add_place.this,admin_user_add_place.class));
                                        }
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    NetworkResponse response = error.networkResponse;
                                    Toast.makeText(admin_user_add_place.this, "ERROR Responece", Toast.LENGTH_SHORT).show();

                                    if (error instanceof ServerError && response != null){
                                        try {
                                            String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers,"utf-8"));
                                            JSONObject obj = new JSONObject(res);
                                            Toast.makeText(admin_user_add_place.this,obj.getString("msg"),Toast.LENGTH_SHORT).show();
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
                                    return headers;
                                }
                            };

                            //set retry policy
                            int socketTime = 3000;
                            RetryPolicy policy = new DefaultRetryPolicy(socketTime,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                            jsonObjectRequest.setRetryPolicy(policy);

                            //request add
                            RequestQueue requestQueue = Volley.newRequestQueue(admin_user_add_place.this);
                            requestQueue.add(jsonObjectRequest);
                        } else {
                            Toast.makeText(admin_user_add_place.this, "Please Enter Title", Toast.LENGTH_SHORT).show();
                        }

//                Toast.makeText(admin_user_add_place.this, "Onclick", Toast.LENGTH_SHORT).show();

            }
        });
//        return view;
    }

    public void addTask(String title, String dese,String place,String price) {

    }
//    public void logout(View view)
//    {
//        Toast.makeText(admin_user_add_place.this, "Logged Out", Toast.LENGTH_SHORT).show();
//        Intent myIntent = new Intent(admin_user_add_place.this, MainActivity.class);   //NEW WAY
//        startActivity(myIntent);
//    }
}