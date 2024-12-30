package com.example.f1_app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ErgastApiService {
    // Get the results of the most recent race of the current season
    @GET("f1/current/last/results.json")
    Call<RaceResponse> getLastRaceResults();

    // Get results for a specific season and round
    @GET("f1/{season}/{round}/results.json")
    Call<RaceResponse> getRaceResults(@Path("season") String season, @Path("round") int round);

    // Get results for the next race of the current season
    @GET("f1/current/next/results.json")
    Call<RaceResponse> getNextRaceResults();

    // Get all data for the F1 series
    @GET("f1/")
    Call<RaceResponse> getAllSeriesData();
}
