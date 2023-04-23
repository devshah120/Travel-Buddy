package com.example.travelbuddy;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlightMethod {
    @GET("api/flight")
    Call<FlightModel> getAllFlight();
}
