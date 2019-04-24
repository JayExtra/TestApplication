package com.example.testapplication.api.model.service;

import com.example.testapplication.api.model.service.model2.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by JayExtra on 4/20/19
 */

public interface UserClient {

    @POST("user")
    Call<User> createAccount( @Body User user);
}
