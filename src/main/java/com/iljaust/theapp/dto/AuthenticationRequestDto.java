package com.iljaust.theapp.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthenticationRequestDto {
    @NotBlank(message = "Username is mandatory")
    @Size(min = 6, max = 40)
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 40)
    private String password;
}
