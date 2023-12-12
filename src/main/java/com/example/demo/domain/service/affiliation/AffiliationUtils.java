package com.example.demo.domain.service.affiliation;

import com.example.demo.infrastructure.repository.mysql.affiliation.AffiliationEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AffiliationUtils {

    private static final String HCO_AS_PARENT_REGEX = "^HCO_.*";
    private static final String HCO_AS_CHILD_REGEX = ".*_HCO$";
    private static final String HCP_AS_PARENT_REGEX = "^HCP_.*";
    private static final String HCP_AS_CHILD_REGEX = ".*_HCP$";

    public static List<Integer> getHcpIds(List<AffiliationEntity> affiliationEntities) {
        return affiliationEntities.stream()
                .map(AffiliationUtils::getHcpIds)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Integer> getHcoIds(List<AffiliationEntity> affiliationEntities) {
        return affiliationEntities.stream()
                .map(AffiliationUtils::getHcoIds)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Integer> getHcoIds(AffiliationEntity affiliationEntity) {
        return getEntityIds(affiliationEntity, HCO_AS_PARENT_REGEX, HCO_AS_CHILD_REGEX);
    }

    public static List<Integer> getHcpIds(AffiliationEntity affiliationEntity) {
        return getEntityIds(affiliationEntity, HCP_AS_PARENT_REGEX, HCP_AS_CHILD_REGEX);
    }

    private static List<Integer> getEntityIds(AffiliationEntity affiliationEntity, String parentRegex, String childRegex) {
        var ids = new ArrayList<Integer>();

        String type = affiliationEntity.getType();
        if (Objects.isNull(type)){
            return ids;
        }

        if (type.matches(parentRegex)) {
            ids.add(affiliationEntity.getParentLink());
        }

        if (type.matches(childRegex)) {
            ids.add(affiliationEntity.getChildLink());
        }

        return ids;
    }
}
