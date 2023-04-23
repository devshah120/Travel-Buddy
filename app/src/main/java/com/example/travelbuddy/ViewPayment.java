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

public class ViewPayment extends AppCompatActivity {

    private static final String TAG = "ViewPayment";
    private Button getPayment;
    private RecyclerView recyclerView;
    private ArrayList<PaymentData> arrayList;
    public loading_1 load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payment);
        load = new loading_1(this);

//        getPayment=findViewById(R.id.userpayment);
        recyclerView = findViewById(R.id.recyclerpayment);
        arrayList = new ArrayList<>();
//        getPayment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
                PaymentMethod paymentMethod = RetrofitClient.getRetrofitInstance().create(PaymentMethod.class);
                Call<PaymentModel> call = paymentMethod.getAllPayment();
                Loadingdsfsdf();
                call.enqueue(new Callback<PaymentModel>() {
                    @Override
                    public void onResponse(Call<PaymentModel> call, Response<PaymentModel> response) {
                        Log.e(TAG, "onResponse: code: "+response.code());

                        ArrayList<PaymentModel.cards> cards = response.body().getCards();

                        for (PaymentModel.cards cards1:cards){
                            Log.e(TAG, "onResponse: Card Name: "+cards1.getCardname());
                            arrayList.add(new PaymentData(cards1.getCardname(),cards1.getCardnumber(),cards1.getExpdate(),cards1.getCvv(),cards1.getUser(),cards1.getCreatedAt()));
                        }
                        RecyclerPaymentAdapter recyclerPaymentAdapter = new RecyclerPaymentAdapter(arrayList);
                        recyclerView.setAdapter(recyclerPaymentAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ViewPayment.this));
                    }

                    @Override
                    public void onFailure(Call<PaymentModel> call, Throwable t) {
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