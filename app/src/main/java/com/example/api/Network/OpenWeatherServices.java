package com.example.api.Network;

import com.example.api.Model.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherServices {
    @GET("weather")
    Call<Forecast> getWeatherByCity(
            @Query("q") String cityName,
            @Query("appid") String apiKey
    );

    @GET("forecast")
    Call<Forecast> getForecastByCity(
            @Query("q") String cityName,
            @Query("appid") String apiKey
    );
}

