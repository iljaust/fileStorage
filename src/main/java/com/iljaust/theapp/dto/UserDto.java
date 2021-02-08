package com.iljaust.theapp.dto;

import com.iljaust.theapp.model.Role;
import com.iljaust.theapp.model.User;
import lombok.Data;



@Data
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private Role role;

}
