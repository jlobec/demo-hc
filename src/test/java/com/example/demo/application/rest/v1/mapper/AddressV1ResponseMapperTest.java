package com.example.demo.application.rest.v1.mapper;

import com.example.demo.application.rest.v1.response.address.AddressV1Response;
import com.example.demo.domain.Address;
import com.example.demo.domain.AddressStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AddressV1ResponseMapperTest {

    private static final Integer A_ID = 1;
    private static final String A_ADDR_1 = "addr1";
    private static final String A_ADDR_2 = "addr2";
    private static final String A_CITY = "city1";
    private static final String A_STATE = "state1";
    private static final String A_ZIP = "10111";
    private static final String ACTIVE_STATUS = "active";

    @Test
    public void toAddressV1Response_nullAddress_shouldReturnNull() {

        var result = AddressV1ResponseMapper.toAddressV1Response(null);

        assertNull(result);
    }

    @Test
    public void toAddressV1Response_addressInformed_shouldReturnMappedResult() {
        var address = new Address(A_ID, A_ADDR_1, A_ADDR_2, A_CITY, A_STATE, A_ZIP, AddressStatus.ACTIVE);
        var expected = new AddressV1Response(A_ID, A_ADDR_1, A_ADDR_2, A_CITY, A_STATE, A_ZIP, ACTIVE_STATUS);

        var actual = AddressV1ResponseMapper.toAddressV1Response(address);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
