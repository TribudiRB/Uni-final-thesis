package com.bcuzek.magisterka.controllers.player.model.utils;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Record;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;
import com.bcuzek.magisterka.infrastructure.preference.IPreference;

import java.util.ArrayList;
import java.util.List;

import lombok.experimental.Builder;

/**
 * Created by robert on 23.04.2017.
 */

@Builder
public class LastTrackManager implements ILastTrackManager {
    IPreference service;
    IRecordRepository recordRepository;
    ITrackRepository trackRepository;
    List<Integer> lastUsed;
    long trackSize;

    @Override
    public int size() {
        return lastUsed.size();
    }

    @Override
    public int get(int index) {
        return lastUsed.get(index);
    }

    public void createLastTrack() {
        List<Integer> last = service.getLastUsed();
        if (last == null) last = new ArrayList<>();
        List<Record> customList = recordRepository.getAll();
        lastUsed = new ArrayList<>(5);
        for (Integer element : last)
            if (element >= trackSize) {
                for (Record c : customList)
                    if (c.getUuid() == element)
                        lastUsed.add(element);
            } else
                lastUsed.add(element);
    }

    @Override
    public void addLastTrack(int element) {
        if (lastUsed.contains(element))
            lastUsed.remove(lastUsed.indexOf(element));
        if (lastUsed.size() > 4)
            lastUsed.remove(lastUsed.size() - 1);
        lastUsed.add(0, element);
        service.writeUsedList(lastUsed);
    }

}
