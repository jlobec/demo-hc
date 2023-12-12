package com.example.demo.domain;

import java.util.Objects;

public enum HealthcareOrganizationStatus implements AffiliableStatus {
    ACTIVE,
    INACTIVE;

    public static HealthcareOrganizationStatus of(String status) {
        if (!Objects.isNull(status)) {
            for (HealthcareOrganizationStatus v : values()) {
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
