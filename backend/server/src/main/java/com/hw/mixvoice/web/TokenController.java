package com.hw.mixvoice.web;
import com.hw.mixvoice.config.auth.TokenService;
import com.hw.mixvoice.config.auth.dto.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class TokenController {
    private final TokenService tokenService;

    @GetMapping("/token/expired")
    public String auth() {
        return "test";
//        throw new RuntimeException();
    }

    @GetMapping("/token/refresh")
    public ResponseEntity refreshAuth(@RequestParam String token, HttpServletRequest request, HttpServletResponse response) {
        token = tokenService.getJwtFromRequest(token);
        if (token != null && tokenService.verifyToken(token)) {
            String email = tokenService.getUid(token);
            Token newToken = tokenService.generateToken(email, "USER");
            newToken.addPrefix();

            return ResponseEntity.ok().body(newToken);
        }

        throw new RuntimeException();
    }
}