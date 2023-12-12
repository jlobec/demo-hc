package com.example.demo.infrastructure.repository.mysql.hcp;

import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class HcpQuerySpecification {
    public static Specification<HealthcareProviderEntity> withCriteria(
            Integer id, String name, HealthcareProviderEntityStatus status) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.isTrue(criteriaBuilder.literal(true));

            if (!Objects.isNull(id)) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
            }

            if (!Objects.isNull(name)) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), name.toUpperCase())
                );
            }

            if (!Objects.isNull(status)) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status.name()));
            }

            return predicate;
        };
    }
}


