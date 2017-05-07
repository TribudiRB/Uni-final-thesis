package com.bcuzek.magisterka.infrastructure.domain.dataBase;

/**
 * Created by robert on 06.01.2017.
 */

public class DataBaseException extends IllegalStateException {

    private static final String exception = "Database is configured";

    public DataBaseException() {
        super(exception);
    }
}
