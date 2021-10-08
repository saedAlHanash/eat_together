package com.example.eattogether;

import com.google.gson.annotations.SerializedName;

public class SingUpModel {

    //      "name": "string",
//              "gender": 0,
//              "age": 0,
//              "cityId": 0,
//              "phoneNumber": "string",
//              "emailAddress": "user@example.com",
//              "password": "string",
//              "avatar": "string",
//              "captchaResponse": "string"
    @SerializedName("name")
    String name;
    @SerializedName("gender")
    int gender;
    @SerializedName("age")
    int age;
    @SerializedName("cityId")
    int cityId;
    @SerializedName("phoneNumber")
    String phoneNumber;
    @SerializedName("emailAddress")
    String emailAddress;
    @SerializedName("password")
    String password;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("captchaResponse")
    String captchaResponse;


}
