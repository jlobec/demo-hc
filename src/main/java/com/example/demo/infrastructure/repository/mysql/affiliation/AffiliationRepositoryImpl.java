package com.example.demo.infrastructure.repository.mysql.affiliation;

import com.example.demo.domain.repository.AffiliationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AffiliationRepositoryImpl implements AffiliationRepository {

    private final MysqlAffiliationRepository affiliationRepository;

    @Autowired
    public AffiliationRepositoryImpl(MysqlAffiliationRepository affiliationRepository) {
        this.affiliationRepository = affiliationRepository;
    }

    @Override
    public Optional<AffiliationEntity> findById(Integer id) {
        return affiliationRepository.findById(id);
    }

    @Override
    public List<AffiliationEntity> findAllById(List<Integer> ids) {
        return affiliationRepository.findAllById(ids);
    }

    @Override
    public List<AffiliationEntity> findAllWith(Integer id, String type, AffiliationEntityStatus status) {
        return affiliationRepository.findAll(AffiliationQuerySpecification.withCriteria(id, type, status));
    }
}
