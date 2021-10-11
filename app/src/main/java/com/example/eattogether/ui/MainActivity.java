package com.example.eattogether.ui;

import android.app.Activity;

import android.os.Bundle;

import android.widget.ImageView;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint.ApiClint;

import com.example.eattogether.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    public static String token;
    @BindView(R.id.saedsaed)
    ImageView imageView;
    API api = ApiClint.getRetrofitInstance().create(API.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

/*
        Call<LoginResponse> call = api.login(new LoginModel("saed404@gmail.com","1234567890",true));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.code()!=200){
                    Toast.makeText(getApplicationContext(), ""+ ProcessRespondedCod.processRespondedCod(response), Toast.LENGTH_SHORT).show();
                }
                else {
                    token = " "+response.body().getResult().getAccessToken();
                    Toast.makeText(getApplicationContext(), ""+response.body().getResult().getUserId()+"ok", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

/*        imageView.setOnClickListener(v -> {
            Call<SingleCountry> call1 = api.getCountry(1);
            call1.enqueue(new Callback<SingleCountry>() {
                @Override
                public void onResponse(Call<SingleCountry> call, Response<SingleCountry> response) {
                    Toast.makeText(MainActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, ""+response.body().getCountry().getCountryName(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<SingleCountry> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "wrong when get country"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });*/


    }


}