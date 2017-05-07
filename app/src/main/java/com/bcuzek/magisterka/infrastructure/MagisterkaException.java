package com.bcuzek.magisterka.infrastructure;

/**
 * Created by robert on 06.03.2017.
 */

public class MagisterkaException extends NullPointerException {

    private static String exception = "Magisterka Application is still null";

    public MagisterkaException() {
        super(exception);
    }

}
