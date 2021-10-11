package com.example.eattogether.Helper;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint.ApiClint;
import com.example.eattogether.Models.Country;
import com.example.eattogether.Models.Countries;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCountriesHelper {

    public static API api = ApiClint.getRetrofitInstance().create(API.class);
    static ArrayList<Country> countries = new ArrayList<>();

    public static ArrayAdapter<String> getAllCountries(Context context) {
        //string of countries name
        ArrayList<String> countriesList = new ArrayList<>();
        countriesList.add("Country..");

        Call<Countries> call = api.getAllCountries();
        call.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                countries = response.body().getCountries();
                for (int i = 0; i < countries.size(); i++) {
                    countriesList.add(countries.get(i).getCountryName());
                }
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                Toast.makeText(context, "wrong when get countries"+"\n"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, countriesList);
    }


    public static ArrayAdapter<String> getAllCities(int countryID, Context context) {
        //string of cities name
        ArrayList<String> citiesList = new ArrayList<>();
        citiesList.add("City..");

        if (countryID != 0) {
            ArrayList<Country.Cities> cities = countries.get(countryID - 1).getCities();
            for (int i = 0; i < cities.size(); i++) {
                citiesList.add(cities.get(i).getName());
            }
        }
        return new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, citiesList);
    }
}
