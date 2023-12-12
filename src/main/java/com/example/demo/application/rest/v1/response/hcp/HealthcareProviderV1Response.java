package com.example.demo.application.rest.v1.response.hcp;

import com.example.demo.application.rest.v1.response.address.AddressV1Response;
import com.example.demo.application.rest.v1.response.affiliation.AffiliateV1Response;
import com.example.demo.application.rest.v1.response.affiliation.AffiliationV1Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthcareProviderV1Response implements AffiliateV1Response {
    private final Integer id;
    private final String name;
    private final String status;
    private final AddressV1Response address;
    private final List<AffiliationV1Response> affiliations;
}
