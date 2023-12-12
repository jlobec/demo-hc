package com.example.demo.application.rest.v1.mapper;

import com.example.demo.application.rest.v1.response.address.AddressV1Response;
import com.example.demo.domain.Address;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddressV1ResponseMapper {

    public static AddressV1Response toAddressV1Response(Address address) {
        if (!Objects.isNull(address)) {
            return new AddressV1Response(
                    address.getId(),
                    address.getAddr1(),
                    address.getAddr2(),
                    address.getCity(),
                    address.getState(),
                    address.getZip(),
                    address.getStatus().toString().toLowerCase()
            );
        }
        return null;
    }

    public static List<AddressV1Response> toAddressV1Responses(List<Address> addresses) {
        if (!Objects.isNull(addresses)) {
            return addresses.stream().map(AddressV1ResponseMapper::toAddressV1Response).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
