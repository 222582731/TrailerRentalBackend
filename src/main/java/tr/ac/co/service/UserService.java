package tr.ac.co.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.ac.co.domain.User;
import tr.ac.co.domain.enums.Role;
import tr.ac.co.factory.UserFactory;
import tr.ac.co.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public Optional<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }


    @Override
    public User save(User entity) {

        if (entity == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (userRepository.findByEmail(entity.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(LocalDateTime.now());
        }

        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) {

        if (entity.getId() == null || !userRepository.existsById(entity.getId())) {
            throw new IllegalArgumentException("User does not exist");
        }

        //avoid double encoding password
        if (entity.getPassword() != null &&
                !entity.getPassword().startsWith("$2a$")) {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        return userRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {

        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }

        userRepository.deleteById(id);
    }

    @Override
    public User read(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }



    //Registering user
    public User register(String firstName,
                         String lastName,
                         String email,
                         String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = UserFactory.createUser(
                firstName,
                lastName,
                email,
                passwordEncoder.encode(password),
                Role.CUSTOMER,
                LocalDateTime.now());

        if (user == null) {
            throw new IllegalArgumentException("Invalid user data");
        }

        return userRepository.save(user);
    }

   //User Login
    public Optional<User> login(String email, String password) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();

        if (passwordEncoder.matches(password, user.getPassword())) {
            return Optional.of(user);
        }

        return Optional.empty();
    }

    public void updatePassword(Long userId, String newPassword) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

        public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
