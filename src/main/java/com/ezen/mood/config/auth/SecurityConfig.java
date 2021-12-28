package com.ezen.mood.config.auth;

import com.ezen.mood.domain.member.Role;
import com.ezen.mood.service.common.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2MemberService;
    private final MemberService memberService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()// 권한별 url 설정
                        .antMatchers("/", "/css/**", "/img/**", "/js/**","/auth/**").permitAll() // 권한 상관없이 모두 Ok
                        .antMatchers("/api/v1/**").hasRole(Role.MEMBER.name()) //USER 권한 url
                        .antMatchers("/admin/**").hasRole(Role.ADMIN.name()) // ADMIN 권한 url
                        .anyRequest().permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                    .formLogin()
                        .loginPage("/auth/login")
                        .defaultSuccessUrl("/")
                        .usernameParameter("email")
                        .permitAll()
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2MemberService);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }


}
