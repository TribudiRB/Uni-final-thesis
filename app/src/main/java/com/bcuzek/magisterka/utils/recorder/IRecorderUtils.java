package com.bcuzek.magisterka.utils.recorder;

public interface IRecorderUtils {
    void startRecording(String name);

    void stopRecording();

    boolean isRecording();
}
