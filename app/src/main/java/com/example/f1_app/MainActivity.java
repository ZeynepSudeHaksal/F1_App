package com.example.f1_app;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RaceAdapter raceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        raceAdapter = new RaceAdapter();
        recyclerView.setAdapter(raceAdapter);

        // Fetch race data from the API
        fetchRaceData();
    }

    private void fetchRaceData() {
        ErgastApiService apiService = RetrofitInstance.getApiService();
        // Fetch the last race results of the current season
        apiService.getLastRaceResults().enqueue(new Callback<RaceResponse>() {
            @Override
            public void onResponse(Call<RaceResponse> call, Response<RaceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RaceResponse raceResponse = response.body();
                    // Pass the race data to the adapter to display
                    raceAdapter.setRaceData(raceResponse.getRaceData());  // This now works
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load race data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RaceResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error fetching race data", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
