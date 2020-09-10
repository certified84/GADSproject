package com.certified.gadsproject.retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GadsApi {

    @GET("api/hours")
    Call<List<Hours>> getHours();

    @GET("api/skilliq")
    Call<List<IqScore>> getIqScores();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Submit> submitProject (
        @Field("entry.1877115667") String firstName,
        @Field("entry.2006916086") String lastName,
        @Field("entry.1824927963") String emailAddress,
        @Field("entry.284483984") String projectLink
    );

}
