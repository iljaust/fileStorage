package com.iljaust.theapp.service;

import com.iljaust.theapp.model.User;
import com.iljaust.theapp.repository.UserRepository;
import com.iljaust.theapp.security.jwt.JwtTokenProvider;
import com.iljaust.theapp.util.Sender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    private final Sender sender;



    public AuthenticationService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.sender = new Sender();
    }

    public Map<Object,Object> authenticate(String username,String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        User user = userRepository.findByUsername(username);
        if(user == null )
            throw new UsernameNotFoundException("User doesn't exist");

        String token = jwtTokenProvider.createToken(username,user.getRole().name());

        Map<Object,Object> response =  new HashMap<>();
        response.put("username",username);
        response.put("token",token);

        return response;

    }

    public User signup(User user) {


        if(userRepository.findByUsername(user.getUsername()) == null) {
            User userReg = new User();
            userReg.setUsername(user.getUsername());
            userReg.setFirstName(user.getFirstName());
            userReg.setSecondName(user.getSecondName());
            userReg.setPassword(user.getPassword());
            userReg.setPhoneNumber(user.getPhoneNumber());
            userReg.setVerCode(codeGenerator());

            userRepository.save(userReg);

            sender.sendCodeSms(userReg.getPhoneNumber(),userReg.getVerCode());

            return userReg;

        }

        return null;
    }


    public boolean verify(User user,String code){
        if(user.getVerCode().equals(code))
            user.setEnabled(true);


        return user.getEnabled();
    }

    private String codeGenerator(){
        Random random = new Random();
        String code = String.format("%04d", random.nextInt(10000));

        return code;
    }



}
