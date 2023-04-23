package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelbuddy.UtilsService.UtilService;

public class admin_login extends AppCompatActivity {

    private Button loginBtn;
    private EditText email_ET,password_ET;
    UtilService utilService;

    public loading_1 load;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        load = new loading_1(this);

        loginBtn = findViewById(R.id.loginBtn);
        email_ET = findViewById(R.id.email_ET);
        password_ET = findViewById(R.id.password_ET);
        utilService = new UtilService();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utilService.hideKeyboard(view,admin_login.this);
                Loadingdsfsdf();
                if(email_ET.getText().toString().equals("admin") && password_ET.getText().toString().equals("admin"))
                {
                    Toast.makeText(admin_login.this,"Admin Successfully Logged In",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(admin_login.this, Dashboard_Admin.class);   //NEW WAY
                    startActivity(myIntent);
                }
                else {
                    Toast.makeText(admin_login.this,"Wrong Username or password",Toast.LENGTH_SHORT).show();
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
}