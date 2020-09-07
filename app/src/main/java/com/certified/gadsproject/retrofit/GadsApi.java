package com.certified.gadsproject.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GadsApi {

    @GET("api/hours")
    Call<List<Hours>> getHours();

    @GET("api/skilliq")
    Call<List<IqScore>> getIqScores();

}
