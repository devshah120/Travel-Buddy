package com.example.travelbuddy;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BusMethod {
    @GET("api/bus")
    Call<BusModel> getAllBus();
}
