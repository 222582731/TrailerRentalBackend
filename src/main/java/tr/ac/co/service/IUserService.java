package tr.ac.co.service;

import tr.ac.co.domain.User;
import tr.ac.co.domain.enums.Role;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IService<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    Optional<User> findByFirstName(String firstName);
}
