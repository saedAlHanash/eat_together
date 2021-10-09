package com.example.eattogether.Models;

import com.google.gson.annotations.SerializedName;

public class SingUpModel {

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
    String captchaResponse = "string";

    public SingUpModel() {
    }

    public SingUpModel(String name, int gender, int age, int cityId, String phoneNumber, String emailAddress, String password, String avatar) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.cityId = cityId;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.password = password;
        this.avatar = avatar;
    }

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

    @Override
    public String toString() {
        return "SingUpModel{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", cityId=" + cityId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", captchaResponse='" + captchaResponse + '\'' +
                '}';
    }
}
