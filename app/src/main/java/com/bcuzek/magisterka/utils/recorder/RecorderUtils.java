package com.bcuzek.magisterka.utils.recorder;

import android.media.MediaRecorder;

import java.io.IOException;

import lombok.SneakyThrows;

public class RecorderUtils implements IRecorderUtils {

    private MediaRecorder mRecorder;
    private boolean recording = false;

    @Override
    public boolean isRecording() {
        return recording;
    }

    public void startRecording(String name) {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(name);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recording = true;
        tryPrepare();
        mRecorder.start();
    }

    @SneakyThrows(IOException.class)
    private void tryPrepare() {
        mRecorder.prepare();
    }

    public void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        recording = false;
        mRecorder = null;
    }

}
