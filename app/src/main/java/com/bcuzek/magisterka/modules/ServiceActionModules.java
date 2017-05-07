package com.bcuzek.magisterka.modules;

import android.content.Context;

import com.bcuzek.magisterka.infrastructure.constans.qualifiers.ServiceQualifiers;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;
import com.bcuzek.magisterka.service.actionHolder.ActionHolder;
import com.bcuzek.magisterka.service.actionHolder.IActionHolder;
import com.bcuzek.magisterka.service.actionHolder.action.ServiceAction;
import com.bcuzek.magisterka.service.actionHolder.action.StartAction;
import com.bcuzek.magisterka.service.phoneState.IPhoneState;
import com.bcuzek.magisterka.service.phoneState.PhoneState;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Provides;

@dagger.Module
public class ServiceActionModules {

    @Singleton
    @Provides
    public IActionHolder getActionHolder(@Named(ServiceQualifiers.PAUSE) ServiceAction pause,
                                         @Named(ServiceQualifiers.START) ServiceAction start,
                                         @Named(ServiceQualifiers.STOP) ServiceAction stop,
                                         @Named(ServiceQualifiers.STOP_SERVICE) ServiceAction stopService,
                                         @Named(ServiceQualifiers.PHONE_START) ServiceAction phoneStart,
                                         @Named(ServiceQualifiers.PHONE_STOP) ServiceAction phoneStop,
                                         @Named(ServiceQualifiers.RESTART) ServiceAction restartTime,
                                         @Named(ServiceQualifiers.EXIT) ServiceAction exitApplication) {
        return ActionHolder
                .builder()
                .pause(pause)
                .start(start)
                .stop(stop)
                .stopService(stopService)
                .phoneStart(phoneStart)
                .phoneStop(phoneStop)
                .restartTime(restartTime)
                .exitApplication(exitApplication)
                .build();
    }


    @Provides
    @Named(ServiceQualifiers.PAUSE)
    @Singleton
    public ServiceAction getPauseAction() {
        return (action, intent) -> action.onPause();
    }

    @Provides
    @Singleton
    @Named(ServiceQualifiers.START)
    public ServiceAction getStartAction() {
        return new StartAction();
    }

    @Provides
    @Singleton
    @Named(ServiceQualifiers.STOP)
    public ServiceAction getStopAction() {
        return (action, intent) -> action.onStop();
    }

    @Provides
    @Singleton
    @Named(ServiceQualifiers.STOP_SERVICE)
    public ServiceAction getStopServiceAction() {
        return (action, intent) -> action.shutdownService();
    }

    @Provides
    @Singleton
    @Named(ServiceQualifiers.PHONE_START)
    public ServiceAction getPhoneStartAction() {
        return (action, intent) -> action.onPhoneCallStart();
    }

    @Provides
    @Singleton
    @Named(ServiceQualifiers.PHONE_STOP)
    public ServiceAction getPhoneStopAction() {
        return (action, intent) -> action.onPhoneCallStop();
    }

    @Provides
    @Singleton
    @Named(ServiceQualifiers.RESTART)
    public ServiceAction getRestartTimeAction() {
        return (action, intent) -> action.restartTime();
    }

    @Provides
    @Singleton
    @Named(ServiceQualifiers.EXIT)
    public ServiceAction getExitApplicationAction() {
        return (action, intent) -> action.exitApplication();
    }

    @Singleton
    @Provides
    public IPhoneState getIPhoneState(Context context, IPreference service) {
        return new PhoneState(context, service);
    }
}
