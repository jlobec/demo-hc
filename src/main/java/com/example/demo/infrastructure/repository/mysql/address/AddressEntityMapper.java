package com.example.demo.infrastructure.repository.mysql.address;

import com.example.demo.domain.Address;
import com.example.demo.domain.AddressStatus;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddressEntityMapper {

    public static List<Address> toAddresses(Collection<AddressEntity> entities) {
        if (!Objects.isNull(entities)) {
            return entities.stream().map(AddressEntityMapper::toAddress).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public static Address toAddress(AddressEntity entity) {
        if (!Objects.isNull(entity)) {
            return new Address(
                    entity.getId(),
                    entity.getAddr1(),
                    entity.getAddr2(),
                    entity.getCity(),
                    entity.getState(),
                    entity.getZip(),
                    entity.getStatus().equalsIgnoreCase("A") ? AddressStatus.ACTIVE : AddressStatus.INACTIVE
            );
        }
        return null;
    }

    public static AddressEntityStatus toAddressEntityStatus(AddressStatus status) {
        if (!Objects.isNull(status)) {
            return AddressStatus.ACTIVE.equals(status) ? AddressEntityStatus.A : AddressEntityStatus.I;
        }
        return null;
    }
}
