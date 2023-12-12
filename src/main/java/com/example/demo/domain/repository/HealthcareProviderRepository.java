package com.example.demo.domain.repository;

import com.example.demo.infrastructure.repository.mysql.hcp.HealthcareProviderEntity;
import com.example.demo.infrastructure.repository.mysql.hcp.HealthcareProviderEntityStatus;

import java.util.List;
import java.util.Optional;

public interface HealthcareProviderRepository {

    Optional<HealthcareProviderEntity> findById(Integer id);

    List<HealthcareProviderEntity> findAllById(List<Integer> ids);

    List<HealthcareProviderEntity> findAllWith(Integer id, String name, HealthcareProviderEntityStatus status);
}
