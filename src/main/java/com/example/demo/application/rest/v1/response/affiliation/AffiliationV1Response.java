package com.example.demo.application.rest.v1.response.affiliation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AffiliationV1Response {
    private final Integer id;
    private final AffiliateV1Response parent;
    private final AffiliateV1Response child;
    private final String status;
    private final String type;
}
