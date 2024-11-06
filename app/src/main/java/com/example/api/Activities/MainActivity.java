package com.example.api.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.api.Model.Forecast;
import com.example.api.Model.ActivitySuggestionAdapter;
import com.example.api.Model.Suggestion;
import com.example.api.Model.Weather;
import com.example.api.Network.OpenWeatherServices;
import com.example.api.Network.RetrofitClientInstance;
import com.example.api.R;
import com.example.api.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.activityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OpenWeatherServices service = RetrofitClientInstance.getRetrofitInstance().create(OpenWeatherServices.class);
        String apiKey = "3ade1e62d4d802c0908f1614ae422cf0";


        binding.imageViewSearch.setOnClickListener(v -> {
            String cityName = binding.editTextNameCity.getText().toString().trim();
            if (!cityName.isEmpty()) {
                searchWeatherByCity(service, cityName, apiKey);
            } else {
                Toast.makeText(MainActivity.this, "Veuillez entrer un nom de ville", Toast.LENGTH_SHORT).show();
            }
        });

        binding.editTextNameCity.setOnEditorActionListener((TextView v, int actionId, android.view.KeyEvent event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event != null && event.getKeyCode() == android.view.KeyEvent.KEYCODE_ENTER && event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                String cityName = binding.editTextNameCity.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    searchWeatherByCity(service, cityName, apiKey);
                } else {
                    Toast.makeText(MainActivity.this, "Veuillez entrer un nom de ville", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        });
    }

    private List<Suggestion> getActivitySuggestions(Weather weather) {
        List<Suggestion> suggestions = new ArrayList<>();
        double tempCelsius = weather.getTemp() - 273.15;

        // Vérifier si la description de la météo est non nulle avant de l'utiliser
        String description = weather.getDescription();

        // Ajouter des suggestions en fonction de la température
        if (description == null || description.isEmpty()) {
            // Suggestions par défaut selon la température
            if (tempCelsius > 30) { // Très chaud
                suggestions.add(new Suggestion("Plage", "Profitez d'une journée à la plage.", R.drawable.beach_icon));
                suggestions.add(new Suggestion("Randonnée en montagne", "Si vous aimez les températures élevées, allez faire une randonnée.", R.drawable.mountain_hike_icon));
                suggestions.add(new Suggestion("Piscine", "Rien de mieux que de se baigner pour se rafraîchir.", R.drawable.pool_icon));
            } else if (tempCelsius > 20 && tempCelsius <= 30) { // Température modérée
                suggestions.add(new Suggestion("Pique-nique", "Profitez du beau temps pour un pique-nique en plein air.", R.drawable.picnic_icon));
                suggestions.add(new Suggestion("Randonnée", "Parfait pour une randonnée en nature.", R.drawable.hiking_icon));
                suggestions.add(new Suggestion("Visite en plein air", "Explorez la ville à pied sous un ciel clair.", R.drawable.city_walk_icon));
            } else if (tempCelsius > 10 && tempCelsius <= 20) { // Température fraîche
                suggestions.add(new Suggestion("Balade en ville", "Explorez la ville par un temps agréable.", R.drawable.city_walk_icon));
                suggestions.add(new Suggestion("Café", "Un bon café en terrasse pour profiter de l'air frais.", R.drawable.coffee_icon));
                suggestions.add(new Suggestion("Musée", "Visitez un musée pour une activité tranquille.", R.drawable.museum_icon));
            } else { // Température froide
                suggestions.add(new Suggestion("Lecture", "Parfait pour rester au chaud avec un bon livre.", R.drawable.reading_icon));
                suggestions.add(new Suggestion("Cinéma", "Profitez d'un film au chaud.", R.drawable.cinema_icon));
                suggestions.add(new Suggestion("Sport a la maison", "Un moment pour s'entrainer et exterioriser.", R.drawable.sport_icon));
            }
        } else {
            // Suggestions basées sur la description de la météo
            if (tempCelsius > 30) { // Très chaud
                suggestions.add(new Suggestion("Plage", "Profitez d'une journée à la plage.", R.drawable.beach_icon));
                suggestions.add(new Suggestion("Piscine", "Se rafraîchir dans une piscine est une bonne idée.", R.drawable.pool_icon));
            } else if (description.contains("clear") && tempCelsius > 20) { // Temps clair et chaud
                suggestions.add(new Suggestion("Randonnée", "Parfait pour une randonnée sous un ciel dégagé.", R.drawable.hiking_icon));
                suggestions.add(new Suggestion("Pique-nique", "Un pique-nique au soleil est idéal.", R.drawable.picnic_icon));
            } else if (description.contains("rain") || tempCelsius <= 10) { // Pluie ou froid
                suggestions.add(new Suggestion("Cinéma", "Un bon film au chaud est parfait pour ce temps.", R.drawable.cinema_icon));
                suggestions.add(new Suggestion("Musée", "Visitez un musée pour rester au sec.", R.drawable.museum_icon));
                suggestions.add(new Suggestion("Lecture", "Un bon livre à l'intérieur est une excellente idée.", R.drawable.reading_icon));
            }
        }

        return suggestions;
    }

    private void searchWeatherByCity(OpenWeatherServices service, String cityName, String apiKey) {
        // Faire un appel à l'API pour obtenir les prévisions météo de la ville
        Call<Forecast> call = service.getWeatherByCity(cityName, apiKey);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.isSuccessful() && response.body() != null) {

                    Forecast forecast = response.body();
                    Weather weather = forecast.getWeather();
                    Log.d("WeatherResponse", "Weather: " + response.body().toString());

                    if (weather.getDescription() != null && !weather.getDescription().isEmpty()) {
                        String description = weather.getDescription();
                        Log.d("WeatherDescription", "Description: " + description);
                    }
                    binding.editTextNameCity.setText(forecast.getCityName());
                    binding.textViewTemperature.setText(String.format(Locale.getDefault(), "%.0f°C", weather.getTemp() - 273.15));

                    List<Suggestion> activitySuggestions = getActivitySuggestions(weather);
                    Log.d("ActivitySuggestions", "Suggestions size: " + activitySuggestions.size());

                    ActivitySuggestionAdapter adapter = new ActivitySuggestionAdapter(activitySuggestions);
                    binding.activityRecyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(MainActivity.this, "Ville non trouvée ou erreur du serveur", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                // En cas d'erreur réseau ou d'échec de l'appel API
                Toast.makeText(MainActivity.this, "Une erreur est survenue", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
