package com.example.demo.domain.repository;

import com.example.demo.infrastructure.repository.mysql.affiliation.AffiliationEntity;
import com.example.demo.infrastructure.repository.mysql.affiliation.AffiliationEntityStatus;

import java.util.List;
import java.util.Optional;

public interface AffiliationRepository {

    Optional<AffiliationEntity> findById(Integer id);

    List<AffiliationEntity> findAllById(List<Integer> ids);

    List<AffiliationEntity> findAllWith(Integer id, String type, AffiliationEntityStatus status);
}
