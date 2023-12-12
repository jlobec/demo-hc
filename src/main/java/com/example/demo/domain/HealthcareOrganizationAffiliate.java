package com.example.demo.domain;

import lombok.Value;

@Value
public class HealthcareOrganizationAffiliate {
    private HealthcareOrganization organization;
    private AffiliationStatus status;
}
