package com.binance.api.utils;

import com.binance.api.models.Talk;

import java.util.Comparator;

public class TalkComparator implements Comparator<Talk> {

    @Override
    public int compare(Talk a, Talk b) {
        return a.getMinutes() < b.getMinutes() ? 1 : -1;
    }
}
