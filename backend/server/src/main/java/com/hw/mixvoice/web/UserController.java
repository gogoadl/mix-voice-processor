package com.hw.mixvoice.web;

import com.hw.mixvoice.config.auth.TokenService;
import com.hw.mixvoice.domain.user.User;
import com.hw.mixvoice.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String token) {
        token = tokenService.getJwtFromRequest(token);
        if (tokenService.verifyToken(token)) {
            String email = tokenService.getUid(token);
            if (userRepository.findByEmail(email).isPresent())
                return ResponseEntity.ok(userRepository.findByEmail(email).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
