package com.bcuzek.magisterka.infrastructure.domain.repositories.track.factory;

import com.bcuzek.magisterka.infrastructure.domain.repositories.IRepository;

/**
 * Created by robert on 21.04.2017.
 */

public interface IRepositoryFactory {
    IRepository getRepository(int soundUUID);
}
