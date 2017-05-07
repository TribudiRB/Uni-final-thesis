package com.bcuzek.magisterka.modules;

import android.content.Intent;

import com.bcuzek.magisterka.infrastructure.constans.qualifiers.BroadcastQualifiers;
import com.bcuzek.magisterka.service.Service;
import com.bcuzek.magisterka.service.receiver.IBroadcastHolder;
import com.bcuzek.magisterka.service.receiver.action.IMessage;
import com.bcuzek.magisterka.service.receiver.action.PlayMessage;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 16.11.2016.
 */
@Module
public class BroadcastModules {

    @Singleton
    @Provides
    public IBroadcastHolder getBroadcastHolder(@Named(BroadcastQualifiers.MESSAGE) IMessage allIMessage,
                                               @Named(BroadcastQualifiers.PLAYER) IMessage playIMessage) {
        return validatorType -> validatorType ? playIMessage : allIMessage;
    }

    @Singleton
    @Provides
    @Named(BroadcastQualifiers.PLAYER)
    public IMessage getPlayMessage() {
        return new PlayMessage();
    }

    @Singleton
    @Provides
    @Named(BroadcastQualifiers.MESSAGE)
    public IMessage getAllMessage() {
        return (context, intent) -> context
                .startService(new Intent(context, Service.class)
                .setAction(intent.getAction()));
    }
}
