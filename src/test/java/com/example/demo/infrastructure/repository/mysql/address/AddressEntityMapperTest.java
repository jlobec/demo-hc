package com.example.demo.infrastructure.repository.mysql.address;

import com.example.demo.domain.Address;
import com.example.demo.domain.AddressStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AddressEntityMapperTest {

    private static final Integer A_EXISTENT_ID = 11;
    private static final Integer A_PARENT_LINK = 22;
    private static final String A_PARENT_TYPE = "HCO";
    private static final String A_ADDR_1 = "addr1";
    private static final String A_ADDR_2 = "addr2";
    private static final String A_CITY = "city1";
    private static final String A_STATE = "state1";
    private static final String A_ZIP = "10111";
    private static final String ACTIVE_STATUS = "A";

    @Test
    public void toAddress_nullEntity_shouldReturnNull() {
        var actual = AddressEntityMapper.toAddress(null);

        assertNull(actual);
    }

    @Test
    public void toAddress_addressEntity_shouldReturnAddressWithExpectedData() {
        var addressEntity = new AddressEntity(A_EXISTENT_ID, A_PARENT_LINK, A_PARENT_TYPE,
                A_ADDR_1, A_ADDR_2, A_CITY, A_STATE, A_ZIP, ACTIVE_STATUS);
        var actual = AddressEntityMapper.toAddress(addressEntity);

        var expected = new Address(A_EXISTENT_ID, A_ADDR_1, A_ADDR_2, A_CITY, A_STATE, A_ZIP, AddressStatus.ACTIVE);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
