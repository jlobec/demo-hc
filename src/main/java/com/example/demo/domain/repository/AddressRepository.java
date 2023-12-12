package com.example.demo.domain.repository;

import com.example.demo.infrastructure.repository.mysql.address.AddressEntity;
import com.example.demo.infrastructure.repository.mysql.address.AddressEntityStatus;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {

    Optional<AddressEntity> findById(Integer id);

    List<AddressEntity> findAllWith(Integer id, String addr1, String addr2,
                                    String city, String state, String zip,
                                    AddressEntityStatus status);
}
