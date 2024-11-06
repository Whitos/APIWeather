package com.example.api.Model;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class Weather implements Serializable {
    @SerializedName("temp")
    private double temp;
    @SerializedName("feels_like")
    private double feelsLike;
    @SerializedName("temp_min")
    private double tempMin;
    @SerializedName("temp_max")
    private double tempMax;
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("humidity")
    private double humidity;
    @SerializedName("weather")
    private List<WeatherDescription> weatherDescriptions;

    public Weather(List<WeatherDescription> weatherDescriptions) {
        this.weatherDescriptions = weatherDescriptions;
    }


    public double getTemp() {
        return temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public String getDescription() {
        if (weatherDescriptions != null && !weatherDescriptions.isEmpty()) {
            return weatherDescriptions.get(0).getDescription();
        }
        return null;
    }

}
