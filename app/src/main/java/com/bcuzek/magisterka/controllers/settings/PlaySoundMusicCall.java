package com.bcuzek.magisterka.controllers.settings;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlaySoundMusicCall {
    YES(true),
    NO(false);

    private final boolean value;

    public static PlaySoundMusicCall toEnum(String state) {
        try {
            return valueOf(state);
        } catch (Exception ex) {
            return YES;
        }
    }
}
