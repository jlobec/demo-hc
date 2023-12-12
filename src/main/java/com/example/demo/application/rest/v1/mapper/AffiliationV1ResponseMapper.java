package com.example.demo.application.rest.v1.mapper;

import com.example.demo.application.rest.v1.response.affiliation.AffiliateV1Response;
import com.example.demo.application.rest.v1.response.affiliation.AffiliationV1Response;
import com.example.demo.domain.Affiliation;
import com.example.demo.domain.AffiliationType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AffiliationV1ResponseMapper {

    public static List<AffiliationV1Response> toAffiliationV1Responses(List<Affiliation> affiliations) {
        if (!Objects.isNull(affiliations)) {
            return affiliations.stream().map(AffiliationV1ResponseMapper::toAffiliationV1Response).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public static AffiliationV1Response toAffiliationV1Response(Affiliation affiliation) {
        AffiliateV1Response parent = null;
        AffiliateV1Response child = null;

        if (AffiliationType.HCO_HCO.equals(affiliation.getAffiliationType())) {
            parent = HealthcareOrganizationV1ResponseMapper.toHcoV1Response(affiliation.getParent());
            child = HealthcareOrganizationV1ResponseMapper.toHcoV1Response(affiliation.getChild());
        }

        if (AffiliationType.HCO_HCP.equals(affiliation.getAffiliationType())) {
            parent = HealthcareProviderV1ResponseMapper.toHcpV1Response(affiliation.getParent());
            child = HealthcareOrganizationV1ResponseMapper.toHcoV1Response(affiliation.getChild());
        }

        if (AffiliationType.HCP_HCO.equals(affiliation.getAffiliationType())) {
            parent = HealthcareProviderV1ResponseMapper.toHcpV1Response(affiliation.getParent());
            child = HealthcareOrganizationV1ResponseMapper.toHcoV1Response(affiliation.getChild());
        }

        if (AffiliationType.HCP_HCP.equals(affiliation.getAffiliationType())) {
            parent = HealthcareProviderV1ResponseMapper.toHcpV1Response(affiliation.getParent());
            child = HealthcareProviderV1ResponseMapper.toHcpV1Response(affiliation.getChild());
        }

        var status = affiliation.getStatus().toString().toLowerCase();
        var type = affiliation.getAffiliationType().name();

        return new AffiliationV1Response(affiliation.getId(), parent, child, status, type);
    }


}
