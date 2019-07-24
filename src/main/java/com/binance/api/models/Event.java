package com.binance.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;

@Data
@AllArgsConstructor
public class Event {
    private Calendar startTime;
    private String title;
    private int durationInMinutes;
}
