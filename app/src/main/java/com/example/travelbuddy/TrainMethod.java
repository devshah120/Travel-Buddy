package com.example.travelbuddy;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrainMethod {
    @GET("api/train")
    Call<TrainModel> getAllTrain();
}
