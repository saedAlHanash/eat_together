package com.example.eattogether;

import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateConverter {

    public static String dateToString(Date date) {
        DateFormat dateFormat;
             dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);
            return dateFormat.format(date);

    }
    public static String formatDate(String date){

        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormat.format(DateConverter.stringToDate(date));
    }

    public static Date stringToDate(String sDate) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",Locale.ENGLISH).parse(sDate);
        } catch (Exception ignored) {
        }
        return date;
    }

    public static String findDifference (Date dateStart, Date dateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ",Locale.ENGLISH);
        long difference_In_Time = dateEnd.getTime() - dateStart.getTime();
/*
                long difference_In_Seconds = (difference_In_Time / 1000) % 60;
                long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;


*/
        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
        long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
        return ""+difference_In_Days+"Day " + difference_In_Hours + "Hour";
    }

    public static String now()
    {
        return dateToString(Calendar.getInstance().getTime());
    }

    public static Date getDateFromDatePicker(DatePicker paramDatePicker, int hour, int minute) {
        int day = paramDatePicker.getDayOfMonth();
        int month = paramDatePicker.getMonth();
        int Year = paramDatePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Year, month, day, hour, minute, 0);
        return calendar.getTime();
    }
}
