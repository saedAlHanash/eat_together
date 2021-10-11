package com.example.eattogether;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.eattogether.APIs.API;
import com.example.eattogether.APIs.ApiClint.ApiClint;
import com.example.eattogether.APIs.ResponseModle.LoginResponse;
import com.example.eattogether.APIs.ResponseModle.ProcessRespondedCod;
import com.example.eattogether.Models.LoginModel;
import com.example.eattogether.ui.Home;
import com.example.eattogether.ui.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppConfig {
    static boolean isLogin = false;
    public static final String TOKEN = "token";
    public static final String  USER_ID = "userId";
    public static SharedPreferences sp;
    public static SharedPreferences.Editor spEdit;
    public static String get_ACCESS_TOKEN(){
        if (sp==null){
            return " ";
        }
         return sp.getString(TOKEN," ");
    }
    public static int get_USER_ID(){
        if (sp==null){
            return 0;
        }
         return sp.getInt(USER_ID,0);
    }
    public static SharedPreferences getSharedPreferencesInstance(Context context) {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
            spEdit = sp.edit();
        }
        return sp;
    }

}
