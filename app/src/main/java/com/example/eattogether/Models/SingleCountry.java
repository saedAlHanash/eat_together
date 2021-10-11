package com.example.eattogether.Models;

import com.google.gson.annotations.SerializedName;

public class SingleCountry {

    @SerializedName("result")
    Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
