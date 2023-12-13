package com.example.demo.application.rest.v1;

import com.example.demo.application.rest.v1.mapper.AddressV1ResponseMapper;
import com.example.demo.application.rest.v1.mapper.AffiliationV1ResponseMapper;
import com.example.demo.application.rest.v1.mapper.HealthcareProviderV1ResponseMapper;
import com.example.demo.application.rest.v1.response.address.AddressV1Response;
import com.example.demo.application.rest.v1.response.address.AddressV1ResponseCollection;
import com.example.demo.application.rest.v1.response.affiliation.AffiliationV1Response;
import com.example.demo.application.rest.v1.response.affiliation.AffiliationV1ResponseCollection;
import com.example.demo.application.rest.v1.response.hcp.HealthcareProviderBaseV1ResponseCollection;
import com.example.demo.application.rest.v1.response.hcp.HealthcareProviderV1Response;
import com.example.demo.application.rest.v1.validation.RequestParamsValidator;
import com.example.demo.domain.HealthcareProviderStatus;
import com.example.demo.domain.service.hcp.HealthcareProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/hcps")
public class HealthcareProviderV1Controller {

    private final HealthcareProviderService hcpService;

    @Autowired
    public HealthcareProviderV1Controller(HealthcareProviderService hcpService) {
        this.hcpService = hcpService;
    }

    @Operation(summary = "Get a hcp by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hcp found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HealthcareProviderV1Response.class))}),
            @ApiResponse(responseCode = "404", description = "Hcp not found",
                    content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HealthcareProviderV1Response> getById(@PathVariable("id") Integer id) {
        var optHcp = hcpService.findById(id);

        if (optHcp.isPresent()) {
            var hcpResponse = HealthcareProviderV1ResponseMapper.toHcpV1Response(optHcp.get());
            return ResponseEntity.ok(hcpResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get hcp addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hcp addresses",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressV1ResponseCollection.class))}),
            @ApiResponse(responseCode = "404", description = "Hcp not found",
                    content = @Content)})
    @RequestMapping(value = "/{id}/addresses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<AddressV1Response>> getAddresses(@PathVariable("id") Integer id) {
        var optHcp = hcpService.findById(id);

        if (optHcp.isPresent()) {
            var hcpAddresses = optHcp.get().getAddresses();
            var addressesResponse = AddressV1ResponseMapper.toAddressV1Responses(hcpAddresses);
            return ResponseEntity.ok(addressesResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get hcp affiliations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hcp affiliations",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AffiliationV1ResponseCollection.class))}),
            @ApiResponse(responseCode = "404", description = "Hcp not found",
                    content = @Content)})
    @RequestMapping(value = "/{id}/affiliations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<AffiliationV1Response>> getAffiliations(@PathVariable("id") Integer id) {
        var optHcp = hcpService.findById(id);

        if (optHcp.isPresent()) {
            var hcpAffiliations = optHcp.get().getAffiliations();
            var affiliationResponses = AffiliationV1ResponseMapper.toAffiliationV1Responses(hcpAffiliations);
            return ResponseEntity.ok(affiliationResponses);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get hcps by criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hcps found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HealthcareProviderBaseV1ResponseCollection.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<HealthcareProviderV1Response>> getByCriteria(@RequestParam(required = false) Integer id,
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
