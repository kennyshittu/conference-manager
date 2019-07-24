package com.binance.api.resources;

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

import static com.binance.api.utils.ConferenceUtils.stringifyConferenceResponse;

@Path("/tracks")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ConferenceManagerResource {

    private ConferenceManagerService conferenceManagerService;

    @Inject
    ConferenceManagerResource(ConferenceManagerService conferenceManagerService) {
        this.conferenceManagerService = conferenceManagerService;
    }

    // An endpoint that takes a csv file of talks and return scheduled tracks
    @Path("upload")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String uploadFile(@FormDataParam("file") InputStream fileInputStream) {
        return stringifyConferenceResponse(conferenceManagerService.generateConferenceTracks(fileInputStream));
    }
}
