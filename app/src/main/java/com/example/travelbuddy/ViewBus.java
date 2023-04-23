package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewBus extends AppCompatActivity {
    private ArrayList<BusData> arrayList;
    private RecyclerView recyclerView;
    private static final String TAG = "ViewBus";
    public loading_1 load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bus);
        load = new loading_1(this);

        recyclerView=findViewById(R.id.recyclerpayment);
        arrayList=new ArrayList<>();

        BusMethod busMethod = RetrofitClient.getRetrofitInstance().create(BusMethod.class);
        Call<BusModel> call = busMethod.getAllBus();
        Loadingdsfsdf();
        call.enqueue(new Callback<BusModel>() {
            @Override
            public void onResponse(Call<BusModel> call, Response<BusModel> response) {
                Log.e(TAG, "onResponse: code: "+response.code());

                ArrayList<BusModel.data> data= response.body().getData();

                for (BusModel.data data1:data){
                    Log.e(TAG, "onResponse: All Name:"+data1.getClasss() );
                    arrayList.add(new BusData(data1.getName(),data1.getEmail(),data1.getFrom(),data1.getTo(),data1.getUser(),data1.getPeople(),data1.getDate(),data1.getTime(),data1.getClasss()));
                }
                RecyclerBusAdapter recyclerBusAdapter = new RecyclerBusAdapter(arrayList);
                recyclerView.setAdapter(recyclerBusAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ViewBus.this));
            }

            @Override
            public void onFailure(Call<BusModel> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());

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