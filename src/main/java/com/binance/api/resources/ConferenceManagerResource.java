package com.binance.api.resources;

import com.binance.api.models.Conference;
import com.binance.api.models.Event;
import com.binance.api.models.Track;
import com.binance.api.models.TrackSession;
import com.binance.api.services.ConferenceManagerService;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Path("/tracks")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ConferenceManagerResource {

  private ConferenceManagerService conferenceManagerService;

  @Inject
  ConferenceManagerResource(ConferenceManagerService conferenceManagerService) {
    this.conferenceManagerService = conferenceManagerService;
  }

  // An endpoint that takes a csv file of talks and return tracks
  @Path("upload")
  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public String uploadFile(@FormDataParam("file") InputStream fileInputStream) {
    return stringifyConferenceResponse(conferenceManagerService.getTracks(fileInputStream));
  }

  public String stringifyConferenceResponse (Conference conference) {

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
    StringBuilder builder = new StringBuilder();

    builder.append("Test Output:").append("\n");

    for(Track track : conference.getTracks()){
      builder.append("Track ").append(track.getTrackId()).append(" :").append("\n");
      List<TrackSession> trackSessions = track.getTrackSessions();

      // Output the talks into tracks based on the totalTalks and the count of Talks.
      for (TrackSession trackSession : trackSessions) {
        for (Event event : trackSession.getEvents()) {
          // Print the prepared talk's title for this Track
          builder.append(sdf.format(event.getStartTime().getTime()))
            .append(" ")
            .append(event.getTitle())
            .append(" ")
            .append(event.getDurationInMinutes())
            .append("mins")
            .append("\n");
        }
      }

      builder.append("\n");
    }

    return builder.toString();
  }

}
