package com.binance.api.services;

import com.binance.api.models.Conference;
import com.binance.api.models.Event;
import com.binance.api.models.FixedEvent;
import com.binance.api.models.Session;
import com.binance.api.models.Talk;
import com.binance.api.models.Track;
import com.binance.api.models.TrackSession;
import com.binance.api.parser.CsvReader;
import com.binance.api.utils.ConferenceUtils;
import com.binance.api.utils.TalkComparator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class ConferenceManagerServiceImpl implements ConferenceManagerService {

  @Override
  public Conference getTracks(InputStream fileInputStream) {
    // Get talks
    List<Talk> talks = CsvReader.readCsvFile(fileInputStream);

    // Generate tracks from talks
    return generateConferenceTracks(talks);
  }

  private Conference generateConferenceTracks(List<Talk> talkList) {
    Conference conference = new Conference();

    // sort all the talks in descending order
    talkList.sort(new TalkComparator());
    int trackCount = 0;

    // run this loop till all the talks are scheduled.
    while (0 != talkList.size()) {

      // create and fill morning session.
      TrackSession morningSession = new TrackSession(Session.MORNING);
      fillTrackSession(morningSession, talkList);

      // create and fill lunch session.
      TrackSession lunchSession = new TrackSession(Session.LUNCH);
      FixedEvent lunch = FixedEvent.LUNCH;
      lunchSession.addEvent(new Event(lunch.startTime, lunch.title, lunch.duration));

      // create and fill afternoon session.
      TrackSession afternoonSession = new TrackSession(Session.AFTERNOON);
      fillTrackSession(afternoonSession, talkList);

      // create and fill networking session.
      TrackSession networkingSession = new TrackSession(Session.NETWORKING);
      FixedEvent networking = FixedEvent.NETWORKING;
      networkingSession.addEvent(new Event(networking.startTime, networking.title, networking.duration));

      // add all the slots for the day into the track.
      Track track = new Track(++trackCount, new ArrayList<>());
      track.addtrackSession(morningSession);
      track.addtrackSession(lunchSession);
      track.addtrackSession(afternoonSession);
      track.addtrackSession(networkingSession);
      // add track to the conference.
      conference.addTrack(track);
    }

    return conference;
  }

  private void fillTrackSession(TrackSession slot, List<Talk> talks) {
    // initialize the slot start time.
    Calendar currentStartTime = slot.getType().startTime;
    for (Iterator<Talk> talksIterator = talks.iterator(); talksIterator.hasNext();) {
      Talk talk = talksIterator.next();
      if (slot.hasRoomFor(talk)) {
        // add an event to the slot at the currentStartTime calculated.
        slot.addEvent(new Event(currentStartTime, talk.getTitle(), talk.getMinutes()));
        // calculate the next start time based on the current start time and current talk duration.
        currentStartTime = ConferenceUtils.getNextStartTime(currentStartTime, talk);
        // remove the talk from the list. This means that the talk has been scheduled in the conference.
        talksIterator.remove();
      }
    }
  }

}
