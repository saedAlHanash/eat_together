package com.example.eattogether.Helper;

import android.util.Patterns;

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
}
