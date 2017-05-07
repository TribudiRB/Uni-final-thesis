package com.bcuzek.magisterka.utils.validator;

import lombok.experimental.Builder;

@Builder
public class Validator implements IValidator {
    private String pattern;

    public boolean validData(String input) {
        return input.matches(pattern);
    }
}
