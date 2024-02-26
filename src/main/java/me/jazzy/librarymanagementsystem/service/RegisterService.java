package me.jazzy.librarymanagementsystem.service;

import lombok.AllArgsConstructor;
import me.jazzy.librarymanagementsystem.model.*;
import me.jazzy.librarymanagementsystem.validator.EmailValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegisterService {

    private final EmailValidation emailValidation;
    private final UserService userService;
    private final BookService bookService;

    public ResponseModel singUpUser(RegisterRequest request) {

        boolean isEmailValid = emailValidation.test(request.getEmail());

        if(!isEmailValid)
            throw new IllegalStateException("Email is not valid!");

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

    public ResponseModel registerBook(Book book) {
        bookService.saveBook(book);

        return new ResponseModel(
                HttpStatus.OK.value(),
                "New Book Added",
                LocalDateTime.now()
        );
    }
}
