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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCaptchaResponse() {
        return captchaResponse;
    }

    public void setCaptchaResponse(String captchaResponse) {
        this.captchaResponse = captchaResponse;
    }
}
