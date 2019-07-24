package com.binance.api.utils;

import com.binance.api.models.Conference;
import com.binance.api.models.Event;
import com.binance.api.models.Talk;
import com.binance.api.models.Track;
import com.binance.api.models.TrackSession;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.binance.api.models.FixedEvent.LUNCH;
import static com.binance.api.models.FixedEvent.NETWORKING;

public class ConferenceUtils {

    public static Calendar getCalendarTime(int hours, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        return cal;
    }

    public static Calendar getNextStartTime(Calendar currentStartTime, Talk currentTalk) {
        Calendar newTime = Calendar.getInstance();
        newTime.set(Calendar.HOUR_OF_DAY, currentStartTime.get(Calendar.HOUR_OF_DAY));
        newTime.set(Calendar.MINUTE, currentStartTime.get(Calendar.MINUTE));
        newTime.add(Calendar.MINUTE, currentTalk.getMinutes());
        return newTime;
    }

    public static Long getDiffInMinutes(Calendar endTime, Calendar startTime) {
        long diff = endTime.getTimeInMillis() - startTime.getTimeInMillis();
        return ((diff / 1000) / 60);
    }

    public static String stringifyConferenceResponse(Conference conference) {

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        StringBuilder builder = new StringBuilder();

        builder.append("Test Output:").append("\n");

        for (Track track : conference.getTracks()) {
            builder.append("Track ").append(track.getTrackId()).append(" :").append("\n");
            List<TrackSession> trackSessions = track.getTrackSessions();

            // Output the talks into tracks based on the totalTalks and the count of Talks.
            for (TrackSession trackSession : trackSessions) {
                for (Event event : trackSession.getEvents()) {
                    // Print the prepared talk's title for this Track
                    builder.append(sdf.format(event.getStartTime().getTime()))
                            .append(" ")
                            .append(event.getTitle());
                    if (!event.getTitle().equals(LUNCH.title) && !event.getTitle().equals(NETWORKING.title)) {
                        builder.append(" ")
                                .append(event.getDurationInMinutes())
                                .append("mins");
                    }

                    builder.append("\n");
                }
            }

            builder.append("\n");
        }

        return builder.toString();
    }

}
