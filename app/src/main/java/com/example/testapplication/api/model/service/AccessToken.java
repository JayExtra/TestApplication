package com.example.testapplication.api.model.service;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JayExtra on 4/20/19
 */

public class AccessToken {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;


    //responsible for mapping the access token and the type of token

    public String getAccessToken() {
        return accessToken;
    }


    public String getTokenType() {
        return tokenType;
    }
}
