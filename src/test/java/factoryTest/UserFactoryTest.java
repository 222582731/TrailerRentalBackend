package factoryTest;

import org.junit.jupiter.api.Test;
import tr.ac.co.domain.User;
import tr.ac.co.domain.enums.Role;
import tr.ac.co.factory.UserFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class UserFactoryTest {

    @Test
    void testCreateValidUser() {
        User user = UserFactory.createUser("Lihle", "Dambuza",
                "l.dambu@example.com", "Password123", Role.CUSTOMER, LocalDateTime.now());
        assertNotNull(user);
        assertEquals("Lihle", user.getFirstName());
        assertEquals(Role.CUSTOMER, user.getRole());
        System.out.println(user.toString());
    }

    @Test
    void testCreateAdmin() {
        User admin = UserFactory.createAdmin("Admin1", "User1",
                "admi1n@example.com", "Admin1Pass1234");
        assertNotNull(admin);
        assertEquals(Role.ADMIN, admin.getRole());
        System.out.println(admin.toString());
    }

    @Test
    void testCreateUserInvalidEmail() {
        User user = UserFactory.createUser("Joseph", "Van Da Linda",
                "invalid-email", "Password123!", Role.CUSTOMER, LocalDateTime.now());
        assertNull(user);
        System.out.println(user.toString());
    }

    @Test
    void testCreateUserMissingFields() {
        User user = UserFactory.createUser("", "Doe", "jane@example.com",
                "Password123!", Role.CUSTOMER, LocalDateTime.now());
        assertNull(user);
        System.out.println(user.toString());
    }
}
