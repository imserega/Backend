package com.example.AuthService.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String role = "USER_ROLE";

    public UserDto(RegistrationRequest request){
        this.email = request.getEmail();
        this.firstname = request.getFirstname();
        this.lastname = request.getLastname();
        this.username = request.getUsername();
        this.role = "USER_ROLE";
    }
}