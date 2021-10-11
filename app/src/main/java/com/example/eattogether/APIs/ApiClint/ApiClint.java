package com.example.eattogether.APIs.ApiClint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {

    public  static final String BASE_URL = "http://test.tuqaatech.com/";
    public static Retrofit retrofit;

   public static TokenInterceptor interceptor=new TokenInterceptor();

   public static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor).build();

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null)
        {
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
/*                  .addConverterFactory(JacksonConverterFactory.create())*/
                    .addConverterFactory( GsonConverterFactory.create(gson))
                    .build();
            return retrofit;
        }
        return retrofit;
    }


}
