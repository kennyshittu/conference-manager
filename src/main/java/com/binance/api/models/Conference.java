package com.binance.api.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Conference {
    List<Track> tracks = new ArrayList<>();

    public void addTrack(Track track) {
        this.tracks.add(track);
    }
}


