package com.hw.mixvoice.config.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class Token {
    private String token;
    private String refreshToken;

    public Token(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
    public void addPrefix() {
        token = "BEARER " + token;
        refreshToken = "BEARER " + refreshToken;
    }
}