package com.michno.organizer.controller;

import com.michno.organizer.model.User;
import com.michno.organizer.payload.UserIdentityAvailability;
import com.michno.organizer.payload.UserProfile;
import com.michno.organizer.payload.UserSummary;
import com.michno.organizer.security.CurrentUser;
import com.michno.organizer.security.UserPrincipal;
import com.michno.organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam("username") String username) {
        Boolean isAvailable = userService.usernameIsAvailable(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam("email") String email) {
        Boolean isAvailable = userService.emailIsAvailable(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("user/{username}")
    public UserProfile getUserProfile(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());

        return userProfile;
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
