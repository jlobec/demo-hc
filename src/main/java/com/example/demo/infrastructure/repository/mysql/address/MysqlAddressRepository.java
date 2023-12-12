package com.example.demo.infrastructure.repository.mysql.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlAddressRepository extends
        JpaRepository<AddressEntity, Integer>,
        JpaSpecificationExecutor<AddressEntity> {
}
