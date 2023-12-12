package com.example.demo.application.rest.v1.response.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressV1Response {
    private final Integer id;
    private final String addr1;
    private final String addr2;
    private final String city;
    private final String state;
    private final String zip;
    private final String status;
}
