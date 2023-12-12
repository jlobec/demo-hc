package com.example.demo.domain.service.hco;

import com.example.demo.domain.HealthcareOrganization;
import com.example.demo.domain.HealthcareOrganizationStatus;

import java.util.List;
import java.util.Optional;

public interface HealthcareOrganizationService {

    Optional<HealthcareOrganization> findById(Integer id);

    List<HealthcareOrganization> findByIdNameOrStatus(Integer id, String name, HealthcareOrganizationStatus status);
}
