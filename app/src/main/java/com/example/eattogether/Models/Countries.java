package com.example.eattogether.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Countries {

    @SerializedName("name")
    String countryName;
    @SerializedName("id")
    int countryID;
    @SerializedName("cities")
    ArrayList<Cities> cities;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public ArrayList<Cities> getCities() {
        return cities;
    }

    public void setCities(ArrayList<Cities> cities) {
        this.cities = cities;
    }
}
