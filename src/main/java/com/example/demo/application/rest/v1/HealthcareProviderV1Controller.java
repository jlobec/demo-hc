package com.example.demo.application.rest.v1;

import com.example.demo.application.rest.v1.mapper.AddressV1ResponseMapper;
import com.example.demo.application.rest.v1.mapper.AffiliationV1ResponseMapper;
import com.example.demo.application.rest.v1.mapper.HealthcareProviderV1ResponseMapper;
import com.example.demo.application.rest.v1.validation.RequestParamsValidator;
import com.example.demo.domain.HealthcareProviderStatus;
import com.example.demo.domain.service.hcp.HealthcareProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hcps")
public class HealthcareProviderV1Controller {

    private final HealthcareProviderService hcpService;

    @Autowired
    public HealthcareProviderV1Controller(HealthcareProviderService hcpService) {
        this.hcpService = hcpService;
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        var optHcp = hcpService.findById(id);

        if (optHcp.isPresent()) {
            var hcpResponse = HealthcareProviderV1ResponseMapper.toHcpV1Response(optHcp.get());
            return ResponseEntity.ok(hcpResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAddresses(@PathVariable("id") Integer id) {
        var optHcp = hcpService.findById(id);

        if (optHcp.isPresent()) {
            var hcpAddresses = optHcp.get().getAddresses();
            var addressesResponse = AddressV1ResponseMapper.toAddressV1Responses(hcpAddresses);
            return ResponseEntity.ok(addressesResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}/affiliations", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAffiliations(@PathVariable("id") Integer id) {
        var optHcp = hcpService.findById(id);

        if (optHcp.isPresent()) {
            var hcpAffiliations = optHcp.get().getAffiliations();
            var affiliationResponses = AffiliationV1ResponseMapper.toAffiliationV1Responses(hcpAffiliations);
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

        var hcps = hcpService.findByIdNameOrStatus(id, name, HealthcareProviderStatus.of(status));
        var hcpsResponse = HealthcareProviderV1ResponseMapper.toHcpV1Responses(hcps);
        return ResponseEntity.ok(hcpsResponse);
    }
}
