package com.example.api.Model;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.api.Activities.PlaceDetailActivity;
import com.example.api.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivitySuggestionAdapter extends RecyclerView.Adapter<ActivitySuggestionAdapter.ViewHolder> {
    private final List<Suggestion> suggestions;
    private final String selectedCity; // Nom de la ville sélectionnée par l'utilisateur
    private final Map<String, Map<String, String>> cityAddresses;
    private final Map<String, Map<String, String>> cityDescriptions;

    public ActivitySuggestionAdapter(List<Suggestion> suggestions, String selectedCity) {
        this.suggestions = suggestions;
        this.selectedCity = selectedCity;

        // Initialiser les maps pour chaque ville et activité
        this.cityAddresses = new HashMap<>();
        this.cityDescriptions = new HashMap<>();

        // Ajouter les informations pour Annecy
        Map<String, String> annecyAddresses = new HashMap<>();
        annecyAddresses.put("Plage", "2 Avenue du Lac, 74000 Annecy");
        annecyAddresses.put("Randonnée", "Sentier du Semnoz, 74000 Annecy");
        annecyAddresses.put("Pique-nique", "Parc Charles Bosson, 74000 Annecy");
        annecyAddresses.put("Piscine", "90 Chem. des Fins N, 74000 Annecy");
        annecyAddresses.put("Cinéma", "7 Av. de Brogny, 74000 Annecy");
        annecyAddresses.put("Musée", "1 Place du Château, 74000 Annecy");
        annecyAddresses.put("Lecture", "1 rue Jean Jaurès, 74000 Annecy");
        annecyAddresses.put("Café", "2 Place Ste Claire, 74000 Annecy");
        annecyAddresses.put("Sport a la maison", "À votre domicile");
        cityAddresses.put("Annecy", annecyAddresses);

        Map<String, String> annecyDescriptions = new HashMap<>();
        annecyDescriptions.put("Plage", "Profitez de la plage d'Annecy, l'une des plus belles plages lacustres d'Europe. Baignade surveillée en été, activités nautiques et vue imprenable sur les montagnes.");
        annecyDescriptions.put("Randonnée", "Parcours balisé offrant une vue panoramique sur le lac d'Annecy et les Alpes. Difficulté moyenne, prévoir 3-4h de marche.");
        annecyDescriptions.put("Pique-nique", "Magnifique parc au bord du lac avec tables de pique-nique, aire de jeux pour enfants et pelouse ombragée.");
        annecyDescriptions.put("Piscine", "Piscine municipale couverte avec bassin olympique, pataugeoire et espace bien-être. Cours de natation disponibles.");
        annecyDescriptions.put("Cinéma", "Cinéma moderne proposant les dernières sorties en VO et VF. Tarif réduit le mercredi.");
        annecyDescriptions.put("Musée", "Découvrez l'histoire d'Annecy dans ce château médiéval. Collections permanentes et expositions temporaires toute l'année.");
        annecyDescriptions.put("Lecture", "Bibliothèque municipale avec grand choix de livres, magazines et espace multimédia. Wifi gratuit.");
        annecyDescriptions.put("Café", "Café historique au cœur de la vieille ville. Terrasse ensoleillée avec vue sur les canaux.");
        annecyDescriptions.put("Sport a la maison", "Restez en forme depuis chez vous avec des exercices adaptés à votre niveau. Pas besoin d'équipement spécial.");
        cityDescriptions.put("Annecy", annecyDescriptions);

        // Ajouter les informations pour Paris
        Map<String, String> parisAddresses = new HashMap<>();
        parisAddresses.put("Plage", "Bords de Seine, Paris");
        parisAddresses.put("Randonnée", "Bois de Boulogne, 75016 Paris");
        parisAddresses.put("Cinéma", "12 Rue de Rivoli, 75001 Paris");
        parisAddresses.put("Musée", "3 Rue des Archives, 75004 Paris");
        parisAddresses.put("Café", "Place Saint-Germain-des-Prés, 75006 Paris");
        parisAddresses.put("Pique-nique", "Parc Monceau, 75008 Paris");
        parisAddresses.put("Piscine", "39 Rue de Pontoise, 75005 Paris");
        cityAddresses.put("Paris", parisAddresses);

        Map<String, String> parisDescriptions = new HashMap<>();
        parisDescriptions.put("Plage", "Détendez-vous aux bords de Seine, avec vue sur les monuments emblématiques.");
        parisDescriptions.put("Randonnée", "Promenade tranquille à travers le Bois de Boulogne, idéal pour se ressourcer.");
        parisDescriptions.put("Cinéma", "Cinéma en plein centre de Paris avec une sélection de films internationaux.");
        parisDescriptions.put("Musée", "Visitez le musée d'art et d'histoire avec des expositions temporaires uniques.");
        parisDescriptions.put("Café", "Sirotez un café au cœur de Saint-Germain, quartier emblématique.");
        parisDescriptions.put("Pique-nique", "Un parc tranquille avec de beaux jardins pour un pique-nique en famille.");
        parisDescriptions.put("Piscine", "Piscine historique, parfaite pour une baignade rafraîchissante.");
        cityDescriptions.put("Paris", parisDescriptions);

        // Répétez cette structure pour d'autres villes, en ajoutant chaque ville à `cityAddresses` et `cityDescriptions`.
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

        // Gérer le clic sur l'élément
        holder.itemView.setOnClickListener(v -> {
            String title = suggestion.getActivityName();

            // Récupérer les informations en fonction de la ville et de l'activité
            String detailedAddress = cityAddresses
                    .getOrDefault(selectedCity, new HashMap<>())
                    .getOrDefault(title, "Adresse non disponible");
            String detailedDescription = cityDescriptions
                    .getOrDefault(selectedCity, new HashMap<>())
                    .getOrDefault(title, suggestion.getActivityDescription());

            Intent intent = new Intent(v.getContext(), PlaceDetailActivity.class);
            intent.putExtra("place_name", title);
            intent.putExtra("place_address", detailedAddress);
            intent.putExtra("place_description", detailedDescription);
            v.getContext().startActivity(intent);
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
