package com.example.travelbuddy;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PaymentMethod {

    @GET("api/payment")
    Call<PaymentModel> getAllPayment();
}
