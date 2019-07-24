package com.binance.api.modules;

import com.binance.api.services.ConferenceManagerService;
import com.binance.api.services.ConferenceManagerServiceImpl;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
class ConferenceManagerModule {

    @Provides
    @Singleton
    static ConferenceManagerService provideConferenceManagerService() {
        return new ConferenceManagerServiceImpl();
    }
}
