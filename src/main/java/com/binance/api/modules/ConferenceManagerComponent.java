package com.binance.api.modules;

import com.binance.api.resources.ConferenceManagerResource;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ConferenceManagerModule.class})
public interface ConferenceManagerComponent  {

  ConferenceManagerResource provideConferenceManagerResource();

}
