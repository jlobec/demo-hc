package com.example.demo.domain;

import lombok.Value;

@Value
public class Address {
    private Integer id;
    private String addr1;
    private String addr2;
    private String city;
    private String state;
    private String zip;
    private AddressStatus status;
}
