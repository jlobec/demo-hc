package com.example.demo.application.rest.v1;

import com.example.demo.application.rest.v1.mapper.AffiliationV1ResponseMapper;
import com.example.demo.application.rest.v1.response.affiliation.AffiliationV1Response;
import com.example.demo.application.rest.v1.response.affiliation.AffiliationV1ResponseCollection;
import com.example.demo.application.rest.v1.validation.RequestParamsValidator;
import com.example.demo.domain.AffiliationStatus;
import com.example.demo.domain.service.affiliation.AffiliationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/affiliations")
public class AffiliationV1Controller {

    private final AffiliationService affiliationService;

    public AffiliationV1Controller(AffiliationService affiliationService) {
        this.affiliationService = affiliationService;
    }

    @Operation(summary = "Get an affiliation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Affiliation found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AffiliationV1Response.class))}),
            @ApiResponse(responseCode = "404", description = "Affiliation not found",
                    content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AffiliationV1Response> getById(@PathVariable("id") Integer id) {
        var affiliation = affiliationService.findById(id);

        if (affiliation.isPresent()) {
            var affiliationResponse = AffiliationV1ResponseMapper.toAffiliationV1Response(affiliation.get());
            return ResponseEntity.ok(affiliationResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get affiliations by criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Affiliations found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AffiliationV1ResponseCollection.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<AffiliationV1Response>> getByCriteria(@RequestParam(required = false) Integer id,
                                                                    @RequestParam(required = false) String type,
                                                                    @RequestParam(required = false) String status) {
        if (!RequestParamsValidator.isValidStatus(status)) {
            return ResponseEntity.badRequest().build();
        }

        var affiliations = affiliationService.findByCriteria(id, type, AffiliationStatus.of(status));
        var affiliationsResponse = AffiliationV1ResponseMapper.toAffiliationV1Responses(affiliations);
        return ResponseEntity.ok(affiliationsResponse);
    }

}
