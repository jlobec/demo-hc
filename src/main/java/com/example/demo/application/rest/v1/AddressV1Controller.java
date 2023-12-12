package com.example.demo.application.rest.v1;

import com.example.demo.application.rest.v1.mapper.AddressV1ResponseMapper;
import com.example.demo.application.rest.v1.validation.RequestParamsValidator;
import com.example.demo.domain.AddressStatus;
import com.example.demo.domain.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/addresses")
public class AddressV1Controller {

    private final AddressService addressService;

    @Autowired
    public AddressV1Controller(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        var optAddress = addressService.findById(id);

        if (optAddress.isPresent()) {
            var addressResponse = AddressV1ResponseMapper.toAddressV1Response(optAddress.get());
            return ResponseEntity.ok(addressResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getByCriteria(@RequestParam(required = false) Integer id,
                                    @RequestParam(required = false) String addr1,
                                    @RequestParam(required = false) String addr2,
                                    @RequestParam(required = false) String city,
                                    @RequestParam(required = false) String state,
                                    @RequestParam(required = false) String zip,
                                    @RequestParam(required = false) String status) {

        if (!RequestParamsValidator.isValidStatus(status)) {
            return ResponseEntity.badRequest().build();
        }

        var addresses = addressService.findByCriteria(id, addr1, addr2, city,
                state, zip, AddressStatus.of(status));
        var addressesResponse = AddressV1ResponseMapper.toAddressV1Responses(addresses);
        return ResponseEntity.ok(addressesResponse);
    }
}
