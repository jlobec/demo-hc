package com.example.demo.domain.service.affiliation;

import com.example.demo.domain.Affiliation;
import com.example.demo.domain.AffiliationStatus;
import com.example.demo.domain.repository.AffiliationRepository;
import com.example.demo.domain.repository.HealthcareOrganizationRepository;
import com.example.demo.domain.repository.HealthcareProviderRepository;
import com.example.demo.infrastructure.repository.mysql.affiliation.AffiliationEntity;
import com.example.demo.infrastructure.repository.mysql.affiliation.AffiliationEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DomainAffiliationService implements AffiliationService {

    private final AffiliationRepository affiliationRepository;
    private final HealthcareOrganizationRepository hcoRepository;
    private final HealthcareProviderRepository hcpRepository;

    @Autowired
    public DomainAffiliationService(AffiliationRepository affiliationRepository,
                                    HealthcareOrganizationRepository hcoRepository,
                                    HealthcareProviderRepository hcpRepository) {
        this.affiliationRepository = affiliationRepository;
        this.hcoRepository = hcoRepository;
        this.hcpRepository = hcpRepository;
    }

    @Override
    public Optional<Affiliation> findById(Integer id) {
        var affiliationEntityOptional = affiliationRepository.findById(id);

        if (affiliationEntityOptional.isPresent()) {
            var affiliationEntity = affiliationEntityOptional.get();
            var hcoIds = AffiliationUtils.getHcoIds(affiliationEntity);
            var hcpIds = AffiliationUtils.getHcpIds(affiliationEntity);

            var hcoEntities = hcoRepository.findAllById(hcoIds);
            var hcpEntities = hcpRepository.findAllById(hcpIds);

            var affiliation = AffiliationEntityMapper.toAffiliation(affiliationEntity, hcoEntities, hcpEntities);
            return Optional.of(affiliation);
        }

        return Optional.empty();
    }

    @Override
    public List<Affiliation> findByHealthcareOrganization(Integer hcoId) {
        var hcoEntityOpt = hcoRepository.findById(hcoId);

        if (hcoEntityOpt.isEmpty()) {
            return Collections.emptyList();
        }

        var childAffiliationIds = hcoEntityOpt.get().getAffiliationsAsChild().stream().map(AffiliationEntity::getId).collect(Collectors.toList());
        var parentAffiliationIds = hcoEntityOpt.get().getAffiliationsAsParent().stream().map(AffiliationEntity::getId).collect(Collectors.toList());
        var affiliationIds = new ArrayList<>(parentAffiliationIds);
        affiliationIds.addAll(childAffiliationIds);

        var affiliationEntities = affiliationRepository.findAllById(affiliationIds);

        var hcoIds = AffiliationUtils.getHcoIds(affiliationEntities);
        var hcpIds = AffiliationUtils.getHcpIds(affiliationEntities);

        var hcoEntities = hcoRepository.findAllById(hcoIds);
        var hcpEntities = hcpRepository.findAllById(hcpIds);

        var affilliations = AffiliationEntityMapper.toAffiliations(
                affiliationEntities, hcoEntities, hcpEntities);

        return affilliations;
    }

    @Override
    public List<Affiliation> findByHealthcareProvider(Integer hcpId) {
        var hcpEntityOpt = hcpRepository.findById(hcpId);

        if (hcpEntityOpt.isEmpty()) {
            return Collections.emptyList();
        }

        var childAffiliationIds = hcpEntityOpt.get().getAffiliationsAsChild().stream().map(AffiliationEntity::getId).collect(Collectors.toList());
        var parentAffiliationIds = hcpEntityOpt.get().getAffiliationsAsParent().stream().map(AffiliationEntity::getId).collect(Collectors.toList());
        var affiliationIds = new ArrayList<>(parentAffiliationIds);
        affiliationIds.addAll(childAffiliationIds);

        var affiliationEntities = affiliationRepository.findAllById(affiliationIds);

        var hcoIds = AffiliationUtils.getHcoIds(affiliationEntities);
        var hcpIds = AffiliationUtils.getHcpIds(affiliationEntities);

        var hcoEntities = hcoRepository.findAllById(hcoIds);
        var hcpEntities = hcpRepository.findAllById(hcpIds);

        var affilliations = AffiliationEntityMapper.toAffiliations(
                affiliationEntities, hcoEntities, hcpEntities);

        return affilliations;
    }

    @Override
    public List<Affiliation> findByCriteria(Integer id, String type, AffiliationStatus status) {
        var affiliationEntities = affiliationRepository.findAllWith(id, type,
                AffiliationEntityMapper.toAffiliationEntityStatus(status));

        var hcoIds = AffiliationUtils.getHcoIds(affiliationEntities);
        var hcpIds = AffiliationUtils.getHcpIds(affiliationEntities);

        var hcoEntities = hcoRepository.findAllById(hcoIds);
        var hcpEntities = hcpRepository.findAllById(hcpIds);

        var affilliations = AffiliationEntityMapper.toAffiliations(
                affiliationEntities, hcoEntities, hcpEntities);

        return affilliations;
    }
}
