package com.example.eattogether.Models;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

/*    {
        "userNameOrEmailAddress": "string",
            "password": "string",
            "rememberClient": true
    }*/

    @SerializedName("userNameOrEmailAddress")
    String userNameOrEmailAddress;
    @SerializedName("password")
    String password;
    @SerializedName("rememberClient")
    boolean rememberClient;

    public String getUserNameOrEmailAddress() {
        return userNameOrEmailAddress;
    }

    public void setUserNameOrEmailAddress(String userNameOrEmailAddress) {
        this.userNameOrEmailAddress = userNameOrEmailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberClient() {
        return rememberClient;
    }

    public void setRememberClient(boolean rememberClient) {
        this.rememberClient = rememberClient;
    }
}
