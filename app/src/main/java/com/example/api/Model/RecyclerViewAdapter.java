package com.example.api.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<Forecast> forecastList;

    public RecyclerViewAdapter(List<Forecast> forecastList) {
        this.forecastList = forecastList;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCityName;
        TextView textViewMin;
        TextView textViewMax;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCityName = itemView.findViewById(R.id.textViewNameCity);
            textViewMin = itemView.findViewById(R.id.textViewMinv);
            textViewMax = itemView.findViewById(R.id.textViewMaxv);
        }
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_forecast, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        Forecast forecast = forecastList.get(position);

        // Bind the data to the TextViews
        holder.textViewCityName.setText(forecast.getCityName());
        holder.textViewMin.setText(String.format("Min: %.1f°C", forecast.getWeather().getTempMin() - 273.15));
        holder.textViewMax.setText(String.format("Max: %.1f°C", forecast.getWeather().getTempMax() - 273.15));
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }
}
