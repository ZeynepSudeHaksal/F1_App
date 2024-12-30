package com.example.f1_app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.f1_app.model.ErgastApiService;
import com.example.f1_app.model.Race;
import com.example.f1_app.model.RaceResponse;
import com.example.f1_app.model.RetrofitInstance;
import com.example.f1_app.RaceAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView raceRecyclerView;
    private RaceAdapter raceAdapter;
    private List<String> yearsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the RecyclerView for races
        raceRecyclerView = findViewById(R.id.raceRecyclerView);
        raceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        raceAdapter = new RaceAdapter(this, new ArrayList<>());
        raceRecyclerView.setAdapter(raceAdapter);

        // Create a dynamic list of years from 1950 to the current year
        yearsList = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = 1950; year <= currentYear; year++) {
            yearsList.add(String.valueOf(year));
        }

        // Set up the Spinner for year selection
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearsList);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner yearSpinner = findViewById(R.id.yearSpinner);
        yearSpinner.setAdapter(yearAdapter);

        // Set the listener for the Spinner
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedYear = yearsList.get(position);
                fetchRacesForYear(selectedYear);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if no year is selected
            }
        });

        // Initially fetch races for the current year
        fetchRacesForYear(String.valueOf(currentYear));
    }

    // Fetch races for the selected year
    private void fetchRacesForYear(String year) {
        ErgastApiService apiService = RetrofitInstance.getApiService();
        apiService.getRacesForYear(year).enqueue(new Callback<RaceResponse>() {
            @Override
            public void onResponse(Call<RaceResponse> call, Response<RaceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Race> races = response.body().getRaceData();
                    // Update the RecyclerView with the fetched races
                    raceAdapter.setRaceData(races);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load races", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RaceResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error fetching race data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
