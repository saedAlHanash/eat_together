package com.example.eattogether.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint.ApiClint;
import com.example.eattogether.Helper.GetCountriesHelper;
import com.example.eattogether.R;
import com.google.android.material.button.MaterialButton;



import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentSearch extends Fragment {

    API api= ApiClint.getRetrofitInstance().create(API.class);
    @BindView(R.id.saerch_button)
    MaterialButton saerchButton;
    @BindView(R.id.saerch_date)
    EditText saerchDate;
    @BindView(R.id.search_max_age)
    EditText searchMaxAge;
    @BindView(R.id.search_country)
    Spinner searchCountry;
    @BindView(R.id.search_gendr)
    Spinner searchGendr;
    @BindView(R.id.search_city)
    Spinner searchCity;
    @BindView(R.id.search_min_age)
    EditText searchMinAge;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
            searchCountry.setAdapter(GetCountriesHelper.getAllCountries(getActivity().getBaseContext()));
            searchCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    searchCity.setAdapter(GetCountriesHelper.getAllCities(position,getActivity().getBaseContext()));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        return view;
    }
}





















/*                Call<SingleCountry> call1 = api.getCountry(1);
                call1.enqueue(new Callback<SingleCountry>() {
                    @Override
                    public void onResponse(Call<SingleCountry> call, Response<SingleCountry> response) {
                        Toast.makeText(getContext(), ""+response.code(), Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onFailure(Call<SingleCountry> call, Throwable t) {
                        Toast.makeText(getContext(), "wrong when get country"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });*/