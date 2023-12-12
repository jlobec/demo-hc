package com.example.demo.infrastructure.repository.mysql.hcp;

import com.example.demo.domain.repository.HealthcareProviderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HealthcareProviderRepositoryImpl implements HealthcareProviderRepository {

    private final MysqlHealthcareProviderRepository hcpRepository;

    public HealthcareProviderRepositoryImpl(MysqlHealthcareProviderRepository hcpRepository) {
        this.hcpRepository = hcpRepository;
    }

    @Override
    public Optional<HealthcareProviderEntity> findById(Integer id) {
        return hcpRepository.findById(id);
    }

    @Override
    public List<HealthcareProviderEntity> findAllById(List<Integer> ids) {
        return hcpRepository.findAllById(ids);
    }

    @Override
    public List<HealthcareProviderEntity> findAllWith(Integer id, String name, HealthcareProviderEntityStatus status) {
        return hcpRepository.findAll(HcpQuerySpecification.withCriteria(id, name, status));
    }
}
