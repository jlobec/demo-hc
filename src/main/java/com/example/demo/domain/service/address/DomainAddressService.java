package com.example.demo.domain.service.address;

import com.example.demo.domain.Address;
import com.example.demo.domain.AddressStatus;
import com.example.demo.domain.repository.AddressRepository;
import com.example.demo.infrastructure.repository.mysql.address.AddressEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomainAddressService implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public DomainAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<Address> findById(Integer id) {
        var optAddressEntity = addressRepository.findById(id);

        if (optAddressEntity.isPresent()) {
            var addressEntity = optAddressEntity.get();
            var address = AddressEntityMapper.toAddress(addressEntity);
            return Optional.of(address);
        }

        return Optional.empty();
    }

    @Override
    public List<Address> findByCriteria(Integer id, String addr1, String addr2, String city,
                                        String state, String zip, AddressStatus status) {
        var addressEntities = addressRepository.findAllWith(id, addr1, addr2, city,
                state, zip, AddressEntityMapper.toAddressEntityStatus(status));
        var addresses = AddressEntityMapper.toAddresses(addressEntities);
        return addresses;
    }
}
