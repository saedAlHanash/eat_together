package com.example.eattogether.ui;

import android.app.Activity;

import android.os.Bundle;

import android.widget.ImageView;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint.ApiClint;

import com.example.eattogether.Helper.GetImageForUserHelper;
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



    }


}