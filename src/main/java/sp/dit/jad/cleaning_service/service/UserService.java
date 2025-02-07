package sp.dit.jad.cleaning_service.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sp.dit.jad.cleaning_service.model.User;
import sp.dit.jad.cleaning_service.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Registers a new user and stores the hashed password.
     */
    public User registerUser(User user) {
        // Hash the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Authenticate the user by verifying the provided password.
     */
    public Optional<User> authenticateUser(String email, String rawPassword) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(rawPassword, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    /**
     * Retrieve a user by their ID.
     */
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
