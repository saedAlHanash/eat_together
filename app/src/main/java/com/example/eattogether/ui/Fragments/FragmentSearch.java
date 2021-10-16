package com.example.eattogether.ui.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSearch extends Fragment {

    API api = ApiClint.getRetrofitInstance().create(API.class);


    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    searchModel model = new searchModel();


    @BindView(R.id.saerch_button)
    MaterialButton searchButton;
    @BindView(R.id.saerch_date)
    EditText searchDate;
    @BindView(R.id.search_max_age)
    EditText searchMaxAge;
    @BindView(R.id.search_country)
    Spinner searchCountry;
    @BindView(R.id.search_gendr)
    Spinner searchGender;
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
        setAdapterForCountry();
        listeners();
        return view;
    }



    public void showPickers() {

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(
                getContext(),
                (view1, hourOfDay, minute1) -> {

                    try {
                        model.setDate(DateConverter.dateToString(
                                DateConverter.getDateFromDatePicker(datePickerDialog.getDatePicker(), hourOfDay, minute1)
                        ));
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "pleas set the date", Toast.LENGTH_SHORT).show();
                    }
                },
                hour,
                minute,
                true);

        datePickerDialog = new DatePickerDialog(getContext(),
                (view1, year1, month1, dayOfMonth) -> {
                    timePickerDialog.show();
                },
                year, month, day);
        datePickerDialog.show();
    }

    public void setAdapterForCountry() {
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
    }

    void listeners() {
        searchCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                model.setCity(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchDate.setOnClickListener(v -> {
            showPickers();
        });

        searchGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                model.setGender(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchButton.setOnClickListener(v -> {
            try {
                model.setMinAge(Integer.parseInt(searchMinAge.getText().toString()));
                model.setMaxAge(Integer.parseInt(searchMaxAge.getText().toString()));
            } catch (Exception ignored) {
                Toast.makeText(getContext(), "wrong with fields", Toast.LENGTH_SHORT).show();
            }
            Call<FindPartnersModel> call = api.findPartnersModel(
                    model.getCity(),
                    model.getGender(),
                    model.getMinAge(),
                    model.getMaxAge(),
                    0,
                    model.getDate(),
                    10
            );
            call.enqueue(new Callback<FindPartnersModel>() {
                @Override
                public void onResponse(Call<FindPartnersModel> call, Response<FindPartnersModel> response) {
                    if (response.code() == 200) {
                        ((Home) getActivity()).replaceFragment(response.body().getResult());
                    } else {
                        Toast.makeText(getContext(),
                                "" + response.code()+
                                 "\n"+ ProcessRespondedCod.processRespondedCod(response)
                                , Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<FindPartnersModel> call, Throwable t) {
                    Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
    class searchModel {
        String date;
        int city;
        int gender;
        int minAge;
        int maxAge;


        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getMinAge() {
            return minAge;
        }

        public void setMinAge(int minAge) {
            this.minAge = minAge;
        }

        public int getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(int maxAge) {
            this.maxAge = maxAge;
        }
    }

}
