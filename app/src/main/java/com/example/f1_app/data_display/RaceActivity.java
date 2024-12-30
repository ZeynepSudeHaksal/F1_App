package com.example.f1_app.data_display;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.f1_app.R;
import com.example.f1_app.RaceAdapter;
import com.example.f1_app.model.ErgastApiService;
import com.example.f1_app.model.Race;
import com.example.f1_app.model.RaceResponse;
import com.example.f1_app.model.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RaceActivity extends AppCompatActivity {

    private RecyclerView raceRecyclerView;
    private RaceAdapter raceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race);

        // Initialize RecyclerView
        raceRecyclerView = findViewById(R.id.raceRecyclerView);
        raceRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter with an empty list
        raceAdapter = new RaceAdapter(this, new ArrayList<>());
        raceRecyclerView.setAdapter(raceAdapter);

        // Get the selected year from the intent
        String selectedYear = getIntent().getStringExtra("year");

        // Fetch race data for the selected year
        fetchRacesForYear(selectedYear);
    }

    private void fetchRacesForYear(String year) {
        ErgastApiService apiService = RetrofitInstance.getApiService();
        // Get all races for the selected year
        apiService.getRacesForYear(year).enqueue(new Callback<RaceResponse>() {
            @Override
            public void onResponse(Call<RaceResponse> call, Response<RaceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Race> races = response.body().getRaceData();
                    // Pass the races to the adapter to display them
                    raceAdapter.setRaceData(races);
                } else {
                    Toast.makeText(RaceActivity.this, "Failed to load races", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RaceResponse> call, Throwable t) {
                Toast.makeText(RaceActivity.this, "Error fetching race data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
