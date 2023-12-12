package com.example.demo.domain.service.address;

import com.example.demo.domain.Address;
import com.example.demo.domain.AddressStatus;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Optional<Address> findById(Integer id);

    List<Address> findByCriteria(Integer id, String addr1, String addr2,
                                 String city, String state, String zip,
                                 AddressStatus status);
}
