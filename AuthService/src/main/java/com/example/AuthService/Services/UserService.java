package com.example.AuthService.Services;

import com.example.AuthService.Entities.User;

public interface UserService {
    User register(User user);
    User findByUsername(String username);
    User findById(Long id);
}
