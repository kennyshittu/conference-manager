package com.binance.api.utils;

import com.binance.api.models.Talk;

import java.util.Calendar;

public class ConferenceUtils {

    public static Calendar getCalendarTime(int hours, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        return cal;
    }

    public static Calendar getNextStartTime(Calendar currentStartTime, Talk currentTalk) {
        Calendar newTime = Calendar.getInstance();
        newTime.set(Calendar.HOUR_OF_DAY, currentStartTime.get(Calendar.HOUR_OF_DAY));
        newTime.set(Calendar.MINUTE, currentStartTime.get(Calendar.MINUTE));
        newTime.add(Calendar.MINUTE, currentTalk.getMinutes());
        return newTime;
    }

    public static Long getDiffInMinutes(Calendar endTime, Calendar startTime) {
        long diff = endTime.getTimeInMillis() - startTime.getTimeInMillis();
        return ((diff / 1000) / 60);
    }
}
