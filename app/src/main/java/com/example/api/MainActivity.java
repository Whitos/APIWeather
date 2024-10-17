package com.example.api;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.api.databinding.ActivityMainBinding;

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

        OpenWeatherServices service =
                RetrofitClientInstance.getRetrofitInstance().create(OpenWeatherServices.
                        class);

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

    private void searchWeatherByCity(OpenWeatherServices service, String cityName, String apiKey) {
        Call<Forecast> call = service.getWeatherByCity(cityName, apiKey);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Forecast forecast = response.body();
                    Weather weather = forecast.getWeather();
                    binding.editTextNameCity.setText(forecast.getCityName());
                    binding.textViewTemperature.setText(String.format(Locale.getDefault(), "%.0f°C", weather.getTemp() - 273.15));
                    binding.textViewMinAffiche.setText(String.format(Locale.getDefault(), "%.0f°C", weather.getTempMin() - 273.15));
                    binding.textViewMaxAffiche.setText(String.format(Locale.getDefault(), "%.0f°C", weather.getTempMax() - 273.15));
                } else {
                    Toast.makeText(MainActivity.this, "Ville non trouvée ou erreur du serveur", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Une erreur est survenue", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
