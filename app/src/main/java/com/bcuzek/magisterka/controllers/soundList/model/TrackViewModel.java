package com.bcuzek.magisterka.controllers.soundList.model;

import com.bcuzek.magisterka.infrastructure.domain.dataBase.entities.Track;
import com.bcuzek.magisterka.utils.calculator.ICalculatorUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;

import lombok.Data;

/**
 * Created by robert on 21.04.2017.
 */
@Data
public class TrackViewModel extends ViewModel<Track> {
    private String names;
    private String description;
    private Track tracks;

    public TrackViewModel create(ICalculatorUtils calculatorUtils, IStringUtils utils, final Track track, final long currentTime) {
        names = utils.getStringById(track.getTitle());
        description = calculate(utils, calculatorUtils, currentTime, track);
        tracks = track;
        return this;
    }
}
