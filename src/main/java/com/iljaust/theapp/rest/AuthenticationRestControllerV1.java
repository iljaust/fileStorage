package com.iljaust.theapp.rest;

import com.authy.AuthyException;
import com.iljaust.theapp.dto.AuthenticationRequestDto;
import com.iljaust.theapp.model.User;
import com.iljaust.theapp.service.AuthenticationService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Data
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationRestControllerV1 {
    private AuthenticationService authenticationService;

    public AuthenticationRestControllerV1(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        Map<Object, Object> response =authenticationService.authenticate(requestDto.getUsername(), requestDto.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody User user) {

          if(authenticationService.signup(user) ==null)
              throw new BadCredentialsException("Username or phone number already exist");

        return ResponseEntity.ok(user);
    }

    @PostMapping("/verify")
    public ResponseEntity verify(@RequestBody User user, String code){
        boolean verified = authenticationService.verify(user,code);

        return ResponseEntity.ok(verified);
    }
}
