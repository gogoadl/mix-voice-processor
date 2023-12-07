package com.hw.mixvoice.config;

import com.hw.mixvoice.config.auth.CustomOAuth2UserService;
import com.hw.mixvoice.config.auth.JwtAuthFilter;
import com.hw.mixvoice.config.auth.OAuth2SuccessHandler;
import com.hw.mixvoice.config.auth.TokenService;
import com.hw.mixvoice.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration // Spring Security 설정들을 활성화시켜 줍니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler successHandler;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors().configurationSource(request -> {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
            cors.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
            cors.setAllowedHeaders(Arrays.asList("*"));
            return cors;
        });
        httpSecurity.
                httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .headers().frameOptions().disable()
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점입니다.
                    .antMatchers("/login/**","/auth/**","/oauth2/**", "/video/**", "/h2-console/**").permitAll()
                    .anyRequest().authenticated() // 나머지 url들은 모두 인증된 사용자들에게만 허용
                .and()
                    .oauth2Login()
                    .redirectionEndpoint()
                    .baseUri("/login/oauth2/code/*")
                .and()
                    .loginPage("http://localhost:3000/pages/login")
                    .successHandler(successHandler)
                    .userInfoEndpoint()
                    .userService(oAuth2UserService);
        httpSecurity.addFilterBefore(new JwtAuthFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
    }

}
