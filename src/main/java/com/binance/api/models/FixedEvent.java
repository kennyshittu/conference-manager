package com.binance.api.models;

import com.binance.api.utils.ConferenceUtils;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@NoArgsConstructor
public enum FixedEvent {

    LUNCH(ConferenceUtils.getCalendarTime(12, 0), 60, "Lunch"),
    NETWORKING(ConferenceUtils.getCalendarTime(17, 0), 60, "Networking");

    public Calendar startTime;
    public int duration;
    public String title;

    FixedEvent(Calendar startTime, int duration, String title) {
        this.startTime = startTime;
        this.duration = duration;
        this.title = title;
    }
}
