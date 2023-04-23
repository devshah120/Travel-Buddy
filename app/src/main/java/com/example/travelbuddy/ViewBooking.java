package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewBooking extends AppCompatActivity   {

    private static final String TAG = "ViewBooking";
    private Button getBooking;
    private ArrayList<BookingData> arrayList;
    private RecyclerView recyclerView;
    public loading_1 load;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);
        load = new loading_1(this);

//        getBooking=findViewById(R.id.userbooking);
        recyclerView = findViewById(R.id.recyclerbooking);
        arrayList = new ArrayList<>();
//        getBooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
                BookingMethod bookingMethod = RetrofitClient.getRetrofitInstance().create(BookingMethod.class);
                Call<BookingModel> call = bookingMethod.getAllBooking();
                Loadingdsfsdf();
                call.enqueue(new Callback<BookingModel>() {
                    @Override
                    public void onResponse(Call<BookingModel> call, Response<BookingModel> response) {
                        Log.e(TAG, "onResponse: code : "+ response.code());

                        ArrayList<BookingModel.cards> cards = response.body().getCards();

                        for(BookingModel.cards cards1:cards){
                            Log.e(TAG, "onResponse: Emails: "+cards1.getEmail());
                            arrayList.add(new BookingData(cards1.getFname(),cards1.getLname(),cards1.getEmail(),cards1.getContactno(),cards1.getUser(),cards1.getCheckIn(),cards1.getCheckOut(),cards1.getPlace()));
                        }

                        RecyclerBookingAdapter recyclerBookingAdapter = new RecyclerBookingAdapter(arrayList);
                        recyclerView.setAdapter(recyclerBookingAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ViewBooking.this) {
                        });

                    }

                    @Override
                    public void onFailure(Call<BookingModel> call, Throwable t) {
                        Log.e(TAG, "onFailure : "+ t.getMessage());
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