package de.syed.dirtybankapi.services;

import de.syed.dirtybankapi.domain.User;
import de.syed.dirtybankapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(String username, String fullName, String email, String password){
        User user = new User();
        user.setUsername(username);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // Unused method
//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email).orElse(null);
//    }
}
