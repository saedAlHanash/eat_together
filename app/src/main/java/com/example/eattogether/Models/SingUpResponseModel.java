package com.example.eattogether.Models;

import com.google.gson.annotations.SerializedName;

public class SingUpResponseModel {
    @SerializedName("canLogin")
    boolean canLogin;

    public boolean isCanLogin() {
        return canLogin;
    }

    public void setCanLogin(boolean canLogin) {
        this.canLogin = canLogin;
    }
}
