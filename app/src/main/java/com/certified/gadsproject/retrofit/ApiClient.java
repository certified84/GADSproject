package com.certified.gadsproject.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit() {

        String baseUrl = "https://gadsapi.herokuapp.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static GadsApi getHoursApi() {
        GadsApi gadsHours = getRetrofit().create(GadsApi.class);
        return gadsHours;
    }

    public static GadsApi getIqApi() {
        GadsApi gadsIq = getRetrofit().create(GadsApi.class);
        return gadsIq;
    }
}
