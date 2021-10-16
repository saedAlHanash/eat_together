package com.example.eattogether.Helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint.ApiClint;
import com.example.eattogether.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetImageForUserHelper {

    public static API api = ApiClint.getRetrofitInstance().create(API.class);
    public static void downloadImage(int userID, ImageView imageView){
        Call<ResponseBody> call = api.downloadImage(userID);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                        if (response.body() != null) {
                            Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                            imageView.setImageBitmap(bmp);
                        }
                    }
                else {
                    imageView.setImageResource(R.drawable.ic_user);
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                imageView.setImageResource(R.drawable.ic_user);
            }
        });
    }


}
