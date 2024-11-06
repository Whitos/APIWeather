package com.example.api.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.R;

import java.util.List;

public class ActivitySuggestionAdapter extends RecyclerView.Adapter<ActivitySuggestionAdapter.ActivityViewHolder> {

    private final List<Suggestion> activitySuggestions;

    public ActivitySuggestionAdapter(List<Suggestion> activitySuggestions) {
        this.activitySuggestions = activitySuggestions;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActivityViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item, parent, false);
        viewHolder = new ActivityViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        holder.textViewActivityDescription.setText(activitySuggestions.get(position).getActivityDescription());
        holder.textViewActivityName.setText(activitySuggestions.get(position).getActivityName());
        holder.imageViewActivity.setImageResource(activitySuggestions.get(position).getActivityImageRes());
    }

    @Override
    public int getItemCount() {
        return activitySuggestions.size();
    }

    public long getItemId(int position) {
        return position;
    }


    public class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView textViewActivityName;
        TextView textViewActivityDescription;
        ImageView imageViewActivity;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewActivityName = itemView.findViewById(R.id.textViewActivityName);
            textViewActivityDescription = itemView.findViewById(R.id.textViewActivityDescription);
            imageViewActivity = itemView.findViewById(R.id.imageViewActivity);
        }
    }
}
