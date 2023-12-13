package com.example.demo.application.rest.v1.response.hco;

import com.example.demo.application.rest.v1.response.address.AddressV1Response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthcareOrganizationBaseV1Response {
    private final Integer id;
    private final String name;
    private final String status;
    private final AddressV1Response address;
}
