package com.example.travelbuddy;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Methods {
    @GET("api/travel/auth")
    Call<UserdataModel> getAllData();
}
