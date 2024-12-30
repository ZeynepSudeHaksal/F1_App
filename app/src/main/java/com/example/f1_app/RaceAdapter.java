package com.example.f1_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.RaceViewHolder> {

    private List<Race> raceData;  // List to hold the race data

    public RaceAdapter() {
        // Initialize with an empty list
        this.raceData = null;
    }

    // Set new data to the adapter
    public void setRaceData(List<Race> raceData) {
        this.raceData = raceData;
        notifyDataSetChanged();  // Notify the adapter to refresh the view
    }

    @Override
    public RaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout for the race result
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_race, parent, false);
        return new RaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RaceViewHolder holder, int position) {
        // Bind the race data to the view
        if (raceData != null && position < raceData.size()) {
            Race race = raceData.get(position);
            holder.bind(race);
        }
    }

    @Override
    public int getItemCount() {
        return raceData != null ? raceData.size() : 0;
    }

    // ViewHolder class to hold the view for each race item
    public static class RaceViewHolder extends RecyclerView.ViewHolder {
        private TextView raceNameTextView;
        private TextView circuitNameTextView;
        private TextView raceDateTextView;

        public RaceViewHolder(View itemView) {
            super(itemView);
            raceNameTextView = itemView.findViewById(R.id.raceNameTextView);
            circuitNameTextView = itemView.findViewById(R.id.circuitNameTextView);
            raceDateTextView = itemView.findViewById(R.id.raceDateTextView);
        }

        // Bind the race data to the views
        public void bind(Race race) {
            raceNameTextView.setText(race.getRaceName());
            circuitNameTextView.setText(race.getCircuit().getCircuitName());  // Accessing Circuit object to get Circuit Name
            raceDateTextView.setText(race.getRaceDate());
        }
    }
}
