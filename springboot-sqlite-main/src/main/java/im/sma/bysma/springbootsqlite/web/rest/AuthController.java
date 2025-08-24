package im.sma.bysma.springbootsqlite.web.rest;

import im.sma.bysma.springbootsqlite.domain.User;
import im.sma.bysma.springbootsqlite.dto.ForgotPasswordRequest;
import im.sma.bysma.springbootsqlite.dto.ResetPasswordRequest;
import im.sma.bysma.springbootsqlite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserRepository userRepository;
   @Autowired
    private JavaMailSender mailSender;

    public AuthController(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        // Simulate sending reset token
        String resetToken = "1234";
       // sendResetTokenEmail(request.getEmail(), resetToken);
        return ResponseEntity.ok("Reset token sent to " + request.getEmail());
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        if ("1234".equals(request.getToken())) {
            Optional<User> user = userRepository.findByEmail(request.getEmail());
            if (user == null) {
                return ResponseEntity.badRequest().body("User not found.");
            }
            user.get().setPassword(request.getNewPassword()); // Hash in real apps!
            userRepository.save(user.get());
            return ResponseEntity.ok("Password has been reset.");
        } else {
            return ResponseEntity.badRequest().body("Invalid token.");
        }
    }

    public void sendResetTokenEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset Request");
        message.setText("Your password reset token is: " + token);
        mailSender.send(message);
    }
}