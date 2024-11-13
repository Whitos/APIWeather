package com.example.api.Network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceResponse {

    @SerializedName("results")
    private List<Place> places;

    // Getters and setters
    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    // Classe interne représentant un lieu (Place)
    public static class Place {
        @SerializedName("name")
        private String name;

        @SerializedName("address")
        private String address;

        // Getters et setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
