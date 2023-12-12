package com.example.demo.application.rest.v1;

import com.example.demo.application.rest.v1.mapper.AddressV1ResponseMapper;
import com.example.demo.application.rest.v1.mapper.AffiliationV1ResponseMapper;
import com.example.demo.application.rest.v1.mapper.HealthcareOrganizationV1ResponseMapper;
import com.example.demo.application.rest.v1.validation.RequestParamsValidator;
import com.example.demo.domain.HealthcareOrganizationStatus;
import com.example.demo.domain.service.hco.HealthcareOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hcos")
public class HealthcareOrganizationV1Controller {

    private final HealthcareOrganizationService hcoService;

    @Autowired
    public HealthcareOrganizationV1Controller(HealthcareOrganizationService hcoService) {
        this.hcoService = hcoService;
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        var optHco = hcoService.findById(id);

        if (optHco.isPresent()) {
            var hcoResponse = HealthcareOrganizationV1ResponseMapper.toHcoV1Response(optHco.get());
            return ResponseEntity.ok(hcoResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAddresses(@PathVariable("id") Integer id) {
        var optHco = hcoService.findById(id);

        if (optHco.isPresent()) {
            var hcoAddresses = optHco.get().getAddresses();
            var addressesResponse = AddressV1ResponseMapper.toAddressV1Responses(hcoAddresses);
            return ResponseEntity.ok(addressesResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}/affiliations", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAffiliations(@PathVariable("id") Integer id) {
        var optHco = hcoService.findById(id);

        if (optHco.isPresent()) {
            var hcoAffiliations = optHco.get().getAffiliations();
            var affiliationResponses = AffiliationV1ResponseMapper.toAffiliationV1Responses(hcoAffiliations);
            return ResponseEntity.ok(affiliationResponses);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getByCriteria(@RequestParam(required = false) Integer id,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) String status) {

        if (!RequestParamsValidator.isValidStatus(status)) {
            return ResponseEntity.badRequest().build();
        }

        var hcos = hcoService.findByIdNameOrStatus(id, name, HealthcareOrganizationStatus.of(status));
        var hcosResponse = HealthcareOrganizationV1ResponseMapper.toHcoV1Responses(hcos);
        return ResponseEntity.ok(hcosResponse);
    }
}
