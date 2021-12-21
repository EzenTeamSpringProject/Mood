package com.ezen.mood.config.auth.dto;

import com.ezen.mood.domain.member.Member;
import com.ezen.mood.domain.member.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Builder
    public AuthDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Member toEntity(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .picture("/img/defaultProfile.png")
                .role(Role.MEMBER)
                .build();
    }
}
