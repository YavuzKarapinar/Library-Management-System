package me.jazzy.librarymanagementsystem.exception.notfound;

import me.jazzy.librarymanagementsystem.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class JwtNotFoundException extends BaseException {
    public JwtNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
