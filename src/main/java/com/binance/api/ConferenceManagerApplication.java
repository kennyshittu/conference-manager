package com.binance.api;

import com.binance.api.health.SimpleHealthCheck;
import com.binance.api.modules.ConferenceManagerComponent;
import com.binance.api.modules.DaggerConferenceManagerComponent;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

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
        super.initialize(bootstrap);
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    }

    @Override
    public void run(final ConferenceManagerConfiguration configuration,
                    final Environment environment) {
        ConferenceManagerComponent conferenceManagerComponent = DaggerConferenceManagerComponent.builder()
                .build();
        environment.jersey().register(conferenceManagerComponent.provideConferenceManagerResource());
        environment.jersey().register(MultiPartFeature.class);
        environment.healthChecks().register("HealthCheck", new SimpleHealthCheck());
    }
}
