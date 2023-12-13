package com.example.demo.application.rest.v1.mapper;

import com.example.demo.application.rest.v1.response.affiliation.AffiliateV1Response;
import com.example.demo.application.rest.v1.response.hcp.HealthcareProviderBaseV1Response;
import com.example.demo.application.rest.v1.response.hcp.HealthcareProviderV1Response;
import com.example.demo.domain.Affiliable;
import com.example.demo.domain.HealthcareProvider;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HealthcareProviderV1ResponseMapper {

    public static AffiliateV1Response toHcpV1Response(Affiliable hcp) {
        return mapToHcpV1Response(hcp);
    }

    public static HealthcareProviderV1Response toHcpV1Response(HealthcareProvider hcp) {
        return mapToHcpV1Response(hcp);
    }

    public static List<HealthcareProviderV1Response> toHcpV1Responses(List<HealthcareProvider> hcps) {
        return mapToHcpExtendedV1Responses(hcps);
    }

    public static List<HealthcareProviderBaseV1Response> toHcpBaseV1Responses(List<HealthcareProvider> hcps) {
        return mapToHcpBaseExtendedV1Responses(hcps);
    }

    private static List<HealthcareProviderBaseV1Response> mapToHcpBaseExtendedV1Responses(List<HealthcareProvider> hcps) {
        if (!Objects.isNull(hcps)) {
            return hcps.stream()
                    .map(HealthcareProviderV1ResponseMapper::mapToHcpBaseV1Response)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static List<HealthcareProviderV1Response> mapToHcpExtendedV1Responses(List<HealthcareProvider> hcps) {
        if (!Objects.isNull(hcps)) {
            return hcps.stream()
                    .map(HealthcareProviderV1ResponseMapper::mapToHcpV1Response)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static HealthcareProviderBaseV1Response mapToHcpBaseV1Response(HealthcareProvider hcp) {
        if (!Objects.isNull(hcp)) {
            return new HealthcareProviderBaseV1Response(
                    hcp.getId(),
                    hcp.getName(),
                    hcp.getStatus().toString().toLowerCase(),
                    AddressV1ResponseMapper.toAddressV1Response(hcp.getActiveAddress()));
        }

        return null;
    }

    private static HealthcareProviderV1Response mapToHcpV1Response(HealthcareProvider hcp) {
        if (!Objects.isNull(hcp)) {
            return new HealthcareProviderV1Response(
                    hcp.getId(),
                    hcp.getName(),
                    hcp.getStatus().toString().toLowerCase(),
                    AddressV1ResponseMapper.toAddressV1Response(hcp.getActiveAddress()),
                    AffiliationV1ResponseMapper.toAffiliationV1Responses(hcp.getAffiliations()));
        }

        return null;
    }

    private static HealthcareProviderV1Response mapToHcpV1Response(Affiliable hcp) {
        if (!Objects.isNull(hcp)) {
            return new HealthcareProviderV1Response(
                    hcp.getId(),
                    hcp.getName(),
                    hcp.getStatus().toString().toLowerCase(),
                    null,
                    null);
        }

        return null;
    }
}
