package com.example.demo.application.rest.v1.mapper;

import com.example.demo.application.rest.v1.response.affiliation.AffiliateV1Response;
import com.example.demo.application.rest.v1.response.hco.HealthcareOrganizationBaseV1Response;
import com.example.demo.application.rest.v1.response.hco.HealthcareOrganizationV1Response;
import com.example.demo.domain.Affiliable;
import com.example.demo.domain.HealthcareOrganization;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HealthcareOrganizationV1ResponseMapper {

    public static AffiliateV1Response toHcoV1Response(Affiliable hco) {
        return mapToHcoV1Response(hco);
    }

    public static HealthcareOrganizationV1Response toHcoV1Response(HealthcareOrganization hco) {
        return mapToHcoV1Response(hco);
    }

    public static List<HealthcareOrganizationV1Response> toHcoV1Responses(List<HealthcareOrganization> hcos) {
        return mapToHcoV1Responses(hcos);
    }

    public static List<HealthcareOrganizationBaseV1Response> toHcoBaseV1Responses(List<HealthcareOrganization> hcos) {
        return mapToHcoBaseV1Responses(hcos);
    }

    private static List<HealthcareOrganizationV1Response> mapToHcoV1Responses(List<HealthcareOrganization> hcos) {
        if (!Objects.isNull(hcos)) {
            return hcos.stream()
                    .map(HealthcareOrganizationV1ResponseMapper::mapToHcoV1Response)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static List<HealthcareOrganizationBaseV1Response> mapToHcoBaseV1Responses(List<HealthcareOrganization> hcos) {
        if (!Objects.isNull(hcos)) {
            return hcos.stream()
                    .map(HealthcareOrganizationV1ResponseMapper::mapToHcoBaseV1Response)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static HealthcareOrganizationV1Response mapToHcoV1Response(HealthcareOrganization hco) {
        if (!Objects.isNull(hco)) {
            return new HealthcareOrganizationV1Response(
                    hco.getId(),
                    hco.getName(),
                    hco.getStatus().toString().toLowerCase(),
                    AddressV1ResponseMapper.toAddressV1Response(hco.getActiveAddress()),
                    AffiliationV1ResponseMapper.toAffiliationV1Responses(hco.getAffiliations()));
        }

        return null;
    }

    private static HealthcareOrganizationBaseV1Response mapToHcoBaseV1Response(HealthcareOrganization hco) {
        if (!Objects.isNull(hco)) {
            return new HealthcareOrganizationBaseV1Response(
                    hco.getId(),
                    hco.getName(),
                    hco.getStatus().toString().toLowerCase(),
                    AddressV1ResponseMapper.toAddressV1Response(hco.getActiveAddress()));
        }

        return null;
    }

    private static HealthcareOrganizationV1Response mapToHcoV1Response(Affiliable hco) {
        if (!Objects.isNull(hco)) {
            return new HealthcareOrganizationV1Response(
                    hco.getId(),
                    hco.getName(),
                    hco.getStatus().toString().toLowerCase(),
                    null,
                    null);
        }

        return null;
    }
}
