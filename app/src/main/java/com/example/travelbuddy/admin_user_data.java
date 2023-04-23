package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class admin_user_data extends AppCompatActivity {
    private RecyclerView recyclerView;
    public loading_1 load;
//    ArrayList<UserdataModel> arrayList;
    private static final String TAG = "admin_user_data";
    private Button getData;
    private ArrayList<Data> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        load = new loading_1(this);

//        getData = findViewById(R.id.userdata);
        recyclerView = findViewById(R.id.recycleruserdata);
        arrayList = new ArrayList<>();
//        getData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
                Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<UserdataModel> call = methods.getAllData();

            Loadingdsfsdf();

                call.enqueue(new Callback<UserdataModel>() {
                    @Override
                    public void onResponse(Call<UserdataModel> call, Response<UserdataModel> response) {
                        Log.e(TAG, "onResponse: code: " +response.code() );
                        ArrayList<UserdataModel.user> user = response.body().getUser();


                        for (UserdataModel.user user1:user){
                            Log.e(TAG, "onResponse: All Output: Emails: "+user1.getEmail() );
                            arrayList.add(new Data(user1.getFname(),user1.getLname(),user1.getContactno(),user1.getEmail()));
                        }

                        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(arrayList);
                        recyclerView.setAdapter(recyclerAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(admin_user_data.this));
                    }

                    @Override
                    public void onFailure(Call<UserdataModel> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getMessage());

                    }
                });
//            }
//        });


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