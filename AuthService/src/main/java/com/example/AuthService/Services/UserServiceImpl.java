package com.example.AuthService.Services;

import com.example.AuthService.Entities.User;
import com.example.AuthService.Repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    @Override
    public User register(User user) {
        if(this.findByUsername(user.getUsername()) != null) return null;
        else{
            userRepo.save(user);
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}
