package im.sma.bysma.springbootsqlite.web.rest;

import im.sma.bysma.springbootsqlite.domain.User;
import im.sma.bysma.springbootsqlite.dto.LoginRequest;
import im.sma.bysma.springbootsqlite.dto.RegisterUserRequest;
import im.sma.bysma.springbootsqlite.exception.UserAlreadyExistsException;
import im.sma.bysma.springbootsqlite.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/")
public class UserController {

    private UserRepository inventoryUserRepository;;

    public UserController(UserRepository inventoryUserRepository) {
        this.inventoryUserRepository = inventoryUserRepository;
    }


    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterUserRequest request) {
        if (inventoryUserRepository.existsByUsername(request.getUsername()) || inventoryUserRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Username or email already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // In production, hash the password!
        user.setRole(request.getRole());
        return inventoryUserRepository.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return inventoryUserRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword())
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid username or password"));
    }
}
