package com.example.eattogether.ui.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint.ApiClint;
import com.example.eattogether.APIs.ResponseModle.ProcessRespondedCod;
import com.example.eattogether.DateConverter;
import com.example.eattogether.Helper.GetCountriesHelper;
import com.example.eattogether.Models.FindPartnersModel;
import com.example.eattogether.R;
import com.example.eattogether.ui.Home;
import com.google.android.material.button.MaterialButton;


import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSearch extends Fragment {

    API api = ApiClint.getRetrofitInstance().create(API.class);
    Calendar calendar = Calendar.getInstance();
    String date = "2021-22-10T5:24:40.260Z   2021-10-11T20:01:46.528Z";
    int city = 0;
    int gender = 0;
    int minAge = 0, maxAge = 0;
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        searchCountry.setAdapter(GetCountriesHelper.getAllCountries(getActivity()));
        searchCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchCity.setAdapter(GetCountriesHelper.getAllCities(position, getActivity()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        saerchDate.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    getContext(),
                    (view1, hourOfDay, minute1) -> {
                        date += hourOfDay + ":" + minute1 + ":40.260Z";
                        saerchDate.setText(date);
                    },
                    hour,
                    minute,
                    true);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    (view1, year1, month1, dayOfMonth) -> {
                        month1 += 1;
                        date = year1 + "-" + month1 + "-" + dayOfMonth + "T";
                        timePickerDialog.show();
                    },
                    year, month, day);
            datePickerDialog.show();
        });
        searchGendr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saerchButton.setOnClickListener(v -> {

            try {
                minAge = Integer.parseInt(searchMinAge.getText().toString());
                maxAge = Integer.parseInt(searchMaxAge.getText().toString());
            } catch (Exception ignored) {

            }
            Call<FindPartnersModel> call = api.findPartnersModel(
                    city,
                    gender,
                    minAge,
                    maxAge,
                    0,
                    "2021-10-11T20:01:46.528Z",
                    10);
            call.enqueue(new Callback<FindPartnersModel>() {
                @Override
                public void onResponse(Call<FindPartnersModel> call, Response<FindPartnersModel> response) {
                    if (response.code() == 200) {
                        saerchButton.setOnClickListener(v -> ((Home) getActivity()).replaceFragment());
                    }
                    else {
                        Toast.makeText(getContext(), ""+response.code(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), "\n"+ ProcessRespondedCod.processRespondedCod(response), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<FindPartnersModel> call, Throwable t) {
                    Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
        return view;
    }
}





/*            */




















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