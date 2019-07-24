package com.binance.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Track {
  private int trackId;
  private List<TrackSession> trackSessions;

  public void addtrackSession(TrackSession trackSession) {
    this.trackSessions.add(trackSession);
  }
}
