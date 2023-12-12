package com.example.demo.infrastructure.repository.mysql.affiliation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlAffiliationRepository extends
        JpaRepository<AffiliationEntity, Integer>,
        JpaSpecificationExecutor<AffiliationEntity> {
}
