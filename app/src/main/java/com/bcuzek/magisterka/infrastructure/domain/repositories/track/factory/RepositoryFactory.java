package com.bcuzek.magisterka.infrastructure.domain.repositories.track.factory;

import com.bcuzek.magisterka.infrastructure.domain.repositories.IRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;

import lombok.experimental.Builder;

/**
 * Created by robert on 21.04.2017.
 */

@Builder
public class RepositoryFactory implements IRepositoryFactory {

    private ITrackRepository trackRepository;
    private IRecordRepository recordRepository;
    private long trackSize;

    @Override
    public IRepository getRepository(int soundUUID) {
        return soundUUID < trackSize ? trackRepository : recordRepository;
    }
}
