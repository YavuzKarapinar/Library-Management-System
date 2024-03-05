package me.jazzy.librarymanagementsystem.service;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.dto.BookDTO;
import me.jazzy.librarymanagementsystem.dto.RegisterRequest;
import me.jazzy.librarymanagementsystem.exception.badrequest.UserBadRequestException;
import me.jazzy.librarymanagementsystem.model.*;
import me.jazzy.librarymanagementsystem.validator.EmailValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {

    private final EmailValidation emailValidation;
    private final UserService userService;

    public ResponseModel singUpUser(RegisterRequest request) {

        boolean isEmailValid = emailValidation.test(request.getEmail());

        if(!isEmailValid)
            throw new UserBadRequestException("Email is not valid!");

        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
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


}
