package com.example.demo.infrastructure.repository.mysql.hcp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MysqlHealthcareProviderRepository extends
        JpaRepository<HealthcareProviderEntity, Integer>,
        JpaSpecificationExecutor<HealthcareProviderEntity> {
}
