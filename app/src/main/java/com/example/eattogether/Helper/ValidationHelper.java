package com.example.eattogether.Helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.RequiresApi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {

    public static boolean isValidMobile(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean isValidMail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword( String password) {

        return password.length()>=8;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void hideSoftKeyboard (Activity activity, View view)
    {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }
}
