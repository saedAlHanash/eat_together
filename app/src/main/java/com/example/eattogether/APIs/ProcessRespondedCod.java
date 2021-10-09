package com.example.eattogether.APIs;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Response;

public class ProcessRespondedCod {
    public static String processRespondedCod(Response response) {
        if (response.code() == 200) {
            return "OK";
        } else if (response.code() == 500) {
            String saed = "";
            try {
                saed = response.errorBody().string();
                APIError error = new Gson().fromJson(saed, APIError.class);
                return error.getError().getMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (response.code() == 400) {
            String saed = "";
            try {
                saed = response.errorBody().string();
                APIError error = new Gson().fromJson(saed, APIError.class);
                if (error.getError().getValidationErrors().size() > 0)
                    return error.getError().getValidationErrors().get(0).getMessage();
                else return " ";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return " ";
    }
}
