package com.hw.mixvoice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity redirectLogin() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
