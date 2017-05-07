package com.bcuzek.magisterka.utils.trackTitle;

import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.track.ITrackRepository;
import com.bcuzek.magisterka.utils.file.IFileUtils;
import com.bcuzek.magisterka.utils.string.IStringUtils;

import lombok.experimental.Builder;

/**
 * Created by robert on 21.04.2017.
 */
@Builder
public class TrackTitleUtils implements ITrackTitleUtils {
    private IStringUtils utils;
    private IRecordRepository recordRepository;
    private ITrackRepository trackRepository;
    private IFileUtils fileUtils;
    private long trackSize;

    public String getTitle(int pos) {
        return pos < trackSize ?
                utils.getStringById(trackRepository.getById(pos).getTitle()) :
                recordRepository.getById(pos).getTitle().substring(fileUtils.getPrefix());
    }
}
