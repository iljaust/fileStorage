package com.iljaust.theapp.security.jwt;

import com.iljaust.theapp.model.Role;
import com.iljaust.theapp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole()),
                user.getEnabled());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role userRole) {
       List<GrantedAuthority> authorities = new ArrayList<>();
       authorities.add(new SimpleGrantedAuthority(userRole.name()));

       return authorities;
    }
}
