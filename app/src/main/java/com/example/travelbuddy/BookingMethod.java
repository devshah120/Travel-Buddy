package com.example.travelbuddy;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookingMethod {

    @GET("api/reservation")
    Call<BookingModel> getAllBooking();
}
