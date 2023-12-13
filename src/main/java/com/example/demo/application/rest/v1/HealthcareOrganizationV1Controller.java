package com.example.demo.application.rest.v1;

import com.example.demo.application.rest.v1.mapper.AddressV1ResponseMapper;
import com.example.demo.application.rest.v1.mapper.AffiliationV1ResponseMapper;
import com.example.demo.application.rest.v1.mapper.HealthcareOrganizationV1ResponseMapper;
import com.example.demo.application.rest.v1.response.address.AddressV1Response;
import com.example.demo.application.rest.v1.response.address.AddressV1ResponseCollection;
import com.example.demo.application.rest.v1.response.affiliation.AffiliationV1Response;
import com.example.demo.application.rest.v1.response.affiliation.AffiliationV1ResponseCollection;
import com.example.demo.application.rest.v1.response.hco.HealthcareOrganizationBaseV1Response;
import com.example.demo.application.rest.v1.response.hco.HealthcareOrganizationBaseV1ResponseCollection;
import com.example.demo.application.rest.v1.response.hco.HealthcareOrganizationV1Response;
import com.example.demo.application.rest.v1.validation.RequestParamsValidator;
import com.example.demo.domain.HealthcareOrganizationStatus;
import com.example.demo.domain.service.hco.HealthcareOrganizationService;
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
@RequestMapping("api/v1/hcos")
public class HealthcareOrganizationV1Controller {

    private final HealthcareOrganizationService hcoService;

    @Autowired
    public HealthcareOrganizationV1Controller(HealthcareOrganizationService hcoService) {
        this.hcoService = hcoService;
    }

    @Operation(summary = "Get a hco by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hco found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HealthcareOrganizationV1Response.class))}),
            @ApiResponse(responseCode = "404", description = "Hco not found",
                    content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<HealthcareOrganizationV1Response> getById(@PathVariable("id") Integer id) {
        var optHco = hcoService.findById(id);

        if (optHco.isPresent()) {
            var hcoResponse = HealthcareOrganizationV1ResponseMapper.toHcoV1Response(optHco.get());
            return ResponseEntity.ok(hcoResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get hco addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hco addresses",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressV1ResponseCollection.class))}),
            @ApiResponse(responseCode = "404", description = "Hco not found",
                    content = @Content)})
    @RequestMapping(value = "/{id}/addresses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<AddressV1Response>> getAddresses(@PathVariable("id") Integer id) {
        var optHco = hcoService.findById(id);

        if (optHco.isPresent()) {
            var hcoAddresses = optHco.get().getAddresses();
            var addressesResponse = AddressV1ResponseMapper.toAddressV1Responses(hcoAddresses);
            return ResponseEntity.ok(addressesResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get hco affiliations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hco affiliations",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AffiliationV1ResponseCollection.class))}),
            @ApiResponse(responseCode = "404", description = "Hco not found",
                    content = @Content)})
    @RequestMapping(value = "/{id}/affiliations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<AffiliationV1Response>> getAffiliations(@PathVariable("id") Integer id) {
        var optHco = hcoService.findById(id);

        if (optHco.isPresent()) {
            var hcoAffiliations = optHco.get().getAffiliations();
            var affiliationResponses = AffiliationV1ResponseMapper.toAffiliationV1Responses(hcoAffiliations);
            return ResponseEntity.ok(affiliationResponses);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get hcos by criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hcos found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HealthcareOrganizationBaseV1ResponseCollection.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<HealthcareOrganizationBaseV1Response>> getByCriteria(@RequestParam(required = false) Integer id,
                                                                                   @RequestParam(required = false) String name,
                                                                                   @RequestParam(required = false) String status) {

        if (!RequestParamsValidator.isValidStatus(status)) {
            return ResponseEntity.badRequest().build();
        }

        var hcos = hcoService.findByIdNameOrStatus(id, name, HealthcareOrganizationStatus.of(status));
        var hcosResponse = HealthcareOrganizationV1ResponseMapper.toHcoBaseV1Responses(hcos);
        return ResponseEntity.ok(hcosResponse);
    }
}
