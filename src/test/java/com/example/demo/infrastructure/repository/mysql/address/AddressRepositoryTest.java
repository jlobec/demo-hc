package com.example.demo.infrastructure.repository.mysql.address;

import com.example.demo.domain.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(AddressRepositoryImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepositoryTest {

    private static final Integer A_EXISTENT_ID = 1;
    private static final Integer A_PARENT_LINK = 1;
    private static final String A_PARENT_TYPE = "HCP";
    private static final String A_ADDR_1 = "123 Main Street";
    private static final String A_ADDR_2 = "Suite 101";
    private static final String A_CITY = "New York City";
    private static final String A_STATE = "NY";
    private static final String A_ZIP = "10001";
    private static final String ACTIVE_STATUS = "A";

    @Autowired
    private TestEntityManager testEM;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void findById_existentAddress_returnsExpectedInfo() {
        var expected = new AddressEntity(A_EXISTENT_ID, A_PARENT_LINK, A_PARENT_TYPE,
                A_ADDR_1, A_ADDR_2, A_CITY, A_STATE, A_ZIP, ACTIVE_STATUS);

        var actualOpt = addressRepository.findById(A_EXISTENT_ID);

        assertTrue(actualOpt.isPresent());
        assertEquals(expected, actualOpt.get());
    }
}
