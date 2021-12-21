package com.ezen.mood.config.auth;

import com.ezen.mood.domain.member.ROLE;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2MemberService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()// 권한별 url 설정
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile","/login").permitAll() // 권한 상관없이 모두 Ok
                    .antMatchers("/api/v1/**").hasRole(ROLE.USER.name()) //USER 권한 url
                    .antMatchers("/api/v1/**").hasRole(ROLE.ADMIN.name()) // ADMIN 권한 url
                    .anyRequest().permitAll()
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 시 갈 url
                .and()
                    .formLogin()
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2MemberService);
    }
}
