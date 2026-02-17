package tr.ac.co.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import tr.ac.co.domain.User;
import tr.ac.co.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldRegisterUserSuccessfully() {

        User user = userService.register(
                "Yanga",
                "Jilaji",
                "yanga@test.com",
                "Password@123"
        );

        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals("yanga@test.com", user.getEmail());

        assertTrue(passwordEncoder.matches("Password@123", user.getPassword()));
        System.out.println(user.getEmail());
    }

    @Test
    void shouldNotAllowDuplicateEmail() {

        userService.register(
                "Yanga",
                "Jilaji",
                "duplicate@test.com",
                "Password@123"
        );

        assertThrows(IllegalArgumentException.class, () ->
                userService.register(
                        "Another",
                        "User",
                        "duplicate@test.com",
                        "Password@123"
                )
        );
    }

    @Test
    void shouldLoginSuccessfully() {

        userService.register(
                "Yanga",
                "Jilaji",
                "login@test.com",
                "Password@123"
        );

        Optional<User> user =
                userService.login("login@test.com", "Password@123");

        assertTrue(user.isPresent());
        System.out.println(user.toString());
    }

    @Test
    void shouldFailLoginWithWrongPassword() {

        userService.register(
                "Yanga",
                "Jilaji",
                "wrongpass@test.com",
                "Password@123"
        );

        Optional<User> user =
                userService.login("wrongpass@test.com", "WrongPass");

        assertTrue(user.isEmpty());
        System.out.println(user.toString());
    }

    @Test
    void shouldFindUserByEmail() {

        userService.register(
                "Yanga",
                "Jilaji",
                "find@test.com",
                "Password@123"
        );

        Optional<User> found =
                userService.findByEmail("find@test.com");

        assertTrue(found.isPresent());
        assertEquals("find@test.com", found.get().getEmail());
        System.out.println();
    }
}
