package com.example.demo.application.rest.v1.validation;

import java.util.Objects;

public class RequestParamsValidator {

    private static final String ACTIVE_STATUS_VALUE = "active";
    private static final String INACTIVE_STATUS_VALUE = "inactive";

    public static boolean isValidStatus(String status) {
        if (!Objects.isNull(status)) {
            return ACTIVE_STATUS_VALUE.equalsIgnoreCase(status) || INACTIVE_STATUS_VALUE.equalsIgnoreCase(status);
        }
        return true;
    }
}
