package me.jazzy.librarymanagementsystem.service;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.exception.badrequest.UserBadRequestException;
import me.jazzy.librarymanagementsystem.exception.notfound.UserNotFoundException;
import me.jazzy.librarymanagementsystem.model.User;
import me.jazzy.librarymanagementsystem.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User with " + email + " not found"));
    }

    public void saveUser(User user) {

        boolean isEmailAlreadyTaken = userRepository.findByEmail(user.getEmail())
                .isPresent();

        if(isEmailAlreadyTaken)
            throw new UserBadRequestException("Email already taken by another user");

        userRepository.save(user);
    }
}
