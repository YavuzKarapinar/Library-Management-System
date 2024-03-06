package me.jazzy.librarymanagementsystem.service;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.dto.AuthResponseDTO;
import me.jazzy.librarymanagementsystem.dto.LoginDTO;
import me.jazzy.librarymanagementsystem.dto.RegisterDTO;
import me.jazzy.librarymanagementsystem.exception.badrequest.UserBadRequestException;
import me.jazzy.librarymanagementsystem.model.*;
import me.jazzy.librarymanagementsystem.security.jwt.JwtGenerator;
import me.jazzy.librarymanagementsystem.validator.EmailValidation;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {

    private final EmailValidation emailValidation;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final PasswordEncoder passwordEncoder;

    public ResponseModel singUpUser(RegisterDTO request) {

        boolean isEmailValid = emailValidation.test(request.getEmail());

        if(!isEmailValid)
            throw new UserBadRequestException("Email is not valid!");

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                LocalDateTime.now(),
                UserType.MEMBER
        );

        userService.saveUser(user);

        return new ResponseModel(
                HttpStatus.OK.value(),
                "User successfully registered.",
                LocalDateTime.now()
        );
    }

    public AuthResponseDTO loginUser(LoginDTO loginDTO) {

        boolean isEmailValid = emailValidation.test(loginDTO.getEmail());

        if(!isEmailValid)
            throw new UserBadRequestException("Email is not valid!");

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return new AuthResponseDTO(token);
    }


}
