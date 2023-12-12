package com.example.demo.domain.service.address;

import com.example.demo.domain.repository.AddressRepository;
import com.example.demo.infrastructure.repository.mysql.address.AddressEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DomainAddressServiceTest {

    private static final Integer A_NON_EXISTENT_ID = 1;
    private static final Integer A_EXISTENT_ID = 11;
    private static final Integer A_PARENT_LINK = 22;
    private static final String A_PARENT_TYPE = "HCO";
    private static final String A_ADDR_1 = "addr1";
    private static final String A_ADDR_2 = "addr2";
    private static final String A_CITY = "city1";
    private static final String A_STATE = "state1";
    private static final String A_ZIP = "10111";
    private static final String ACTIVE_STATUS = "A";

    @Mock
    private AddressRepository addressRepositoryMock;

    @Test
    public void findById_addressNotPresent_shouldReturnOptionalEmpty() {
        when(addressRepositoryMock.findById(A_NON_EXISTENT_ID)).thenReturn(Optional.empty());

        var service = new DomainAddressService(addressRepositoryMock);

        var actual = service.findById(A_NON_EXISTENT_ID);

        assertTrue(actual.isEmpty());
    }

    @Test
    public void findById_addressPresent_shouldReturnOptionalWithTheExpectedData() {
        var addressEntity = new AddressEntity(A_EXISTENT_ID, A_PARENT_LINK, A_PARENT_TYPE,
                A_ADDR_1, A_ADDR_2, A_CITY, A_STATE, A_ZIP, ACTIVE_STATUS);

        when(addressRepositoryMock.findById(A_EXISTENT_ID)).thenReturn(Optional.of(addressEntity));

        var service = new DomainAddressService(addressRepositoryMock);

        var actual = service.findById(A_EXISTENT_ID);

        assertTrue(actual.isPresent());
    }
}
