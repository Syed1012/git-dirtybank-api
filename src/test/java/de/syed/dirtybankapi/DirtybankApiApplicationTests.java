package de.syed.dirtybankapi;

import de.syed.dirtybankapi.repository.AccountRepository;
import de.syed.dirtybankapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.junit.jupiter.api.extension.ExtendWith;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Import({UserRepository.class, AccountRepository.class}) // import any beans you need
class DirtybankApiApplicationTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void contextLoads() {
        // Ensures context starts without real DB
    }
}
