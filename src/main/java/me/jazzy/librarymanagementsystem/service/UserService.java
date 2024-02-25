package me.jazzy.librarymanagementsystem.service;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.model.User;
import me.jazzy.librarymanagementsystem.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with " + email + "not found"));
    }

    public void saveUser(User user) {

        boolean isEmailAlreadyTaken = userRepository.findByEmail(user.getEmail())
                .isPresent();

        if(isEmailAlreadyTaken)
            throw new IllegalStateException("Email already taken by another user");

        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
}
