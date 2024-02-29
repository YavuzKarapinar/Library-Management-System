package me.jazzy.librarymanagementsystem.exception.notfound;

import me.jazzy.librarymanagementsystem.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
