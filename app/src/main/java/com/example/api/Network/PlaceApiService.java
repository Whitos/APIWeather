package com.example.api.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceApiService {
    @GET("place/search") // Remplacez l'URL par celle correspondant à votre API
    Call<PlaceResponse> getPlaceDetails(
            @Query("city") String city,
            @Query("apiKey") String apiKey
    );
}
