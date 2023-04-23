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

public class ViewFlight extends AppCompatActivity {
    private ArrayList<FlightData> arrayList;
    private static final String TAG = "ViewFlight";
    private RecyclerView recyclerView;
    public loading_1 load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flight);
        load = new loading_1(this);

        recyclerView=findViewById(R.id.recyclerpayment);
        arrayList=new ArrayList<>();

        FlightMethod flightMethod = RetrofitClient.getRetrofitInstance().create(FlightMethod.class);
        Call<FlightModel> call = flightMethod.getAllFlight();
        Loadingdsfsdf();
        call.enqueue(new Callback<FlightModel>() {
            @Override
            public void onResponse(Call<FlightModel> call, Response<FlightModel> response) {
                Log.e(TAG, "onResponse: code: "+response.code());

                ArrayList<FlightModel.data> data= response.body().getData();

                for (FlightModel.data data1:data){
                    Log.e(TAG, "onResponse: All Name:"+data1.getClasss());
                    arrayList.add(new FlightData(data1.getName(),data1.getEmail(),data1.getFrom(),data1.getTo(),data1.getUser(),data1.getPeople(),data1.getDate(),data1.getTime(),data1.getClasss()));
                }
                RecyclerFlightAdapter recyclerFlightAdapter = new RecyclerFlightAdapter(arrayList);
                recyclerView.setAdapter(recyclerFlightAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ViewFlight.this));
            }

            @Override
            public void onFailure(Call<FlightModel> call, Throwable t) {
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