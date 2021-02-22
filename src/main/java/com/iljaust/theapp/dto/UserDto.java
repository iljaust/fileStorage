package com.iljaust.theapp.dto;

import com.iljaust.theapp.model.Role;
import com.iljaust.theapp.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class UserDto {
    private Long id;
    @NotBlank(message = "Username is mandatory")
    @Size(min = 6, max = 40)
    private String username;
    @NotBlank(message = "First name is mandatory")
    @Size(max = 40)
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 40)
    private String secondName;
    @NotBlank(message = "Phone number name is mandatory")
    @Size(min = 6, max = 40)
    private String phoneNumber;
    private Role role;

}
