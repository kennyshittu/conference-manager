package com.binance.api.services;

import com.binance.api.models.Conference;

import java.io.InputStream;

public interface ConferenceManagerService {
    Conference generateConferenceTracks(InputStream fileInputStream);
}
