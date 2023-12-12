package com.example.demo.domain.service.hcp;

import com.example.demo.domain.HealthcareProvider;
import com.example.demo.domain.HealthcareProviderStatus;

import java.util.List;
import java.util.Optional;

public interface HealthcareProviderService {

    Optional<HealthcareProvider> findById(Integer id);

    List<HealthcareProvider> findByIdNameOrStatus(Integer id, String name, HealthcareProviderStatus status);
}
