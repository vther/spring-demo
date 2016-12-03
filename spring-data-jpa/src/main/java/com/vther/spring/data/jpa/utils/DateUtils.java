package com.vther.spring.data.jpa.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    public Date stringToDate(String string) {
        if (string == null) {
            return null;
        }
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return sdf.parse(string);
        } catch (ParseException e) {
            return null;
        }
    }
}
