package com.example.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenWeatherServices {
    @GET("weather?q=London&appid=e075a0b59517e88cc46940bb262add13")
    Call<Forecast> getForcast();
}
