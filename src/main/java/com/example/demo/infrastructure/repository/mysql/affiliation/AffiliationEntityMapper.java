package com.example.demo.infrastructure.repository.mysql.affiliation;

import com.example.demo.domain.AddressStatus;
import com.example.demo.domain.Affiliable;
import com.example.demo.domain.Affiliation;
import com.example.demo.domain.AffiliationStatus;
import com.example.demo.domain.AffiliationType;
import com.example.demo.infrastructure.repository.mysql.address.AddressEntityStatus;
import com.example.demo.infrastructure.repository.mysql.hco.HealthcareOrganizationEntity;
import com.example.demo.infrastructure.repository.mysql.hco.HealthcareOrganizationEntityMapper;
import com.example.demo.infrastructure.repository.mysql.hcp.HealthcareProviderEntity;
import com.example.demo.infrastructure.repository.mysql.hcp.HealthcareProviderEntityMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AffiliationEntityMapper {

    private static final String HCO_AS_PARENT_REGEX = "^HCO_.*";
    private static final String HCO_AS_CHILD_REGEX = ".*_HCO$";
    private static final String HCP_AS_PARENT_REGEX = "^HCP_.*";
    private static final String HCP_AS_CHILD_REGEX = ".*_HCP$";

    public static List<Affiliation> toAffiliations(List<AffiliationEntity> affiliationEntities,
                                                   List<HealthcareOrganizationEntity> hcoEntities,
                                                   List<HealthcareProviderEntity> hcpEntities){
        if (!Objects.isNull(affiliationEntities)) {
            return affiliationEntities.stream()
                    .map(a -> toAffiliation(a, hcoEntities, hcpEntities))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    public static Affiliation toAffiliation(AffiliationEntity affiliationEntity,
                                            List<HealthcareOrganizationEntity> hcoEntities,
                                            List<HealthcareProviderEntity> hcpEntities) {

        var type = affiliationEntity.getType();
        Affiliable parent = null;
        Affiliable child = null;
        if (type.matches(HCO_AS_PARENT_REGEX)) {
            var hcoEntityParent = hcoEntities.stream().filter(hco -> affiliationEntity.getParentLink().equals(hco.getId())).findFirst();
            if (hcoEntityParent.isPresent()) {
                parent = HealthcareOrganizationEntityMapper.toHco(hcoEntityParent.get());
            }
        }

        if (type.matches(HCO_AS_CHILD_REGEX)) {
            var hcoEntityChild = hcoEntities.stream().filter(hco -> affiliationEntity.getChildLink().equals(hco.getId())).findFirst();
            if (hcoEntityChild.isPresent()) {
                child = HealthcareOrganizationEntityMapper.toHco(hcoEntityChild.get());
            }
        }

        if (type.matches(HCP_AS_PARENT_REGEX)) {
            var hcpEntityParent = hcpEntities.stream().filter(hcp -> affiliationEntity.getParentLink().equals(hcp.getId())).findFirst();
            if (hcpEntityParent.isPresent()) {
                parent = HealthcareProviderEntityMapper.toHcp(hcpEntityParent.get());
            }
        }

        if (type.matches(HCP_AS_CHILD_REGEX)) {
            var hcpEntityChild = hcpEntities.stream().filter(hcp -> affiliationEntity.getChildLink().equals(hcp.getId())).findFirst();
            if (hcpEntityChild.isPresent()) {
                child = HealthcareProviderEntityMapper.toHcp(hcpEntityChild.get());
            }
        }

        return new Affiliation(
                affiliationEntity.getId(),
                parent,
                child,
                mapStatus(affiliationEntity.getStatus()),
                AffiliationType.of(affiliationEntity.getType()));
    }

    public static AffiliationEntityStatus toAffiliationEntityStatus(AffiliationStatus status) {
        if (!Objects.isNull(status)) {
            return AffiliationStatus.ACTIVE.equals(status) ? AffiliationEntityStatus.A : AffiliationEntityStatus.I;
        }
        return null;
    }

    private static AffiliationStatus mapStatus(String status) {
        return status.equalsIgnoreCase(AffiliationEntityStatus.A.name())
                ? AffiliationStatus.ACTIVE
                : AffiliationStatus.INACTIVE;
    }
}
