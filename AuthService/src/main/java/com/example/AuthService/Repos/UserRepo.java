package com.example.AuthService.Repos;

import com.example.AuthService.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
}
