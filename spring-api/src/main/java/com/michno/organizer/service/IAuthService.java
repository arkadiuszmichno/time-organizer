package com.michno.organizer.service;


import com.michno.organizer.model.User;
import com.michno.organizer.payload.LoginRequest;

public interface IAuthService {
    String authenticateUser(LoginRequest loginRequest);

    void confirmEmail(String token);

    User createUser(User user);
}
