package com.example.f1_app.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ErgastApiService {

    // Get the results of the most recent race of the current season
    @GET("f1/current/last/results.json")
    Call<RaceResponse> getLastRaceResults();

    // Get results for a specific season and round (e.g., season 2023, round 1)
    @GET("f1/{season}/{round}/results.json")
    Call<RaceResponse> getRaceResults(@Path("season") String season, @Path("round") int round);

    // Get results for the next race of the current season
    @GET("f1/current/next/results.json")
    Call<RaceResponse> getNextRaceResults();

    // Get all data for the F1 series (general data about the series)
    @GET("f1.json")  // Updated the path to correctly point to the general F1 data
    Call<RaceResponse> getAllSeriesData();

    // Get all races for a specific year (season) (e.g., 2023 season)
    @GET("f1/{season}.json")
    Call<RaceResponse> getRacesForYear(@Path("season") String season);

    // Get a specific race's data by season and round (e.g., for 2023 season, round 10)
    @GET("f1/{season}/{round}/race.json")
    Call<RaceResponse> getSpecificRaceData(@Path("season") String season, @Path("round") int round);
}
