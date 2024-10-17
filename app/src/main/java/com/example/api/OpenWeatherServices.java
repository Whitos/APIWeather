package com.example.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherServices {
    @GET("weather")
    Call<Forecast> getWeatherByCity(
            @Query("q") String cityName,
            @Query("appid") String apiKey
    );
}
