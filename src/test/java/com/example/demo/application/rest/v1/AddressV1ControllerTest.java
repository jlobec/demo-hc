package com.example.demo.application.rest.v1;

import com.example.demo.domain.Address;
import com.example.demo.domain.AddressStatus;
import com.example.demo.domain.service.address.AddressService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressV1Controller.class)
public class AddressV1ControllerTest {

    private static final Integer A_NON_EXISTENT_ID = 99;
    private static final Integer A_EXISTENT_ID = 2;
    private static final String A_ADDR_1 = "addr1";
    private static final String A_ADDR_2 = "addr2";
    private static final String A_CITY = "city1";
    private static final String A_STATE = "state1";
    private static final String A_ZIP = "10111";
    private static final String ACTIVE_STATUS = "active";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @InjectMocks
    private AddressV1Controller addressV1Controller;

    @Test
    public void getById_addressNotPresent_shouldReturnNotFound() throws Exception {
        when(addressService.findById(A_NON_EXISTENT_ID)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/addresses/{id}", A_NON_EXISTENT_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getById_addressPresent_shouldReturnOk() throws Exception {
        var result = new Address(A_EXISTENT_ID, A_ADDR_1, A_ADDR_2, A_CITY, A_STATE, A_ZIP, AddressStatus.ACTIVE);
        when(addressService.findById(A_EXISTENT_ID)).thenReturn(Optional.of(result));

        mockMvc.perform(get("/api/v1/addresses/{id}", A_EXISTENT_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(A_EXISTENT_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addr1").value(A_ADDR_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addr2").value(A_ADDR_2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(A_CITY))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value(A_STATE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.zip").value(A_ZIP))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(ACTIVE_STATUS));
    }
}
