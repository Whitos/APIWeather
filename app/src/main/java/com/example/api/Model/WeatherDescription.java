package com.example.api.Model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class WeatherDescription implements Serializable {
    @SerializedName("main")
    private String main;  // description courte (e.g., "Clear", "Clouds", "Rain")

    @SerializedName("description")
    private String description;  // description détaillée (e.g., "clear sky", "light rain")

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}