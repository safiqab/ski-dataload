package com.dish.scm.ski.skidataload.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());


    public static String getCurrentDateString() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd") );
    }

    public static Date convertStringToDate(String date) throws ParseException {

        if (StringUtils.isEmpty(date) || date.equalsIgnoreCase("null") || date.equalsIgnoreCase("0") )
            return null;
        else
            return new java.sql.Date(dateFormat.parse(date).getTime());
    }



    public static String getStringDate(Date date) {
        return dateFormat.format(date);
    }

    public static String getDateForFile() {
        return String.valueOf(LocalDateTime.now().getYear()) + String.valueOf(LocalDateTime.now().getMonthValue()) +
                String.valueOf(LocalDateTime.now().getDayOfMonth())+ "_" + String.valueOf(LocalDateTime.now().getHour()) +
                String.valueOf(LocalDateTime.now().getMinute()) + String.valueOf(LocalDateTime.now().getSecond());
    }
}
