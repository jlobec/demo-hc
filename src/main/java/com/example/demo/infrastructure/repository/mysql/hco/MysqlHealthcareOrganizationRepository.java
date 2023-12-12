package com.example.demo.infrastructure.repository.mysql.hco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlHealthcareOrganizationRepository extends
        JpaRepository<HealthcareOrganizationEntity, Integer>,
        JpaSpecificationExecutor<HealthcareOrganizationEntity> {
}