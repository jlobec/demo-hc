package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class HealthcareProvider implements Affiliable {
    private Integer id;
    private String name;
    private List<Address> addresses;
    private HealthcareProviderStatus status;
    private List<Affiliation> affiliations;

    public Address getActiveAddress(){
        if (!Objects.isNull(addresses)){
            addresses.sort(Comparator.comparing(a -> AddressStatus.ACTIVE.equals(a.getStatus()), Comparator.reverseOrder()));
            return addresses.stream().findFirst().orElse(null);
        }
        return null;
    }
}
