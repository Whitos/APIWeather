package com.example.api.Model;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Forecast implements Serializable {
    @SerializedName("main")
    Weather weather;
    @SerializedName("dt")
    private String datetime;
    @SerializedName("name")
    private String cityName;
    @SerializedName("coord")
    private Coord coord;

    public Forecast(Weather weather, String datetime) {
        this.weather = weather;
        this.datetime = datetime;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDateTime() {
        return datetime;
    }

    public Weather getWeather() {
        return weather;
    }

}


