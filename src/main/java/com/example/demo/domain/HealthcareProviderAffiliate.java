package com.example.demo.domain;

import lombok.Value;

@Value
public class HealthcareProviderAffiliate {
    HealthcareProvider provider;
    AffiliationStatus status;
}
