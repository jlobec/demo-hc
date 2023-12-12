package com.example.demo.infrastructure.repository.mysql.affiliation;

import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class AffiliationQuerySpecification {

    public static Specification<AffiliationEntity> withCriteria(
            Integer id, String type, AffiliationEntityStatus status) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.isTrue(criteriaBuilder.literal(true));

            if (!Objects.isNull(id)) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
            }

            if (!Objects.isNull(type)) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("type")), type.toUpperCase())
                );
            }

            if (!Objects.isNull(status)) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status.name()));
            }

            return predicate;
        };
    }
}
