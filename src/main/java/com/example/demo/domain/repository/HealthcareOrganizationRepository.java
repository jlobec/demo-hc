package com.example.demo.domain.repository;

import com.example.demo.infrastructure.repository.mysql.hco.HealthcareOrganizationEntity;
import com.example.demo.infrastructure.repository.mysql.hco.HealthcareOrganizationEntityStatus;

import java.util.List;
import java.util.Optional;

public interface HealthcareOrganizationRepository {

    Optional<HealthcareOrganizationEntity> findById(Integer id);

    List<HealthcareOrganizationEntity> findAllById(List<Integer> ids);

    List<HealthcareOrganizationEntity> findAllWith(Integer id, String name, HealthcareOrganizationEntityStatus status);
}
