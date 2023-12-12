package com.example.demo.infrastructure.repository.mysql.address;

import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class AddressQuerySpecification {

    public static Specification<AddressEntity> withCriteria(Integer id, String addr1, String addr2, String city,
                                                            String state, String zip, AddressEntityStatus status) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.isTrue(criteriaBuilder.literal(true));

            if (!Objects.isNull(id)) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), id));
            }

            if (!Objects.isNull(addr1)) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("addr1")), addr1.toUpperCase())
                );
            }

            if (!Objects.isNull(addr2)) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("addr2")), addr2.toUpperCase())
                );
            }

            if (!Objects.isNull(city)) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("city")), city.toUpperCase())
                );
            }

            if (!Objects.isNull(state)) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("state")), state.toUpperCase())
                );
            }

            if (!Objects.isNull(zip)) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("zip")), zip.toUpperCase())
                );
            }

            if (!Objects.isNull(status)) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status.name()));
            }

            return predicate;
        };
    }
}
