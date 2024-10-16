package com.example.api;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Weather implements Serializable {
    @SerializedName("temp")
    private double temp;
    @SerializedName("feels_like")
    private double feelsLike;
}
