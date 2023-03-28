package com.leo.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeHandle {
    public static Timestamp dateStimestampConversion(String dateString) throws ParseException {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            dateFormat.setTimeZone(TimeZone.getTimeZone("ICT"));
            Date date = dateFormat.parse(dateString);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
    }
}



