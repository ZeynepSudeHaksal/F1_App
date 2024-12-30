package com.example.f1_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.f1_app.model.Race;

import java.util.List;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.RaceViewHolder> {

    private Context context;
    private List<Race> raceData;

    public RaceAdapter(Context context, List<Race> raceData) {
        this.context = context;
        this.raceData = raceData;
    }

    @Override
    public RaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_race, parent, false);
        return new RaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RaceViewHolder holder, int position) {
        Race race = raceData.get(position);
        holder.raceNameTextView.setText(race.getRaceName());
        holder.circuitNameTextView.setText(race.getCircuit().getCircuitName());
        holder.raceDateTextView.setText(race.getRaceDate());
    }

    @Override
    public int getItemCount() {
        return raceData.size();
    }

    public void setRaceData(List<Race> raceData) {
        this.raceData = raceData;
        notifyDataSetChanged();
    }

    public static class RaceViewHolder extends RecyclerView.ViewHolder {

        TextView raceNameTextView;
        TextView circuitNameTextView;
        TextView raceDateTextView;

        public RaceViewHolder(View itemView) {
            super(itemView);
            raceNameTextView = itemView.findViewById(R.id.raceNameTextView);
            circuitNameTextView = itemView.findViewById(R.id.circuitNameTextView);
            raceDateTextView = itemView.findViewById(R.id.raceDateTextView);
        }
    }
}
