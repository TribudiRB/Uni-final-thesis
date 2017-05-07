package com.bcuzek.magisterka.controllers.soundList.model.track;

import com.bcuzek.magisterka.controllers.soundList.model.TrackViewModel;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;

import java.util.List;

import lombok.experimental.Builder;
import rx.Observable;

@Builder
public class DataView implements ITrackView {
    private final ITrackRepository trackRepository;
    private final ICalculatorUtils calculatorUtils;
    private final IStringUtils utils;

    public List<TrackViewModel> prepareData() {
        return Observable
                .from(trackRepository.getAll())
                .map(track -> new TrackViewModel().create(calculatorUtils, utils, track, trackRepository.sumDuration(track)))
                .toList()
                .toBlocking()
                .single();
    }
}
