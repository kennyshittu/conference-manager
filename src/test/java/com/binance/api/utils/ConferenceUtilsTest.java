package com.binance.api.utils;

import com.binance.api.models.Talk;
import org.junit.Test;

import java.util.Calendar;

import static com.binance.api.TestUtils.exampleConference;
import static com.binance.api.TestUtils.exampleStringifiedConference;
import static com.binance.api.models.Session.MORNING;
import static com.binance.api.models.Session.NETWORKING;
import static org.assertj.core.api.Assertions.assertThat;

public class ConferenceUtilsTest {

    @Test
    public void testGetCalendarTime() {
        Calendar expected = Calendar.getInstance();
        expected.set(Calendar.HOUR_OF_DAY, 10);
        expected.set(Calendar.MINUTE, 0);

        Calendar actual = ConferenceUtils.getCalendarTime(10, 0);

        assertThat(actual.getTime().toString()).isEqualTo(expected.getTime().toString());
    }

    @Test
    public void testGetNextStartTime() {
        Calendar expected = Calendar.getInstance();
        expected.set(Calendar.HOUR_OF_DAY, 10);
        expected.set(Calendar.MINUTE, 0);

        Calendar actual = ConferenceUtils.getNextStartTime(MORNING.startTime, new Talk(60, "test"));
        assertThat(actual.getTime().toString()).isEqualTo(expected.getTime().toString());
    }

    @Test
    public void testGetDiffInMinutes() {
        Long expected = 60L;
        Long actual = ConferenceUtils.getDiffInMinutes(NETWORKING.endTime, NETWORKING.startTime);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testStringifyConferenceResponse() throws Exception {
        String expected = exampleStringifiedConference();
        String actual = ConferenceUtils.stringifyConferenceResponse(exampleConference());

        assertThat(actual).isEqualTo(expected);
    }
}
