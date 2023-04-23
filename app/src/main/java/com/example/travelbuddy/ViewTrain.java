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

public class ViewTrain extends AppCompatActivity {
    private ArrayList<TrainData> arrayList;
    private RecyclerView recyclerView;
    private static final String TAG = "ViewTrain";
    public loading_1 load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_train);
        load = new loading_1(this);

        recyclerView=findViewById(R.id.recyclerpayment);
        arrayList=new ArrayList<>();

        TrainMethod trainMethod = RetrofitClient.getRetrofitInstance().create(TrainMethod.class);
        Call<TrainModel> call = trainMethod.getAllTrain();
        Loadingdsfsdf();
        call.enqueue(new Callback<TrainModel>() {
            @Override
            public void onResponse(Call<TrainModel> call, Response<TrainModel> response) {
                Log.e(TAG, "onResponse: code: "+response.code());

                ArrayList<TrainModel.data> data= response.body().getData();

                for (TrainModel.data data1:data){
                    Log.e(TAG, "onResponse: All Name:"+data1.getClasss() );
                    arrayList.add(new TrainData(data1.getName(),data1.getEmail(),data1.getFrom(),data1.getTo(),data1.getUser(),data1.getPeople(),data1.getDate(),data1.getTime(),data1.getClasss()));
                }
                RecyclerTrainAdapter recyclerTrainAdapter = new RecyclerTrainAdapter(arrayList);
                recyclerView.setAdapter(recyclerTrainAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ViewTrain.this));
            }

            @Override
            public void onFailure(Call<TrainModel> call, Throwable t) {
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