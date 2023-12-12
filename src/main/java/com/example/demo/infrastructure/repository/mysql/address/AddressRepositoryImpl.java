package com.example.demo.infrastructure.repository.mysql.address;

import com.example.demo.domain.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddressRepositoryImpl implements AddressRepository {

    private final MysqlAddressRepository addressRepository;

    @Autowired
    public AddressRepositoryImpl(MysqlAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<AddressEntity> findById(Integer id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<AddressEntity> findAllWith(Integer id, String addr1, String addr2, String city,
                                           String state, String zip, AddressEntityStatus status) {
        return addressRepository.findAll(
                AddressQuerySpecification.withCriteria(id, addr1, addr2, city, state, zip, status));
    }

}
