package com.example.eattogether.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllCountriesModel {
    @SerializedName("result")
    ArrayList<Countries> countries ;

    public ArrayList<Countries> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Countries> countries) {
        this.countries = countries;
    }
}
