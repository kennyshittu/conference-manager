package com.binance.api.models;

import com.binance.api.utils.ConferenceUtils;

import java.util.Calendar;

public enum Session {
  AFTERNOON(ConferenceUtils.getCalendarTime(13, 0), ConferenceUtils.getCalendarTime(17, 0)),
  LUNCH(ConferenceUtils.getCalendarTime(13, 0), ConferenceUtils.getCalendarTime(14, 0)),
  MORNING(ConferenceUtils.getCalendarTime(9, 0), ConferenceUtils.getCalendarTime(12, 0)),
  NETWORKING(ConferenceUtils.getCalendarTime(17, 0), ConferenceUtils.getCalendarTime(18, 0));

  public Calendar startTime;
  public Calendar endTime;

  Session(Calendar startTime, Calendar endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
  }
}
