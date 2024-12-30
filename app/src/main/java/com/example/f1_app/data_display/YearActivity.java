package com.example.f1_app.data_display;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.f1_app.R;

import java.util.ArrayList;
import java.util.Calendar;

public class YearActivity extends AppCompatActivity {

    private ListView yearListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);

        // Initialize ListView
        yearListView = findViewById(R.id.yearListView);

        // Get the current year
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        // Create a list of years from 1950 to the current year
        ArrayList<String> years = new ArrayList<>();
        for (int year = 1950; year <= currentYear; year++) {
            years.add(String.valueOf(year));
        }

        // Adapter to bind the data to the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, years);
        yearListView.setAdapter(adapter);

        // Set an item click listener to handle user clicks on a year
        yearListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected year
                String selectedYear = years.get(position);

                // Start the RaceActivity and pass the selected year
                Intent intent = new Intent(YearActivity.this, RaceActivity.class);
                intent.putExtra("year", selectedYear);
                startActivity(intent);
            }
        });
    }
}
