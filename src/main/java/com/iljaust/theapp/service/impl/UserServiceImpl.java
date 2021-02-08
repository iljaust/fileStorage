package com.iljaust.theapp.service.impl;

import com.authy.AuthyApiClient;
import com.authy.AuthyException;
import com.iljaust.theapp.model.User;
import com.iljaust.theapp.repository.UserRepository;
import com.iljaust.theapp.service.UserService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User register(User user) {
        userRepository.save(user);

        return user;
    }

    @Override
    public List<User> getAll() {

        List<User> result = userRepository.findAll();

        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            return null;
        }

        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);

    }

}
