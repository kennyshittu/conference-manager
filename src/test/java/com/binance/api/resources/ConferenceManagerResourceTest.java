package com.binance.api.resources;

import com.binance.api.services.ConferenceManagerService;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.InputStream;

import static com.binance.api.TestUtils.exampleConference;
import static com.binance.api.TestUtils.exampleInputStream;
import static com.binance.api.TestUtils.exampleStringifiedConference;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ConferenceManagerResourceTest {
    @Test
    public void testUploadFile() throws Exception {
        InputStream inputStream = exampleInputStream();
        String expected = exampleStringifiedConference();

        ConferenceManagerService service = Mockito.mock(ConferenceManagerService.class);
        ConferenceManagerResource resource = new ConferenceManagerResource(service);

        when(service.generateConferenceTracks(inputStream)).thenReturn(exampleConference());

        String actual = resource.uploadFile(inputStream);

        assertThat(actual).isEqualTo(expected);
    }
}
