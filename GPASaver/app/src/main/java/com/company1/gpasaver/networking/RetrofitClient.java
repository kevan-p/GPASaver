package com.company1.gpasaver.networking;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    // Placeholder API for test data.
    private static final String BASE_URL = "https://api.randomuser.me/";
    public final static String RANDOM_USER_URL = "https://api.randomuser.me/?results=10&nat=en";

    public static Retrofit getRetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
        return retrofit;
    }
}
