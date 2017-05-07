package com.bcuzek.magisterka.infrastructure.musicPlayer;

import java.io.IOException;

public interface IMusicPlayer {
    void play(int sound) throws IOException;

    void stop();
}
