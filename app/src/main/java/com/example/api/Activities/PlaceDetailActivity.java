package com.example.api.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.api.R;
import com.example.api.databinding.ActivityPlaceDetailBinding;

public class PlaceDetailActivity extends AppCompatActivity {

    private ActivityPlaceDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurer le bouton retour dans la Toolbar
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        // Récupérer les données de l'intent
        String placeName = getIntent().getStringExtra("place_name");
        String placeAddress = getIntent().getStringExtra("place_address");
        String placeDescription = getIntent().getStringExtra("place_description");

        // Définir l'icône en fonction de l'activité
        int iconResource = getIconResourceForActivity(placeName);
        binding.activityIcon.setImageResource(iconResource);

        // Afficher les informations
        binding.textViewPlaceName.setText(placeName);
        binding.textViewPlaceAddress.setText(placeAddress);
        binding.textViewPlaceDescription.setText(placeDescription);

        // Configurer le lien Maps
        if (!"À votre domicile".equals(placeAddress)) {
            binding.buttonOpenMaps.setOnClickListener(v -> {
                String url = "https://www.google.com/maps/search/" + Uri.encode(placeAddress);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            });
        } else {
            binding.buttonOpenMaps.setVisibility(View.GONE); // Cache le bouton si c'est "Sport a la maison"
        }
    }

    private int getIconResourceForActivity(String activityName) {
        switch (activityName.toLowerCase()) {
            case "plage":
                return R.drawable.beach_icon;
            case "randonnée":
                return R.drawable.hiking_icon;
            case "pique-nique":
                return R.drawable.picnic_icon;
            case "piscine":
                return R.drawable.pool_icon;
            case "cinéma":
                return R.drawable.cinema_icon;
            case "musée":
                return R.drawable.museum_icon;
            case "lecture":
                return R.drawable.reading_icon;
            case "café":
                return R.drawable.coffee_icon;
            case "sport a la maison":
                return R.drawable.sport_icon;
            default:
                return R.drawable.beach_icon; // Icône par défaut
        }
    }
}
