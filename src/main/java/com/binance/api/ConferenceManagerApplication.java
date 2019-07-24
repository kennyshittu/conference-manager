package com.binance.api;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ConferenceManagerApplication extends Application<ConferenceManagerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ConferenceManagerApplication().run(args);
    }

    @Override
    public String getName() {
        return "ConferenceManager";
    }

    @Override
    public void initialize(final Bootstrap<ConferenceManagerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ConferenceManagerConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
