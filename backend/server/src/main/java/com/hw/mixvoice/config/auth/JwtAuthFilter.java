package com.hw.mixvoice.config.auth;

import com.hw.mixvoice.config.auth.dto.UserDto;
import com.hw.mixvoice.domain.user.User;
import com.hw.mixvoice.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {
    private final TokenService tokenService;
    private final UserRepository userRepository;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter called");
        String token = ((HttpServletRequest)request).getHeader("Authorization");
        log.info("token : {}", token);

        if (token != null) {
            if (token.startsWith("BEARER"))
                token = token.split(" ")[1];

            if (tokenService.verifyToken(token)) {

                log.info("token : {}", token);
                String email = tokenService.getUid(token);
                Optional<User> user = userRepository.findByEmail(email);
                if (user.isPresent()) { // 회원가입된 사람
                    UserDto userDto = User.toDto(user.get());
                    Authentication auth = getAuthentication(userDto);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
                else { // 회원가입 로직

                }

            }
        }

        chain.doFilter(request, response);
    }

    public Authentication getAuthentication(UserDto member) {
        return new UsernamePasswordAuthenticationToken(member, "",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}