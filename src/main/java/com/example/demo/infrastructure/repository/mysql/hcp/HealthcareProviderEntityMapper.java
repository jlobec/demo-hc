package com.example.demo.infrastructure.repository.mysql.hcp;

import com.example.demo.domain.HealthcareProvider;
import com.example.demo.domain.HealthcareProviderStatus;
import com.example.demo.infrastructure.repository.mysql.address.AddressEntityMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HealthcareProviderEntityMapper {
    public static HealthcareProviderEntityStatus toHcpEntityStatus(HealthcareProviderStatus status) {
        if (!Objects.isNull(status)) {
            return HealthcareProviderStatus.ACTIVE.equals(status) ? HealthcareProviderEntityStatus.A
                    : HealthcareProviderEntityStatus.I;
        }

        return null;
    }

    public static List<HealthcareProvider> toHcps(List<HealthcareProviderEntity> entities) {
        if (!Objects.isNull(entities)) {
            return entities.stream().map(HealthcareProviderEntityMapper::toHcp).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public static HealthcareProvider toHcp(HealthcareProviderEntity entity) {
        return new HealthcareProvider(
                entity.getId(),
                entity.getName(),
                AddressEntityMapper.toAddresses(entity.getAddresses()),
                mapStatus(entity.getStatus()),
                null
        );
    }

    private static HealthcareProviderStatus mapStatus(String status) {
        return status.equalsIgnoreCase(HealthcareProviderEntityStatus.A.name())
                ? HealthcareProviderStatus.ACTIVE
                : HealthcareProviderStatus.INACTIVE;
    }
}
