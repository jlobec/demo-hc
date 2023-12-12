package com.example.demo.domain;

import lombok.Value;

@Value
public class Affiliation {
    private Integer id;
    private Affiliable parent;
    private Affiliable child;
    private AffiliationStatus status;
    private AffiliationType affiliationType;
}
