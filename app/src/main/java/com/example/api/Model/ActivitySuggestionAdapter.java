package com.example.api.Model;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.Activities.PlaceDetailActivity;
import com.example.api.R;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ActivitySuggestionAdapter extends RecyclerView.Adapter<ActivitySuggestionAdapter.ViewHolder> {
    private final List<Suggestion> suggestions;
    private final Map<String, String> activityAddresses;
    private final Map<String, String> activityDescriptions;

    public ActivitySuggestionAdapter(List<Suggestion> suggestions) {
        this.suggestions = suggestions;

        // Initialiser les maps avec les adresses et descriptions détaillées
        this.activityAddresses = new HashMap<>();
        this.activityDescriptions = new HashMap<>();

        // Remplir les maps avec les détails pour chaque activité
        activityAddresses.put("Plage", "2 Avenue du Lac, 74000 Annecy");
        activityAddresses.put("Randonnée", "Sentier du Semnoz, 74000 Annecy");
        activityAddresses.put("Pique-nique", "Parc Charles Bosson, 74000 Annecy");
        activityAddresses.put("Piscine", "Place des Romains, 74000 Annecy");
        activityAddresses.put("Cinéma", "7 Rue de la République, 74000 Annecy");
        activityAddresses.put("Musée", "1 Place du Château, 74000 Annecy");
        activityAddresses.put("Lecture", "4 Rue du Pâquier, 74000 Annecy");
        activityAddresses.put("Café", "15 Rue Royale, 74000 Annecy");
        activityAddresses.put("Sport a la maison", "À votre domicile");

        activityDescriptions.put("Plage", "Profitez de la plage d'Annecy, l'une des plus belles plages lacustres d'Europe. Baignade surveillée en été, activités nautiques et vue imprenable sur les montagnes.");
        activityDescriptions.put("Randonnée", "Parcours balisé offrant une vue panoramique sur le lac d'Annecy et les Alpes. Difficulté moyenne, prévoir 3-4h de marche.");
        activityDescriptions.put("Pique-nique", "Magnifique parc au bord du lac avec tables de pique-nique, aire de jeux pour enfants et pelouse ombragée.");
        activityDescriptions.put("Piscine", "Piscine municipale couverte avec bassin olympique, pataugeoire et espace bien-être. Cours de natation disponibles.");
        activityDescriptions.put("Cinéma", "Cinéma moderne proposant les dernières sorties en VO et VF. Tarif réduit le mercredi.");
        activityDescriptions.put("Musée", "Découvrez l'histoire d'Annecy dans ce château médiéval. Collections permanentes et expositions temporaires toute l'année.");
        activityDescriptions.put("Lecture", "Bibliothèque municipale avec grand choix de livres, magazines et espace multimédia. Wifi gratuit.");
        activityDescriptions.put("Café", "Café historique au cœur de la vieille ville. Terrasse ensoleillée avec vue sur les canaux.");
        activityDescriptions.put("Sport a la maison", "Restez en forme depuis chez vous avec des exercices adaptés à votre niveau. Pas besoin d'équipement spécial.");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Suggestion suggestion = suggestions.get(position);
        holder.activityTitle.setText(suggestion.getActivityName());
        holder.activityDescription.setText(suggestion.getActivityDescription());
        holder.activityIcon.setImageResource(suggestion.getActivityImageRes());

        String address = activityAddresses.get(suggestion.getActivityName());

        // Gérer le clic court pour ouvrir les détails
        holder.itemView.setOnClickListener(v -> {
            String title = suggestion.getActivityName();
            String detailedAddress = activityAddresses.getOrDefault(title, "Adresse non disponible");
            String detailedDescription = activityDescriptions.getOrDefault(title, suggestion.getActivityDescription());

            Intent intent = new Intent(v.getContext(), PlaceDetailActivity.class);
            intent.putExtra("place_name", title);
            intent.putExtra("place_address", detailedAddress);
            intent.putExtra("place_description", detailedDescription);
            v.getContext().startActivity(intent);
        });

        // Gérer le clic long pour ouvrir Google Maps dans le navigateur
        holder.itemView.setOnLongClickListener(v -> {
            if (address != null && !address.equals("À votre domicile")) {
                String url = "https://www.google.com/maps/search/" + Uri.encode(address);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                v.getContext().startActivity(intent);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView activityTitle;
        final TextView activityDescription;
        final ImageView activityIcon;

        ViewHolder(View view) {
            super(view);
            activityTitle = view.findViewById(R.id.textViewActivityName);
            activityDescription = view.findViewById(R.id.textViewActivityDescription);
            activityIcon = view.findViewById(R.id.imageViewActivity);
        }
    }
}