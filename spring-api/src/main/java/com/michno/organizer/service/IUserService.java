package com.michno.organizer.service;

import com.michno.organizer.model.User;

public interface IUserService {
    User saveRegisteredUser(User user);

    boolean usernameIsAvailable(String username);

    boolean emailIsAvailable(String email);

    User findUserByUsername(String username);

    void deleteUser(Long id);
}
