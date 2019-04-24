package com.example.testapplication.api.model.service;

import com.example.testapplication.api.model.service.model2.GithubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GithubClient {
    //sends json response
    @Headers("Accept: application/json")
    //secures the github access token
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<AccessToken> getAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code
    );

    //gets the current users repos in Github
    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> reposForUser(@Path("user") String user);
}
