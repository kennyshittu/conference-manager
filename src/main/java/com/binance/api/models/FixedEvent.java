package com.binance.api.models;

import com.binance.api.utils.ConferenceUtils;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@NoArgsConstructor
public enum FixedEvent {

    LUNCH(ConferenceUtils.getCalendarTime(12, 0), null, 60, "Lunch"),
    NETWORKING(ConferenceUtils.getCalendarTime(16, 0), ConferenceUtils.getCalendarTime(17, 0), 60, "Networking");

    public Calendar startTime;
    public Calendar otherStartTime;
    public int duration;
    public String title;

    FixedEvent(Calendar startTime, Calendar otherStartTime,  int duration, String title) {
        this.startTime = startTime;
        this.otherStartTime = otherStartTime;
        this.duration = duration;
        this.title = title;
    }
}
