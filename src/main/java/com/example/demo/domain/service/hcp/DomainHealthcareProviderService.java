package com.example.demo.domain.service.hcp;

import com.example.demo.domain.HealthcareProvider;
import com.example.demo.domain.HealthcareProviderStatus;
import com.example.demo.domain.repository.HealthcareProviderRepository;
import com.example.demo.domain.service.affiliation.AffiliationService;
import com.example.demo.infrastructure.repository.mysql.hcp.HealthcareProviderEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomainHealthcareProviderService implements HealthcareProviderService {

    private final HealthcareProviderRepository hcpRepository;
    private final AffiliationService affiliationService;

    @Autowired
    public DomainHealthcareProviderService(HealthcareProviderRepository hcpRepository, AffiliationService affiliationService) {
        this.hcpRepository = hcpRepository;
        this.affiliationService = affiliationService;
    }

    @Override
    public Optional<HealthcareProvider> findById(Integer id) {
        var optHcpEntity = hcpRepository.findById(id);

        if (optHcpEntity.isPresent()) {
            var hcpAffiliations = affiliationService.findByHealthcareProvider(id);
            var hcp = HealthcareProviderEntityMapper.toHcp(optHcpEntity.get());
            hcp.setAffiliations(hcpAffiliations);
            return Optional.of(hcp);
        }

        return Optional.empty();
    }

    @Override
    public List<HealthcareProvider> findByIdNameOrStatus(Integer id, String name, HealthcareProviderStatus status) {
        var hcpEntities = hcpRepository.findAllWith(
                id,
                name,
                HealthcareProviderEntityMapper.toHcpEntityStatus(status)
        );
        var hcps = HealthcareProviderEntityMapper.toHcps(hcpEntities);
        return hcps;
    }
}
