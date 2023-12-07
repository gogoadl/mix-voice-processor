package com.hw.mixvoice.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hw.mixvoice.config.auth.dto.Token;
import com.hw.mixvoice.config.auth.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenService tokenService;
    private final UserRequestMapper userRequestMapper;

    private static final String TOKEN = "token";
    private static final String REFRESH_TOKEN = "refreshToken";
    private static final String REDIRECT_URL = "http://localhost:3000/login/redirect";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        UserDto userDto = userRequestMapper.toDto(oAuth2User);

        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);
        // 최초 로그인이라면 회원가입 처리를 한다.
        String targetUrl;
        log.info("토큰 발행 시작");

        Token token = tokenService.generateToken(userDto.getEmail(), "USER");
        log.info("{}", token);
        targetUrl = UriComponentsBuilder.fromUriString(REDIRECT_URL)
                .queryParam(TOKEN, "BEARER " + token.getToken())
                .queryParam(REFRESH_TOKEN, "BEARER " + token.getRefreshToken())
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}