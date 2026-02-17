package tr.ac.co.factory;

import tr.ac.co.domain.User;
import tr.ac.co.domain.enums.Role;
import tr.ac.co.util.Helper;


public class UserFactory {

    public static User createUser(String firstName,
                                  String lastName,
                                  String email,
                                  String password,
                                  Role role){

        if (Helper.isNullOrEmpty(firstName)
                || Helper.isNullOrEmpty(lastName)
                || Helper.isNullOrEmpty(password)
                || !Helper.isValidEmail(email)
                || role == null) {

            return null;
        }
        return new User.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setRole(role)
                .build();
    }

    public static User createAdmin(String firstName,
                                   String lastName,
                                   String email,
                                   String password) {
        return createUser(firstName, lastName, email, password, Role.ADMIN);
    }
}
