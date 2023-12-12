package com.example.demo.infrastructure.repository.mysql.hco;

import com.example.demo.domain.HealthcareOrganization;
import com.example.demo.domain.HealthcareOrganizationStatus;
import com.example.demo.infrastructure.repository.mysql.address.AddressEntityMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HealthcareOrganizationEntityMapper {

    public static HealthcareOrganizationEntityStatus toHcoEntityStatus(HealthcareOrganizationStatus status) {
        if (!Objects.isNull(status)) {
            return HealthcareOrganizationStatus.ACTIVE.equals(status) ? HealthcareOrganizationEntityStatus.A
                    : HealthcareOrganizationEntityStatus.I;
        }

        return null;
    }

    public static List<HealthcareOrganization> toHcos(List<HealthcareOrganizationEntity> entities) {
        if (!Objects.isNull(entities)) {
            return entities.stream().map(HealthcareOrganizationEntityMapper::toHco).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public static List<HealthcareOrganization> toHcosLight(List<HealthcareOrganizationEntity> entities) {
        if (!Objects.isNull(entities)) {
            return entities.stream().map(HealthcareOrganizationEntityMapper::toHcoLight).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public static HealthcareOrganization toHco(HealthcareOrganizationEntity entity) {
        return new HealthcareOrganization(
                entity.getId(),
                entity.getName(),
                AddressEntityMapper.toAddresses(entity.getAddresses()),
                mapStatus(entity.getStatus()),
                null
        );
    }

    public static HealthcareOrganization toHcoLight(HealthcareOrganizationEntity entity) {
        return new HealthcareOrganization(
                entity.getId(),
                entity.getName(),
                null,
                mapStatus(entity.getStatus()),
                null
        );
    }

    private static HealthcareOrganizationStatus mapStatus(String status) {
        return status.equalsIgnoreCase(HealthcareOrganizationEntityStatus.A.name())
                ? HealthcareOrganizationStatus.ACTIVE
                : HealthcareOrganizationStatus.INACTIVE;
    }
}
