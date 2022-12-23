package com.example.AuthService.Entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    public User(){}
    public User(RegistrationRequest request){
        this.username = request.getUsername();
        this.role = "USER_ROLE";
        this.password = request.getPassword();
    }
}
