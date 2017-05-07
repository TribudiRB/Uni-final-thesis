package com.bcuzek.magisterka.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TimeDelay {
    Slow(10),
    Fast(1);
    private final int value;
}