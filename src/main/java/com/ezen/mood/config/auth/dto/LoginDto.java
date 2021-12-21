package com.ezen.mood.config.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class LoginDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
