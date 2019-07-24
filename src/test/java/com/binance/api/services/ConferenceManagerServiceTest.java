package com.binance.api.services;

import com.binance.api.models.Conference;
import com.binance.api.models.Track;
import org.junit.Test;

import static com.binance.api.TestUtils.exampleInputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class ConferenceManagerServiceTest {

    @Test
    public void testGenerateConferenceTracks() {

        ConferenceManagerService service = new ConferenceManagerServiceImpl();
        Conference conference = service.generateConferenceTracks(exampleInputStream());

        assertThat(conference).isNotNull();
        assertThat(conference.getTracks()).isNotEmpty();
        assertThat(conference.getTracks().size()).isEqualTo(2);

        Track track1 = conference.getTracks().get(0);
        Track track2 = conference.getTracks().get(1);

        assertThat(track1.getTrackId()).isEqualTo(1);
        assertThat(track1.getTrackSessions()).isNotEmpty();
        assertThat(track2.getTrackId()).isEqualTo(2);
        assertThat(track2.getTrackSessions()).isNotEmpty();
    }
}
