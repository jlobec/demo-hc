package com.example.demo.infrastructure.repository.mysql.hco;

import com.example.demo.domain.repository.HealthcareOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HealthcareOrganizationRepositoryImpl implements HealthcareOrganizationRepository {

    private final MysqlHealthcareOrganizationRepository hcoRepository;

    @Autowired
    public HealthcareOrganizationRepositoryImpl(MysqlHealthcareOrganizationRepository hcoRepository) {
        this.hcoRepository = hcoRepository;
    }

    @Override
    public Optional<HealthcareOrganizationEntity> findById(Integer id) {
        return hcoRepository.findById(id);
    }

    @Override
    public List<HealthcareOrganizationEntity> findAllById(List<Integer> ids) {
        return hcoRepository.findAllById(ids);
    }

    @Override
    public List<HealthcareOrganizationEntity> findAllWith(Integer id, String name, HealthcareOrganizationEntityStatus status) {
        return hcoRepository.findAll(HcoQuerySpecification.withCriteria(id, name, status));
    }
}
