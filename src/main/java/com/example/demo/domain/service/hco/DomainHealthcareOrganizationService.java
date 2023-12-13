package com.example.demo.domain.service.hco;

import com.example.demo.domain.HealthcareOrganization;
import com.example.demo.domain.HealthcareOrganizationStatus;
import com.example.demo.domain.repository.HealthcareOrganizationRepository;
import com.example.demo.domain.service.affiliation.AffiliationService;
import com.example.demo.infrastructure.repository.mysql.hco.HealthcareOrganizationEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomainHealthcareOrganizationService implements HealthcareOrganizationService {

    private final HealthcareOrganizationRepository hcoRepository;
    private final AffiliationService affiliationService;

    @Autowired
    public DomainHealthcareOrganizationService(HealthcareOrganizationRepository hcoRepository, AffiliationService affiliationService) {
        this.hcoRepository = hcoRepository;
        this.affiliationService = affiliationService;
    }

    @Override
    public Optional<HealthcareOrganization> findById(Integer id) {
        var optHcoEntity = hcoRepository.findById(id);

        if (optHcoEntity.isPresent()) {
            var hcoAffiliations = affiliationService.findByHealthcareOrganization(id);
            var hco = HealthcareOrganizationEntityMapper.toHco(optHcoEntity.get());
            hco.setAffiliations(hcoAffiliations);
            return Optional.of(hco);
        }

        return Optional.empty();
    }

    @Override
    public List<HealthcareOrganization> findByIdNameOrStatus(Integer id,
                                                             String name,
                                                             HealthcareOrganizationStatus status) {

        var hcoEntities = hcoRepository.findAllWith(
                id,
                name,
                HealthcareOrganizationEntityMapper.toHcoEntityStatus(status)
        );
        var hcos = HealthcareOrganizationEntityMapper.toHcos(hcoEntities);
        return hcos;
    }
}
