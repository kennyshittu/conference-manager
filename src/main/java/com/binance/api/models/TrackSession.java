package com.binance.api.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.binance.api.utils.ConferenceUtils.getDiffInMinutes;

@Data
public class TrackSession {
  private List<Event> events;
  private int remainingDuration;
  private Session type;

  public TrackSession(Session type) {
    this.events = new ArrayList<>();
    this.type = type;
    remainingDuration = getDiffInMinutes(type.endTime, type.startTime).intValue();
  }

  public void addEvent(Event event) {
    events.add(event);
    this.remainingDuration -= event.getDurationInMinutes();
  }

  // check if the talk can be accommodated in the current slot.
  public boolean hasRoomFor(Talk talk) {
    return remainingDuration >= talk.getMinutes();
  }
}
