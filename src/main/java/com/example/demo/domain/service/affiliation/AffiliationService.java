package com.example.demo.domain.service.affiliation;

import com.example.demo.domain.Affiliation;
import com.example.demo.domain.AffiliationStatus;

import java.util.List;
import java.util.Optional;

public interface AffiliationService {

    Optional<Affiliation> findById(Integer id);

    List<Affiliation> findByHealthcareOrganization(Integer hcoId);

    List<Affiliation> findByHealthcareProvider(Integer hcpId);

    List<Affiliation> findByCriteria(Integer id, String type, AffiliationStatus status);
}
