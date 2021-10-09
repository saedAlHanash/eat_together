package com.example.eattogether.Models;

import com.google.gson.annotations.SerializedName;

public class Cities {

    @SerializedName("countryId")
    int countryId;
    @SerializedName("countryName")
    String countryName;
    @SerializedName("name")
    String name;
    @SerializedName("id")
    int cityID;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }
}
