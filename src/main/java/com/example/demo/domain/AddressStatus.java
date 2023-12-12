package com.example.demo.domain;

import java.util.Objects;

public enum AddressStatus {
    ACTIVE,
    INACTIVE;

    public static AddressStatus of(String status) {
        if (!Objects.isNull(status)) {
            for (AddressStatus v : values()) {
                if (v.toString().equalsIgnoreCase(status)) {
                    return v;
                }
            }
        }

        return null;
    }
}
