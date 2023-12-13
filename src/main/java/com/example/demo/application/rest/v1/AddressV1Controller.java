package com.example.demo.application.rest.v1;

import com.example.demo.application.rest.v1.mapper.AddressV1ResponseMapper;
import com.example.demo.application.rest.v1.response.address.AddressV1Response;
import com.example.demo.application.rest.v1.response.address.AddressV1ResponseCollection;
import com.example.demo.application.rest.v1.validation.RequestParamsValidator;
import com.example.demo.domain.AddressStatus;
import com.example.demo.domain.service.address.AddressService;
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
@RequestMapping("api/v1/addresses")
public class AddressV1Controller {

    private final AddressService addressService;

    @Autowired
    public AddressV1Controller(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Get an address by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressV1Response.class))}),
            @ApiResponse(responseCode = "404", description = "Address not found",
                    content = @Content)})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddressV1Response> getById(@PathVariable("id") Integer id) {
        var optAddress = addressService.findById(id);

        if (optAddress.isPresent()) {
            var addressResponse = AddressV1ResponseMapper.toAddressV1Response(optAddress.get());
            return ResponseEntity.ok(addressResponse);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get addresses by criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Addresses found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AddressV1ResponseCollection.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<AddressV1Response>> getByCriteria(@RequestParam(required = false) Integer id,
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
