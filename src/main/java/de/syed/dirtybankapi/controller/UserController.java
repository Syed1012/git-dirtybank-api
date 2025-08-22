package de.syed.dirtybankapi.controller;

import de.syed.dirtybankapi.domain.User;
import de.syed.dirtybankapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    private UserController(UserService userService){
        this.userService = userService;
    }

    // Create a new user
    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestParam String username,
                                           @RequestParam String fullName,
                                           @RequestParam String email,
                                           @RequestParam String password) {
        User createdUser = userService.createUser(username, fullName, email, password);
        return ResponseEntity.ok(createdUser);
    }

    // Get user by username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        if(user != null){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
