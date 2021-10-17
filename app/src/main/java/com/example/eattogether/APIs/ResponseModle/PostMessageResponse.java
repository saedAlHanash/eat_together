package com.example.eattogether.APIs.ResponseModle;

import com.example.eattogether.Models.PostMessageModel;
import com.google.gson.annotations.SerializedName;

public class PostMessageResponse {

    @SerializedName("result")
    PostMessageModel result;
    @SerializedName("success")
    boolean success;
}
