package com.bcuzek.magisterka.controllers.record;

import android.widget.EditText;

import com.bcuzek.magisterka.controllers.player.TimeCalculator;
import com.bcuzek.magisterka.controllers.record.animation.ISubscriptionTimer;
import com.bcuzek.magisterka.infrastructure.domain.repositories.track.record.IRecordRepository;
import com.bcuzek.magisterka.layout.circuitTimer.CircuitTimer;
import com.bcuzek.magisterka.service.model.time.ITimeHolder;
import com.bcuzek.magisterka.utils.file.IFileUtils;
import com.bcuzek.magisterka.utils.record.IRecordUtils;
import com.bcuzek.magisterka.utils.recorder.IRecorderUtils;

import java.util.Date;

import lombok.RequiredArgsConstructor;

/**
 * Created by robert on 17.10.2016.
 */

@RequiredArgsConstructor()
public class RecordModel {
    private static long currentTime;
    private final TimeCalculator timeCalculator;
    private final IRecordRepository recordRepository;
    private final IRecorderUtils recorder;
    private final IRecordUtils recordCreator;
    private final IFileUtils manager;
    private final ISubscriptionTimer scheduler;
    private final ITimeHolder holder;
    private String fileName;

    public boolean isRecording() {
        return recorder.isRecording();
    }

    public void startRecording(CircuitTimer clock, EditText inputText) {
        String name = manager.createFile(fileName);
        inputText.setEnabled(false);
        recorder.startRecording(name);
        startTimer(clock);
    }

    public void stopRecording(EditText inputText) {
        recorder.stopRecording();
        if (inputText != null) inputText.setEnabled(true);
        scheduler.stop();
    }

    private void startTimer(CircuitTimer clock) {
        long startTime = new Date().getTime();
        scheduler.start(() -> {
            currentTime = new Date().getTime() - startTime;
            holder.setCurrentTime(currentTime);
            timeCalculator.updateClockMinute(clock);
        });
    }

    public void saveRecord(CircuitTimer clock, EditText inputText) {
        recordRepository.add(recordCreator.createRecord(fileName));
        backToInitialViewState(clock, inputText);
    }

    public void createFileName(EditText inputText) {
        fileName = manager.createFileTitle(inputText.getText().toString());
    }

    public void deleteRecord(CircuitTimer clock, EditText inputText) {
        backToInitialViewState(clock, inputText);
        manager.remove(fileName);
    }

    private void backToInitialViewState(CircuitTimer clock, EditText inputText) {
        inputText.setText("");
        timeCalculator.restartClock(clock, "");
    }

}
