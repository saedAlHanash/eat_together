package com.example.eattogether.Helper;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint;
import com.example.eattogether.Models.Cities;
import com.example.eattogether.Models.Countries;
import com.example.eattogether.Models.AllCountriesModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCountriesHelper {

    public static API api = ApiClint.getRetrofitInstance().create(API.class);
    static   ArrayList<Countries> countries ;
    public static ArrayAdapter<String> getAllCountries(Context context) {

        ArrayList<String> countriesList = new ArrayList<>();
        countriesList.add("Country..");
        Call<AllCountriesModel> call = api.getAllCountries();

        call.enqueue(new Callback<AllCountriesModel>() {
            @Override
            public void onResponse(Call<AllCountriesModel> call, Response<AllCountriesModel> response) {
                countries = response.body().getCountries();
                for (int i = 0; i < countries.size(); i++) {
                    countriesList.add(countries.get(i).getCountryName());
                }
            }
            @Override
            public void onFailure(Call<AllCountriesModel> call, Throwable t) {
            }
        });

        return new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, countriesList);
    }

    public static ArrayAdapter<String> getAllCities(int countryID, Context context) {
        ArrayList<String> citiesList = new ArrayList<>();
        citiesList.add("City..");
        if (countryID!=0) {
            ArrayList<Cities> cities =countries.get(countryID-1).getCities();
            for (int i = 0; i < cities.size(); i++) {
                citiesList.add(cities.get(i).getName());
            }
        }
        return new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, citiesList);
    }
}
