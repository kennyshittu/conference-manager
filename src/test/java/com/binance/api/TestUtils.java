package com.binance.api;

import com.binance.api.models.Conference;
import com.binance.api.services.ConferenceManagerService;
import com.binance.api.services.ConferenceManagerServiceImpl;
import com.binance.api.utils.ConferenceUtilsTest;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestUtils {
    public static InputStream exampleInputStream() {
        return ConferenceUtilsTest.class.getResourceAsStream("sample.csv");
    }

    public static Conference exampleConference() {
        ConferenceManagerService service = new ConferenceManagerServiceImpl();
        return service.generateConferenceTracks(exampleInputStream());
    }

    public static String exampleStringifiedConference() throws Exception {
        InputStream resourceStream = ConferenceUtilsTest.class.getResourceAsStream("output.txt");
        return IOUtils.toString(resourceStream, StandardCharsets.UTF_8);
    }
}
