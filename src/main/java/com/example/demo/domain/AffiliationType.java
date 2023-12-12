package com.example.demo.domain;

import java.util.Arrays;

public enum AffiliationType {
    HCP_HCP, HCP_HCO, HCO_HCP, HCO_HCO;

    public static AffiliationType of(String type) {
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(type))
                .findFirst()
                .orElse(null);
    }
}
