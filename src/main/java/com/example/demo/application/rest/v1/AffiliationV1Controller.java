package com.example.demo.application.rest.v1;

import com.example.demo.application.rest.v1.mapper.AffiliationV1ResponseMapper;
import com.example.demo.application.rest.v1.validation.RequestParamsValidator;
import com.example.demo.domain.AffiliationStatus;
import com.example.demo.domain.service.affiliation.AffiliationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/affiliations")
public class AffiliationV1Controller {

    private final AffiliationService affiliationService;

    public AffiliationV1Controller(AffiliationService affiliationService) {
        this.affiliationService = affiliationService;
    }

    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        var affiliation = affiliationService.findById(id);

        if (affiliation.isPresent()) {
            var affiliationResponse = AffiliationV1ResponseMapper.toAffiliationV1Response(affiliation.get());
            return ResponseEntity.ok(affiliationResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getByCriteria(@RequestParam(required = false) Integer id,
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
