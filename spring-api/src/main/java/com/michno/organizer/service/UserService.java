package com.michno.organizer.service;

import com.michno.organizer.exception.ResourceNotFoundException;
import com.michno.organizer.model.User;
import com.michno.organizer.model.VerificationToken;
import com.michno.organizer.repository.UserRepository;
import com.michno.organizer.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoListService todoListService;

    @Override
    public User saveRegisteredUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean usernameIsAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    @Override
    public boolean emailIsAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    @Override
    public void deleteUser(Long id) {
        todoListService.deleteCreatedBy(id);
        userRepository.delete(id);
    }

}
