package com.example.eattogether.APIs.ApiClint;

import android.content.Context;

import com.example.eattogether.AppConfig;
import com.example.eattogether.ui.MainActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest=chain.request().newBuilder()
                .header("Authorization","Bearer "+AppConfig.get_ACCESS_TOKEN())
                .build();

        return chain.proceed(newRequest);
    }
}
