package com.hw.mixvoice.config.auth;

import com.hw.mixvoice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화시켜 줍니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final  CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .headers().frameOptions().disable()
                .and() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.
                    .authorizeRequests() // URL별 권한 관리를 설정하는 옵션의 시작점입니다.
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    // "/" 등 지정된 URL들은 permitAll 옵션을 통해 전체 열람 권한을 주기
                    .antMatchers("/api/**").hasAnyRole(Role.USER.name(), Role.GUEST.name())
                    // "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능
                    .anyRequest().authenticated() // 나머지 url들은 모두 인증된 사용자들에게만 허용
                .and()
                    .logout().logoutSuccessUrl("/") // 로그아웃 성공 시 / 주소로 이동
                .and()
                    .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);

                // OAuth2 로그인 기능에 대한 설정의 진입점. 로그인 성공이후 사용자 정보 가져올때의 설정. 소셜로그인 성공 시 후속조치를 진행할
                // UserService 인터페이스의 구현체를 등록

    }

}
