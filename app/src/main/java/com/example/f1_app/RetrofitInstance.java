package com.example.f1_app;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static final String BASE_URL = "http://ergast.com/api/";
    private static Retrofit retrofit;

    // Private constructor to prevent instantiation
    private RetrofitInstance() {}

    // Public method to get the Retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            synchronized (RetrofitInstance.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

    // Public method to get the API service
    public static ErgastApiService getApiService() {
        return getRetrofitInstance().create(ErgastApiService.class);
    }
}
