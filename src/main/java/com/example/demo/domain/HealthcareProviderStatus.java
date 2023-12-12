package com.example.demo.domain;

import java.util.Objects;

public enum HealthcareProviderStatus implements AffiliableStatus {
    ACTIVE,
    INACTIVE;

    public static HealthcareProviderStatus of(String status) {
        if (!Objects.isNull(status)) {
            for (HealthcareProviderStatus v : values()) {
                if (v.toString().equalsIgnoreCase(status)) {
                    return v;
                }
            }
        }

        return null;
    }

    @Override
    public String getStatus() {
        return name().toLowerCase();
    }
}
