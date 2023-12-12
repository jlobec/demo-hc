package com.example.demo.domain;

import java.util.Objects;

public enum AffiliationStatus {
    ACTIVE,
    INACTIVE;

    public static AffiliationStatus of(String status) {
        if (!Objects.isNull(status)) {
            for (AffiliationStatus v : values()) {
                if (v.toString().equalsIgnoreCase(status)) {
                    return v;
                }
            }
        }

        return null;
    }
}
