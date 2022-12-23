package com.example.AuthService.Services;


import com.example.AuthService.Entities.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtUtil jwt;
    private final UserService userService;
    private final AmqpTemplate amqpTemplate;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public AuthService(final JwtUtil jwt,
                        UserService userService,
                       AmqpTemplate amqpTemplate) {
        this.jwt = jwt;
        this.userService = userService;
        this.amqpTemplate = amqpTemplate;
    }

    public AuthResponse register(RegistrationRequest registrationRequest) {
        try{
            User user = userService.findByUsername(registrationRequest.getUsername());
            if(user != null){
                return new AuthResponse("Username already exist", "Change your credentials");
            }
            user = new User(registrationRequest);
            UserDto newUser = new UserDto(registrationRequest);

            user.setPassword(BCrypt.hashpw(registrationRequest.getPassword(), BCrypt.gensalt()));
            amqpTemplate.convertAndSend("UserReg", mapper.writeValueAsString(newUser));
            userService.register(user);

            String accessToken = jwt.generate(user, "ACCESS");
            String refreshToken = jwt.generate(user, "REFRESH");
            return new AuthResponse(accessToken, refreshToken);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public AuthResponse login(LoginRequest request){
        User user = userService.findByUsername(request.getUsername());
        if(user == null){
            return new AuthResponse("Invalid data","Change your credentials");
        }
        boolean correct = BCrypt.checkpw(request.getPassword(),user.getPassword());
        if(!correct){
            return new AuthResponse("Invalid data","Change your credentials");
        }
        String accessToken = jwt.generate(user, "ACCESS");
        String refreshToken = jwt.generate(user, "REFRESH");
        return new AuthResponse(accessToken, refreshToken);
    }
}
